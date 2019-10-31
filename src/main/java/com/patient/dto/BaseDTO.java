package com.patient.dto;

import java.util.Date;
import javax.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class BaseDTO {
	private String id; 
	private String createdBy;
	private Date createdDt;
	private String updatedBy;
	private Date updatedDt;
}
