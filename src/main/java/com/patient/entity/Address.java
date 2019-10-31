package com.patient.entity;

import lombok.Data;

@Data
public class Address {

  private Long  addressId;
  private String addressLine1;
  private String addressLine2;
  private String city;
  private String state;
  private String zipCode;
}
