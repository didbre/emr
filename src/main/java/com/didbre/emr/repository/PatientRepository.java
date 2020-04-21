package com.didbre.emr.repository;

import com.didbre.emr.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>
{
}
