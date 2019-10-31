package com.patient.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.patient.constrants.ApplicationConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "address", schema = ApplicationConstants.DB_SCHEMA)
public class Address extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
  // AS Database table column name are different thats why using @column annotation
  @Column(name="addressline1")
  private String addressLine1;
  @Column(name="addressLine2")
  private String addressLine2;
  @Column(name="city")
  private String city;
  @Column(name="state")
  private String state;
  @Column(name="zipode")
  private String zipCode;
  @Column(name="patientmemberrecordid")
  private String patientMemberRecordid;
  //TODO : Master table needs to be created 
  @Column(name="addresstype")
  private String addresstype;
  @ManyToOne
  private PatientMemberRecord patientMemberRecord;
}
