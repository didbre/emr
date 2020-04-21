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

  /**
   * Get patient by id
   *
   * @param patientId
   * @return PatientVO Virtual Object of Patient
   * @throws Exception
   */
  @GetMapping(value = "/{patientId}")
  public PatientVO findPatientById(@PathVariable(value = "patientId") Long patientId)
      throws Exception {

    return patientService.findPatientById(patientId);
  }

  /**
   * Get all patients
   *
   * @return List of Virtual Object
   * @throws Exception
   */
  @GetMapping(value = "/all")
  public List<PatientVO> findAllPatients() throws Exception {

    return patientService.findAllPatients();
  }

  @PostMapping("patient")
  public PatientVO createPatient(@RequestBody PatientVO patientVO) throws Exception {

    return patientService.createPatient(patientVO);
  }

  @PatchMapping("patient_update")
  public PatientVO updatePatient(@RequestBody PatientVO patientVO) throws Exception{

    return patientService.updatePatient(patientVO);
  }
}
