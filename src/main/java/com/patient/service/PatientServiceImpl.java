package com.patient.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.security.auth.login.CredentialException;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.patient.dto.PatientDTO;
import com.patient.exception.CpException;
import com.patient.repository.AddressRepository;
import com.patient.repository.PatientMemberRecordRepository;
import com.patient.repository.PatientRepository;
import com.patient.entity.*;

@Service
// Transaction management 
@Transactional
public class PatientServiceImpl implements PatientService {
    
	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
    private PatientRepository patientRepo;
	
	@Autowired
    private PatientMemberRecordRepository patientMemberRecordRepo;
	
	@Autowired
    private AddressRepository addressRepo;
	
	/**
     * Returns an {@code  Optional<PatientDTO>} 
     * @param enterpriseId the global patient ID
     * @return an {@code Optional<PatientDTO>} if Patient info is present
     * is non-null, otherwise an empty {@code Optional}
     */
	public Optional<PatientDTO> get(String enterpriseId) {
		try {
			Patient patient = patientRepo.findByEnterpriseId(enterpriseId);
			PatientDTO dtoPatient = modelMapper.map(patient, PatientDTO.class);
			return Optional.ofNullable(dtoPatient);
		} catch (Exception e) {
			StringBuffer errorMsg = new StringBuffer();
			errorMsg.append("Exception when getting patient information by patientId ");
			errorMsg.append(enterpriseId);
			throw new CpException("500", errorMsg.toString(), e);

		}
	}

	

	/**
     * Returns an {@code  boolean} 
     * @param PatientDTO patient data
     * @return a {@code Optional<PatientDTO>}
     * This method with Aspect this will make autocommit false and execute the method if any exception
     * occured then it will rollback else commit 
     */
	public Optional<PatientDTO> save(PatientDTO patient) {
	   try {
			Patient patientInfo = modelMapper.map(patient, Patient.class);
			List<PatientMemberRecord> patientMemberRecord = patientInfo.getMemberRecords();
			patientInfo.setMemberRecords(null);
			patientInfo =patientRepo.save(patientInfo);
			
			if(patientInfo!=null) {
				for(PatientMemberRecord record : patientMemberRecord) {
					record.setPatientId(patientInfo.getId());
					List<Address> addressList = record.getAddress();
					PatientMemberRecord createdRecord = patientMemberRecordRepo.save(record);
					if(createdRecord!=null) {
						for(Address address : addressList) {
							address.setPatientMemberRecordid(createdRecord.getId());
							Address createdAddress = addressRepo.save(address);
							createdRecord.setAddress(new ArrayList<Address>());
							createdRecord.getAddress().add(createdAddress);
						}
						patientInfo.setMemberRecords(new ArrayList<PatientMemberRecord>());
						patientInfo.getMemberRecords().add(createdRecord);
					}
				}
			}
			PatientDTO dtoPatient = modelMapper.map(patientInfo, PatientDTO.class);
			return Optional.ofNullable(dtoPatient);
		} catch (Exception e) {
			StringBuffer errorMsg = new StringBuffer();
			errorMsg.append("Exception when adding patient information ");
			throw new CpException("500", errorMsg.toString(), e);

		} 
	}
	
	/**
     * Returns an {@code  boolean} 
     * @param PatientDTO patient data
     * @return a {@code Optional<PatientDTO>}
     * This method with Aspect this will make autocommit false and execute the method if any exception
     * occured then it will rollback else commit 
     */
	public Optional<PatientDTO> update(PatientDTO patient) {
	   try {
			Patient patientInfo = modelMapper.map(patient, Patient.class);
			patientInfo =patientRepo.save(patientInfo);			
			return Optional.ofNullable(patient);
		} catch (Exception e) {
			StringBuffer errorMsg = new StringBuffer();
			errorMsg.append("Exception when adding patient information ");
			throw new CpException("500", errorMsg.toString(), e);

		} 
	}

	/**
     * Returns an {@code  boolean} 
     * @param enterpriseId the global patient ID
     * @return a {@code boolean} if patient all info is deleted successfully return true else false
     * This method with Aspect this will make autocommit false and execute the method if any exception
     * occured then it will rollback else commit 
     */
	public boolean delete(String enterpriseId) {
		boolean res = false;
		try {
			Patient patient = patientRepo.findByEnterpriseId(enterpriseId);
			if(patient !=null && !CollectionUtils.isEmpty(patient.getMemberRecords())) {
				// Loop through all members in order to get address associate with this
				// O(N^2*N)
				patient.getMemberRecords().forEach(member->{
					addressRepo.deleteAll(member.getAddress());
				});
				patientMemberRecordRepo.deleteAll(patient.getMemberRecords());
			}
			
			Long patientDeletedCount = patientRepo.deleteByEnterpriseId(enterpriseId);
			if(patientDeletedCount != 0) {
				res = true;
			} 
			
		} catch (Exception e) {
			StringBuffer errorMsg = new StringBuffer();
			errorMsg.append("Exception when deleting patient information by patientId ");
			errorMsg.append(enterpriseId);
			throw new CpException("500", errorMsg.toString(), e);

		}
		return res;
	}

}
