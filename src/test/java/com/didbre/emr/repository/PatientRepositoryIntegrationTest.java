package com.didbre.emr.repository;

import com.didbre.emr.InitiateTestData;
import com.didbre.emr.domain.Patient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PatientRepositoryIntegrationTest
{
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PatientRepository patientRepository;

    @Test
    @Transactional
    public void whenFindPatientById_thenReturnPatient()
    {

//        given

        entityManager.persist(InitiateTestData.initiatePatient());
        entityManager.flush();

//        when
        Optional<Patient> found = patientRepository.findById(1L);

//        assert
        assertThat(found.get().getFirstName()).isEqualTo(InitiateTestData.initiatePatient().getFirstName());
    }
}
