package com.didbre.emr.web.rest;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.Date;

/**
 * Patient Data Transfer Object
 * Used between the web and the service layer of the application
 */
@Data
public class PatientDTO
{
    @Setter(AccessLevel.NONE)
    private Long id;

    private String firstName;
    private String middleName;
    private String lastName;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String appOrLocal;
    private String city;
    private String province;
    private String country;
    private String zipCode;
    private String phoneNumber;
    private String phoneExtension;
    private String email;
    private String hin;
    private Date birthDate;
}
