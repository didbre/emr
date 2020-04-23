package com.didbre.emr.service;

import com.didbre.emr.domain.Patient;
import com.didbre.emr.repository.PatientRepository;
import com.didbre.emr.service.validator.PatientValidator;
import com.didbre.emr.service.vo.PatientVO;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class PatientService {
  Logger log = LoggerFactory.getLogger(PatientService.class);

  private final PatientRepository repository;

  private final PatientValidator validator;

  public PatientService(PatientRepository patientRepository, PatientValidator validator) {
    this.repository = patientRepository;
    this.validator = validator;
  }

  /**
   * Find a patient by is patientId the convert it in patientVO
   *
   * @param patientId
   * @return
   * @throws NoSuchElementException
   */
  public PatientVO findPatientById(Long patientId) throws Exception {

    try {
      PatientVO patientVO = new PatientVO();
      BeanUtils.copyProperties(patientVO, repository.findById(patientId).get());
      return patientVO;

    } catch (NoSuchElementException e) {
      throw new NoSuchElementException("No patient found with id <" + patientId + ">");
    }
  }

  /**
   * Find all patients
   *
   * @return
   * @throws NoSuchElementException
   */
  public List<PatientVO> findAllPatients() throws Exception {
    try {
      List<Patient> patients = new ArrayList<>();
      List<PatientVO> patientsVO = new ArrayList<>();

      repository.findAll().forEach(e -> patients.add(e));

      for (int i = 0; i < patients.size(); i++) {
        PatientVO patientVO = new PatientVO();
        BeanUtils.copyProperties(patientVO, patients.get(i));
        patientsVO.add(patientVO);
      }

      return patientsVO;

    } catch (NoSuchElementException e) {
      //    todo change the message of a exception without creating new one
      throw new NoSuchElementException("No patient found");
    }
  }

  /**
   * Create a new patient
   *
   * @param patientVO
   * @return
   * @throws Exception
   */
  public PatientVO createPatient(PatientVO patientVO) throws Exception {


//    try
//    {
//      repository.findById(patientVO.getId());
//    }
//    catch (NoSuchElementException e)
//    {
//      throw new NoSuchElementException("Patient ID <"+patientVO.getId()+"> already exist");
//    }
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
    PatientValidator validator = new PatientValidator(this.repository);
    return validator.validateUpdate(patientVO);
  }
}
