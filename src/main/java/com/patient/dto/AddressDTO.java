package com.patient.dto;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class AddressDTO extends BaseDTO implements Serializable {
 	  private static final long serialVersionUID = 1L;
	  private String addressLine1;
	  private String addressLine2;
	  private String city;
	  private String state;
	  private String zipCode;
	  private String addresstype;
}
