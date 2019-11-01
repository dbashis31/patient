CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
-- Drop table

-- DROP TABLE patient.patient;

CREATE TABLE patient.patient (
	id uuid NOT NULL DEFAULT uuid_generate_v4(),
	enterpriseid varchar(9) NULL,
	createddt date NULL,
	createdby varchar(50) NULL,
	updatedby varchar(50) NULL,
	updateddt date NULL,
	CONSTRAINT patient_pk PRIMARY KEY (id)
);
ALTER TABLE patient.patient ADD CONSTRAINT patient_un UNIQUE (enterpriseid);

-- Drop table

-- DROP TABLE patient.patientmemberrecord;

CREATE TABLE patient.patientmemberrecord (
	"source" varchar(10) NULL,
	id uuid NOT NULL DEFAULT uuid_generate_v4(),
	patientid uuid NULL,
	medicalrecordnumber varchar(9) NULL,
	firstname varchar(50) NULL,
	lastname varchar(50) NULL,
	socialsecuritynumber varchar(10) NULL,
	createddt date NULL,
	createdby varchar(50) NULL,
	updatedby varchar(50) NULL,
	updateddt date NULL,
	CONSTRAINT patientmemberrecord_pk PRIMARY KEY (id),
	CONSTRAINT patientmemberrecord_fk FOREIGN KEY (patientid) REFERENCES patient.patient(id)
);

-- Drop table

-- DROP TABLE patient.address;

CREATE TABLE patient.address (
	id uuid NOT NULL DEFAULT uuid_generate_v4(),
	addressline1 varchar(100) NULL,
	addressline2 varchar(100) NULL,
	city varchar(50) NULL,
	state varchar(50) NULL,
	zipode varchar(50) NULL,
	patientmemberrecordid uuid NULL,
	addresstypeid varchar NULL,
	updateddt date NULL,
	createddt date NULL,
	createdby varchar(50) NULL,
	updatedby varchar(50) NULL,
	CONSTRAINT address_pk PRIMARY KEY (id),
	CONSTRAINT address_fk FOREIGN KEY (patientmemberrecordid) REFERENCES patient.patientmemberrecord(id)
);
