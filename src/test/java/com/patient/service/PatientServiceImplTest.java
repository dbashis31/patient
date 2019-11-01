package com.patient.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;
import com.patient.dto.PatientDTO;
import com.patient.entity.Address;
import com.patient.entity.Patient;
import com.patient.entity.PatientMemberRecord;
import com.patient.exception.CpException;
import com.patient.repository.AddressRepository;
import com.patient.repository.PatientMemberRecordRepository;
import com.patient.repository.PatientRepository;

@RunWith(SpringRunner.class)
public class PatientServiceImplTest {
    
	
	@Mock
    private ModelMapper modelMapper;
	
	@Mock
    private PatientRepository patientRepo;
	
	@Mock
    private PatientMemberRecordRepository patientMemberRecordRepo;
	
	@Mock
    private AddressRepository addressRepo;
	
	@InjectMocks
	PatientServiceImpl patientServiceImpl;
	
	
	@Test
	public void testGetReturnCorectObject() {
		String argument = "1234";
		Patient p=new Patient();
		PatientDTO dtoPatient=new PatientDTO(); ;
		when(patientRepo.findByEnterpriseId(argument)).thenReturn(p);
		when(modelMapper.map(p, PatientDTO.class)).thenReturn(dtoPatient);
		assertEquals(dtoPatient, patientServiceImpl.get(argument).get());
	}
	
	@Test
	public void testGetReturnNull() {
		String argument = "1234";
		Patient p=null;
		PatientDTO dtoPatient=null; ;
		when(patientRepo.findByEnterpriseId(argument)).thenReturn(p);
		when(modelMapper.map(p, PatientDTO.class)).thenReturn(dtoPatient);
		assertFalse(patientServiceImpl.get(argument).isPresent());
	}
	
	@Test(expected = CpException.class)
	public void testGetException() {
		String argument = "1234";
		Patient p=null;
		PatientDTO dtoPatient=null;
	    when(patientRepo.findByEnterpriseId(argument)).thenReturn(p);
		when(modelMapper.map(p, PatientDTO.class)).thenThrow(NullPointerException.class);
		patientServiceImpl.get(argument);
	}
	
	@Test
	public void testDeleteSuccess() {
		String argument = "1234";
		Patient p=new Patient();
		when(patientRepo.findByEnterpriseId(argument)).thenReturn(p);
		when(patientRepo.deleteByEnterpriseId(argument)).thenReturn(1l);
		assertEquals(true, patientServiceImpl.delete(argument));
	}
	
	@Test
	public void testDeleteNotSuccess() {
		String argument = "1234";
		Patient p=new Patient();
		when(patientRepo.findByEnterpriseId(argument)).thenReturn(p);
		when(patientRepo.deleteByEnterpriseId(argument)).thenReturn(0l);
		assertEquals(false, patientServiceImpl.delete(argument));
	}
	
	@Test
	public void testDeleteSuccessWithPatientMemberRecord() {
		String argument = "1234";
		Patient p=new Patient();
		List<PatientMemberRecord> memberRecords = new ArrayList<PatientMemberRecord>();
		memberRecords.add(new PatientMemberRecord());
		when(patientRepo.findByEnterpriseId(argument)).thenReturn(p);
		when(patientRepo.deleteByEnterpriseId(argument)).thenReturn(1l);
		assertEquals(true, patientServiceImpl.delete(argument));
	}
	
	@Test
	public void testDeleteSuccessWithAddress() {
		String argument = "1234";
		Patient p=new Patient();
		List<PatientMemberRecord> memberRecords = new ArrayList<PatientMemberRecord>();
		PatientMemberRecord re=new PatientMemberRecord();
		List<Address> address = new ArrayList<Address>();
		address.add(new Address());
		re.setAddress(address);
		memberRecords.add(re);
		when(patientRepo.findByEnterpriseId(argument)).thenReturn(p);
		when(patientRepo.deleteByEnterpriseId(argument)).thenReturn(1l);
		assertEquals(true, patientServiceImpl.delete(argument));
	}
	
	@Test(expected = CpException.class)
	public void testDeleteException() {
		String argument = "1234";
		Patient p=new Patient();
		List<PatientMemberRecord> memberRecords = new ArrayList<PatientMemberRecord>();
		PatientMemberRecord re=new PatientMemberRecord();
		List<Address> address = new ArrayList<Address>();
		address.add(new Address());
		re.setAddress(address);
		memberRecords.add(re);
		when(patientRepo.findByEnterpriseId(argument)).thenReturn(p);
		when(patientRepo.deleteByEnterpriseId(argument)).thenThrow(NullPointerException.class);
		patientServiceImpl.delete(argument);
	}

}
