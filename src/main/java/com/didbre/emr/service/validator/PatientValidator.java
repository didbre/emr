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

    protected void validateUpdate(PatientVO patientVO) throws Exception
    {

    }
}
