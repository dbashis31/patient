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
	@Query("SELECT pat FROM Patient pat "
			+ " Join fetch pat.memberRecords "
			+ " left join fetch pat.memberRecords.address "
			+ " where pat.enterpriseId = :enterpriseId ")
	Patient findByPatientByEnterpriseId(@Param("enterpriseId") String enterpriseId);
	long deleteByEnterpriseId(String enterpriseId);
}