package com.didbre.emr.service.validator;

import com.didbre.emr.repository.PatientRepository;
import com.didbre.emr.service.vo.PatientVO;

/**
 * Validation for Patient before interacting with the database
 */
public class PatientValidator
{
    private final PatientRepository patientRepository;

    public PatientValidator(PatientRepository patientRepository)
    {
        this.patientRepository = patientRepository;
    }

    /**
     * Validate Patient before save
     *
     * @param patientVO
     * @throws Exception
     */
    protected void validateSave(PatientVO patientVO) throws Exception
    {
//        check if the id is null
        if (patientVO.getId() != null)
        {
//            throw error. Save patient cannot have id. Generate id done at db level
        }
    }

    /**
     * Validate Patient before update
     * @param patientVO
     * @throws Exception
     */
    protected void validateUpdate(PatientVO patientVO) throws Exception
    {
//        check if patient exist
        if (patientVO.getId() == null)
        {
//            patient id cannot be null
//            todo change exception
            throw new Exception("Cannot update a patient without id");
        }
        try
        {
            patientRepository.findById(patientVO.getId());
        }
        catch (Exception e)
        {
//            patient is not in the database

        }
    }
}
