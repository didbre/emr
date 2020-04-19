package com.didbre.emr.web.rest;

import com.didbre.emr.config.Patch;
import com.didbre.emr.config.PatchRequestBody;
import com.didbre.emr.domain.Patient;
import com.didbre.emr.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
public class PatientResource {
  Logger log = LoggerFactory.getLogger(PatientResource.class);

  private PatientService patientService;

  public PatientResource(PatientService patientService) {

    this.patientService = patientService;
  }

  @GetMapping("/{patientId}")
  public Patient findPatientById(@PathVariable(value = "patientId") Long patientId)
      throws Exception {

    log.info("Find patient with ID <{}>", patientId);
    return patientService.findPatientById(patientId);
  }

  @GetMapping("/all")
  public List<Patient> findAllPatients() throws Exception {

    log.info("Find all patients");
    return patientService.findAllPatients();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
  @Patch(service = PatientService.class, id = Long.class)
  public Patient updatePatient(@PathVariable Long id, @PatchRequestBody Patient patient) throws Exception {

    patient.setId(id);
    return patientService.save(patient);
  }
}
