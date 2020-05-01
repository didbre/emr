package com.didbre.emr.web.rest;

import com.didbre.emr.service.PatientService;
import com.didbre.emr.service.validator.PatientValidator;
import com.didbre.emr.service.vo.PatientVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
    value = "/api/patient/",
    produces = {MediaType.APPLICATION_JSON_VALUE},
    consumes = {MediaType.APPLICATION_JSON_VALUE})
@CrossOrigin
@Slf4j
public class PatientController {

  private PatientService patientService;

  private PatientValidator patientValidator;

  public PatientController(PatientService patientService, PatientValidator patientValidator) {

    this.patientService = patientService;
    this.patientValidator = patientValidator;
  }

  /**
   * Get patient by id
   *
   * @param patientId
   * @return PatientVO Virtual Object of Patient
   * @throws Exception
   */
  @GetMapping(value = "{patientId}")
  public PatientVO findPatientById(@PathVariable(value = "patientId") String patientId)
      throws Exception {
    patientValidator.validateQuery(patientId);
    return patientService.findPatientById(patientId);
  }

  /**
   * Get all patients
   *
   * @return List of Virtual Object
   * @throws Exception
   */
  @GetMapping(value = "all")
  public List<PatientVO> findAllPatients() throws Exception {

    return patientService.findAllPatients();
  }

  /**
   * Create a patient
   *
   * @param patientVO
   * @return
   * @throws Exception
   */
  @PostMapping("create")
  public PatientVO createPatient(@RequestBody PatientVO patientVO) throws Exception {
    log.info("about to create patient with the following values");
    log.info(patientVO.toString());
    return patientService.createPatient(patientVO);
  }

  /**
   * Update a patient
   *
   * @param patientVO
   * @return
   * @throws Exception
   */
  @PatchMapping("update")
  public PatientVO updatePatient(@RequestBody PatientVO patientVO) throws Exception {

    return patientService.updatePatient(patientVO);
  }

  /**
   * Delete a patient
   *
   * @param patientId
   * @throws Exception
   */
  @DeleteMapping(value = "delete/{patientId}")
  public void deletePatient(@PathVariable(value = "patientId") Long patientId) throws Exception {

    patientService.deletePatient(patientId);
  }
}
