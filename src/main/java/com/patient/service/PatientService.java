package com.patient.service;

import java.util.Optional;

import com.patient.dto.PatientDTO;
/**
 * Provides business operations that are closely associated with the Patient domain 
 * object 
 */
public interface PatientService {	
	public Optional<PatientDTO> get(String enterpriseId);
	public Optional<PatientDTO> update(PatientDTO patient);
	public Optional<PatientDTO> save(PatientDTO patient);
	public boolean delete(String enterpriseId);
}