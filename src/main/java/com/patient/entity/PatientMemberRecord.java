package com.patient.entity;

import java.util.List;

import lombok.Data;

@Data
public class PatientMemberRecord {

  private String source;
  private String medicalRecordNumber;
  private String firstName;
  private String lastName;
  private String socialSecurityNumber;
  private List<Address> address;
}
