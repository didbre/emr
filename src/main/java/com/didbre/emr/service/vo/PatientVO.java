package com.didbre.emr.service.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

/** Patient Virtual Object */
@Data
public class PatientVO {
//  @JsonIgnore
  private Long id;

  private String firstName;
  private String middleName;
  private String lastName;
  private Date birthDate;
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
}
