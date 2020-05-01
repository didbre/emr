package com.didbre.emr.service;

import com.didbre.emr.domain.Patient;
import com.didbre.emr.repository.PatientRepository;
import com.didbre.emr.service.validator.PatientValidator;
import com.didbre.emr.service.vo.PatientVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@Slf4j
public class PatientService {

  private final PatientRepository repository;

  private final PatientValidator validator;

  public PatientService(PatientRepository patientRepository, PatientValidator validator) {
    this.repository = patientRepository;
    this.validator = new PatientValidator(this.repository);
  }

  /**
   * Find a patient by is patientId the convert it in patientVO
   *
   * @param patientId
   * @return
   * @throws Exception
   */
  public PatientVO findPatientById(String patientId) throws Exception {

    Patient patient =
        repository
            .findById(NumberUtils.createLong(patientId))
            .orElseThrow(
                () ->
                    new NoSuchElementException(
                        String.format("No result for patient ID < %s >", patientId)));
    PatientVO patientVO = new PatientVO();
    BeanUtils.copyProperties(patientVO, patient);
    return patientVO;
  }

  /**
   * Find all patients
   *
   * @return
   * @throws NoSuchElementException
   */
  public List<PatientVO> findAllPatients() throws Exception {

    List<Patient> patients = new ArrayList<>();
    repository.findAll().forEach(e -> patients.add(e));

    if (patients.isEmpty()) {
      throw new NoSuchElementException("No patient at all");
    }

    List<PatientVO> patientsVO = new ArrayList<>();

    for (Patient e : patients) {
      PatientVO patientVO = new PatientVO();
      BeanUtils.copyProperties(patientVO, e);
      patientsVO.add(patientVO);
    }

    return patientsVO;
  }

  /**
   * Create a new patient
   *
   * @param patientVO
   * @return
   * @throws Exception
   */
  public PatientVO createPatient(PatientVO patientVO) throws Exception {

    validator.validateSave(patientVO);
    PatientVO patientSaved = new PatientVO();
    Patient patient = new Patient();
    BeanUtils.copyProperties(patient, patientVO);

    Patient save = repository.save(patient);
    BeanUtils.copyProperties(patientSaved, save);

    return patientSaved;
  }

  /**
   * Update a patient
   *
   * @param patientVO
   * @return
   * @throws Exception
   */
  public PatientVO updatePatient(PatientVO patientVO) throws Exception {
    return validator.validateUpdate(patientVO);
  }

  /**
   * Delete a patient
   *
   * @param patientId
   * @throws Exception
   */
  public void deletePatient(Long patientId) throws Exception
  {
    log.info("About to delete patient with patientID <{}>", patientId);
    try
    {
      repository.deleteById(patientId);
    }
    catch (EmptyResultDataAccessException e)
    {
      throw new EmptyResultDataAccessException(
          String.format("Cannot delete. No result for patient ID < %s >", patientId), 1);
    }
  }
}
