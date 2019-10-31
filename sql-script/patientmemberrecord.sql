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
	CONSTRAINT patientmemberrecord_pk PRIMARY KEY (id),
	CONSTRAINT patientmemberrecord_fk FOREIGN KEY (patientid) REFERENCES patient.patient(id)
);

-- Permissions

ALTER TABLE patient.patientmemberrecord OWNER TO postgres;
GRANT ALL ON TABLE patient.patientmemberrecord TO postgres;

ALTER TABLE patient.patientmemberrecord ADD createddt date NULL;
ALTER TABLE patient.patientmemberrecord ADD createdby varchar(50) NULL;
ALTER TABLE patient.patientmemberrecord ADD updatedby varchar(50) NULL;
ALTER TABLE patient.patientmemberrecord ADD updateddt date NULL;
