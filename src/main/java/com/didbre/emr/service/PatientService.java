package com.didbre.emr.service;

import com.didbre.emr.domain.Patient;
import com.didbre.emr.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
   * Find a patient by is patientId
   *
   * @param patientId
   * @return
   * @throws NoSuchElementException
   */
  public Patient findPatientById(Long patientId) throws NoSuchElementException {

    try {
      return this.repository.findById(patientId).get();
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
  public List<Patient> findAllPatients() throws NoSuchElementException {
    try {
      return this.repository.findAll();
    } catch (NoSuchElementException e) {
      throw new NoSuchElementException("No patient found");
    }
  }

  public void updatePatient() {}
}
