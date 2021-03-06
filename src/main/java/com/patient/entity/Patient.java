package com.patient.entity;
/**
 * This class is entity class of Patient 
 */
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import com.patient.constrants.*;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Getter
@Setter
@Table(name = "patient", schema = ApplicationConstants.DB_SCHEMA)
public class Patient extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	 @Id
	 @GeneratedValue(generator = "UUID")
	 @GenericGenerator(
			name = "UUID",
			strategy = "org.hibernate.id.UUIDGenerator"
		)
	 @Column(name = "id")
	 private UUID id;
	 
	// AS Database table column name are different thats why using @column annotation 
	 @Column(name="enterpriseid")
	 private String enterpriseId; // global identifier
	 
	 @JoinColumn(name="patientid")
	 @OneToMany(fetch = FetchType.LAZY)
	 private List<PatientMemberRecord> memberRecords;
	 
	
	 
}
