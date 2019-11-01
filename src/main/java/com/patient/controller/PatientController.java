package com.patient.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.patient.dto.PatientDTO;
import com.patient.service.PatientService;

/**
 * REST controller exposing operations for the Patient domain object
 * According to Rest FULL service principal we should perform operation on A
 * @author Debashis 
 */

@RestController
@RequestMapping(path = "/patient")
public class PatientController {

	@Autowired
	private PatientService patientService;

	@GetMapping("/{enterpriseId}")
	public ResponseEntity<Object> get(@PathVariable String enterpriseId) {

		Optional<PatientDTO> response = patientService.get(enterpriseId);

		if (response != null) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@DeleteMapping("/{enterpriseId}")
	public ResponseEntity<Object> delete(@PathVariable String enterpriseId) {

		boolean isDeleted = patientService.delete(enterpriseId);

		if (isDeleted) {
			//TODO : Messgae should move to Properties file and we should have a object for the same
			return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully");
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<Object> post(@RequestBody PatientDTO patient) {
        Optional<PatientDTO> data = patientService.save(patient);
        if (data.isPresent()) {
			//TODO : Messgae should move to Properties file
			return ResponseEntity.status(HttpStatus.OK).body(data);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping("/")
	public ResponseEntity<Object> put(@RequestBody PatientDTO patient) {
        Optional<PatientDTO> data = patientService.save(patient);
        if (data.isPresent()) {
			//TODO : Messgae should move to Properties file
			return ResponseEntity.status(HttpStatus.OK).body(data);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	
}
