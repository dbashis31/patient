CREATE TABLE patient.address (
	id uuid NULL DEFAULT uuid_generate_v4(),
	addressline1 varchar(100) NULL,
	addressline2 varchar(100) NULL,
	city varchar(50) NULL,
	state varchar(50) NULL,
	zipode varchar(50) NULL,
	patientmemberrecordid uuid NULL,
	CONSTRAINT address_pk PRIMARY KEY (id)
	
);

ALTER TABLE patient.address ADD addresstypeid uuid NULL;
ALTER TABLE patient.address ADD CONSTRAINT address_fk FOREIGN KEY (patientmemberrecordid) REFERENCES patient.patientmemberrecord(id);
ALTER TABLE patient.address  ADD createddt date NULL;
ALTER TABLE patient.address  ADD createdby varchar(50) NULL;
ALTER TABLE patient.address  ADD updatedby varchar(50) NULL;
ALTER TABLE patient.address  ADD updateddt date NULL;