package com.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.patient.entity.Patient;
/**
 * The repository interface for the Patient domain object
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {
	// TODO : We can solve N+1 problem using join fetch
	Patient findByEnterpriseId(String enterpriseId);
	long deleteByEnterpriseId(String enterpriseId);
}