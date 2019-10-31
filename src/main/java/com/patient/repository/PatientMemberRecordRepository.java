package com.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.patient.entity.PatientMemberRecord;
/**
 * The repository interface for the PatientMemberRecord domain object
 */
@Repository
public interface PatientMemberRecordRepository extends JpaRepository<PatientMemberRecord, String> {
}