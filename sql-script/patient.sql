CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE patient.patient (
	id uuid NOT NULL DEFAULT uuid_generate_v4(),
	enterpriseid varchar(9) NULL, --  global identifier
	CONSTRAINT patient_pk PRIMARY KEY (id)
);

-- Column comments

COMMENT ON COLUMN patient.patient.enterpriseid IS ' global identifier';

-- Constraint comments

COMMENT ON CONSTRAINT patient_pk ON patient.patient IS 'Primary key';

-- Permissions

ALTER TABLE patient.patient OWNER TO postgres;
GRANT ALL ON TABLE patient.patient TO postgres;

ALTER TABLE patient.patient  ADD createddt date NULL;
ALTER TABLE patient.patient  ADD createdby varchar(50) NULL;
ALTER TABLE patient.patient  ADD updatedby varchar(50) NULL;
ALTER TABLE patient.patient  ADD updateddt date NULL;