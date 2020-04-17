package com.didbre.emr.web.rest;

import com.didbre.emr.domain.Patient;
import com.didbre.emr.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PatientResource {
  Logger log = LoggerFactory.getLogger(PatientResource.class);

  private PatientService patientService;

  public PatientResource(PatientService patientService) {
    this.patientService = patientService;
  }

  @GetMapping("/patient/{patientId}")
  public Patient findPatientById(@PathVariable(value = "patientId") Long patientId)
      throws Exception {
    log.info("Find patient with ID <{}>", patientId);
    return patientService.findPatientById(patientId);
  }

  @GetMapping("/patients")
  public List<Patient> findAllPatients() throws Exception {
    log.info("Find all patients");
    return patientService.findAllPatients();
  }

  @PutMapping("/employees/{id}")
  Patient replacePatient(@RequestBody Patient newPatient, @PathVariable Long id) {

    Patient patient = patientService.findPatientById(id);


    return patient;

  }
}
