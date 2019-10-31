package com.patient.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.patient.constrants.*;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "patient", schema = ApplicationConstants.DB_SCHEMA)
public class Patient extends AuditEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	 private String enterpriseId; // global identifier
	 
	 private List<PatientMemberRecord> memberRecords;

}
