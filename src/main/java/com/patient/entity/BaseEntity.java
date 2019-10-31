package com.patient.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
	

	@Column(name="createdby")
	@CreatedBy
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="createddt")
	@CreatedDate
	private Date createdDt;

	@Column(name="updatedby")
	@LastModifiedBy
	private String updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updateddt")
	@LastModifiedDate
	private Date updatedDt;
	
	

}
