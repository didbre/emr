package com.didbre.emr.service.validator;

import com.didbre.emr.domain.Patient;
import com.didbre.emr.repository.PatientRepository;
import com.didbre.emr.service.vo.PatientVO;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

/** Validation for Patient before interacting with the database */
@Service
@Transactional
public class PatientValidator {
  private final PatientRepository patientRepository;

  public PatientValidator(PatientRepository patientRepository) {
    this.patientRepository = patientRepository;
  }

  /**
   * Validate Patient before save
   *
   * @param patientVO
   * @throws Exception
   */
  public void validateSave(PatientVO patientVO) throws Exception {
    //        check if the id is null
    if (patientVO.getId() != null) {
      //            throw error. Save patient cannot have id. Generate id done at db level
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
    BeanUtils.copyProperties(patient, patientVO);
    Patient save = patientRepository.save(patient);
    PatientVO patientVO1 = new PatientVO();
    BeanUtils.copyProperties(patientVO1, save);

    return patientVO1;
  }
}
