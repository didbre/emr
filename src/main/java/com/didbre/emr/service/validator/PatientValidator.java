package com.didbre.emr.service.validator;

import com.didbre.emr.domain.Patient;
import com.didbre.emr.repository.PatientRepository;
import com.didbre.emr.service.vo.PatientVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.NoSuchElementException;

/** Validation for Patient before interacting with the database */
@Service
@Transactional
@Slf4j
public class PatientValidator {
  private final PatientRepository patientRepository;

  public PatientValidator(PatientRepository patientRepository) {
    this.patientRepository = patientRepository;
  }

  /**
   * Validate query request
   *
   * <p>1. Check if the search criteria is a number
   *
   * @param searchId
   * @throws Exception
   */
  public void validateQuery(String searchId) throws NumberFormatException {
    if (!NumberUtils.isCreatable(searchId)) {
      throw new NumberFormatException(
          "< " + searchId + " > is not a valid search criteria. Only number is accepted");
    }
  }

  /**
   * Validate Patient before save
   *
   * @param patientVO
   * @throws Exception
   */
  public void validateSave(PatientVO patientVO) throws Exception {

    if (patientVO.getId() != null) {
      throw new Exception("Cannot create new patient with an ID ");
    }

  }

  /**
   * Validate Patient before update
   *
   * @param patientVO
   * @throws Exception
   */
  public final PatientVO validateUpdate(PatientVO patientVO) throws Exception {
    //        check if patient exist
    if (patientVO.getId() == null) {
      //            patient id cannot be null
      //            todo change exception
      throw new NoSuchElementException("Cannot update a patient without id");
    }
    try {
      patientRepository.findById(patientVO.getId());
    } catch (NoSuchElementException noSuchElementException) {
      //            patient is not in the database
      throw new NoSuchElementException(
          String.format("Patient with ID < %s > is not existing", patientVO.getId()));
    } catch (Exception e) {
      throw e;
    }

    Patient patient = new Patient();
    //    Copy info from the request to the pojo used for the database
    BeanUtils.copyProperties(patient, patientVO);
    //    Save the patient
    Patient patientSaved = patientRepository.save(patient);
    PatientVO patientVOReturn = new PatientVO();
    //    Copy info from the pojo used for the database to the pojo used by the web tier
    BeanUtils.copyProperties(patientVOReturn, patientSaved);

    return patientVOReturn;
  }
}
