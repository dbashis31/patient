package com.patient.dto;
/**
 * This class is DTO class of Patient 
 */
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class PatientDTO extends BaseDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String enterpriseId; // global identifier
	private List<PatientMemberRecordDTO> memberRecords;
	 
}
