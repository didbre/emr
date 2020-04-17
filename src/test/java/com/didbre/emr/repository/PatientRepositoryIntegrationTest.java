package com.didbre.emr.repository;

import com.didbre.emr.domain.Patient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
//@AutoConfigureTestDatabase
public class PatientRepositoryIntegrationTest
{
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PatientRepository patientRepository;


    @Test
    public void whenFindPatientById_thenReturnPatient()
    {
////        given
//        Patient patient = new Patient();
//        patient.setId(6666666666666L);
//        patient.setFirstName("Francois");
//        patient.setLastName("Pignon");
//        patient.setAddressLine1("123 foo");
//        patient.setCity("montreal");
//        patient.setProvince("qc");
//        patient.setCountry("canada");
//        patient.setZipCode("1j1 2k2");
//        patient.setPhoneNumber("5141234567");
//        patient.setHin("BRED18107212");
//
//        entityManager.persist(patient);
//        entityManager.flush();
//
////        when
//        Optional<Patient> found = patientRepository.findById(6666666666666L);
//
////        assert
//        assertThat(found.get().getFirstName()).isEqualTo(patient.getFirstName());



    }
}
