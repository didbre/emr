package com.didbre.emr;

import com.didbre.emr.domain.Patient;

import java.util.Date;

public final class InitiateTestData
{
    public static Patient initiatePatient()
    {
        Patient patient = new Patient();
        patient.setFirstName("Francois");
        patient.setLastName("Pignon");
        patient.setBirthDate(new Date());
        patient.setAddressLine1("123 foo");
        patient.setCity("montreal");
        patient.setProvince("qc");
        patient.setCountry("canada");
        patient.setZipCode("1j1 2k2");
        patient.setPhoneNumber("5141234567");
        patient.setHin("BRED18107212");

        return patient;
    }
}
