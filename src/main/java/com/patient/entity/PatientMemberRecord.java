package com.patient.entity;
/**
 * This class is entity class of patientmemberrecord  
 */
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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
	@Id
	@org.hibernate.annotations.Type(type="pg-uuid")
	@Column(name = "id")
	private UUID id;
	// AS Database table column name are different thats why using @column annotation
	@Column(name="source")
	private String source;
	@org.hibernate.annotations.Type(type="pg-uuid")
	@Column(name="patientid")
	private UUID patientId;
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
	
	
	
}
