package com.patient.dto;
/**
 * This class is DTO class of patientmemberrecord  
 */
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class PatientMemberRecordDTO extends BaseDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String source;
	private String patientId;
	private String medicalRecordNumber;
	private String firstName;
	private String lastName;
	private String socialSecurityNumber;
   	private List<AddressDTO> address;
	
}
