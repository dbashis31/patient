package com.patient.entity;
/**
 * This class is entity class of patientmemberrecord  
 */
import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.patient.constrants.ApplicationConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "patientmemberrecord", schema = ApplicationConstants.DB_SCHEMA)
public class PatientMemberRecord extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	// AS Database table column name are different thats why using @column annotation
	@Column(name="source")
	private String source;
	@Column(name="patientid")
	private String patientId;
	@Column(name="medicalrecordnumber")
	private String medicalRecordNumber;
	@Column(name="firstname")
    private String firstName;
	@Column(name="lastname")
	private String lastName;
	@Column(name="socialsecuritynumber")
    private String socialSecurityNumber;
	@JoinColumn(name="patientmemberrecordid")
	@OneToMany(fetch = FetchType.LAZY)
	private List<Address> address;
	@ManyToOne
    private Patient patient;
	
}
