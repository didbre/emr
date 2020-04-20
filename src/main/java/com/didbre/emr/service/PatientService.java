package com.didbre.emr.service;

import com.didbre.emr.domain.Patient;
import com.didbre.emr.repository.PatientRepository;
import com.didbre.emr.service.vo.PatientVO;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class PatientService {
  Logger log = LoggerFactory.getLogger(PatientService.class);

  private final PatientRepository repository;

  public PatientService(PatientRepository patientRepository) {
    this.repository = patientRepository;
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

  public Patient save(Patient patient) throws Exception {

    log.info("save the patient");
    log.info(patient.toString());
    Patient patient1 = null;
    try {
      patient1 = repository.save(patient);
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    return patient1;
  }
}
