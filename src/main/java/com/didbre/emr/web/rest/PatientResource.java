package com.didbre.emr.web.rest;

import com.didbre.emr.service.PatientService;
import com.didbre.emr.service.vo.PatientVO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
    value = "/api/patient",
    produces = {MediaType.APPLICATION_JSON_VALUE},
    consumes = {MediaType.APPLICATION_JSON_VALUE})
@CrossOrigin
public class PatientResource {

  private PatientService patientService;

  public PatientResource(PatientService patientService) {

    this.patientService = patientService;
  }

  @GetMapping(value = "/{patientId}")
  public PatientVO findPatientById(@PathVariable(value = "patientId") Long patientId)
      throws Exception {

    return patientService.findPatientById(patientId);
  }

  @GetMapping(value = "/all")
  public List<PatientVO> findAllPatients() throws Exception {

    return patientService.findAllPatients();
  }
}
