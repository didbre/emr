package com.didbre.emr.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
@Table(name = "patient")
public class Patient {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  private Long id;

  @NotNull
  @Size(max = 50)
  @Column(name = "first_name", length = 50)
  private String firstName;

  @Size(max = 50)
  @Column(name = "middle_name", length = 50)
  private String middleName;

  @NotNull
  @Size(max = 50)
  @Column(name = "last_name", length = 50)
  private String lastName;

  @NotNull
  @Temporal(TemporalType.DATE)
  @Column(name = "birth_date")
  private Date birthDate;

  //    todo create address object

  @NotNull
  @Size(max = 300)
  @Column(name = "address_line_1", length = 300)
  private String addressLine1;

  @Size(max = 300)
  @Column(name = "address_line_2", length = 300)
  private String addressLine2;

  @Size(max = 300)
  @Column(name = "address_line_3", length = 300)
  private String addressLine3;

  @Size(max = 20)
  @Column(name = "app_or_local", length = 20)
  private String appOrLocal;

  @NotNull
  @Size(max = 100)
  @Column(name = "city", length = 100)
  private String city;

  @NotNull
  @Size(max = 100)
  @Column(name = "province", length = 100)
  private String province;

  @NotNull
  @Size(max = 100)
  @Column(name = "country", length = 100)
  private String country;

  @NotNull
  @Size(max = 10)
  @Column(name = "zip_code", length = 10)
  private String zipCode;

  //    todo create phone object
  //    todo create enum for phone kind : fixed, cellular, desk, wife, etc

  @NotNull
  @Size(max = 20)
  @Column(name = "phone_number", length = 20)
  private String phoneNumber;

  @Size(max = 10)
  @Column(name = "phone_extension", length = 10)
  private String phoneExtension;

  @Email
  @Size(min = 5, max = 254)
  @Column(name = "email", length = 254)
  private String email;

  //    Health Insurance Number
  @NotNull
  @Size(max = 20)
  @Column(name = "hin", length = 20)
  private String hin;

  //    todo create object specific to health condition : dmla, diabétique, etc

}
