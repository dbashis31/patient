CREATE TABLE patient.address (
	id uuid NULL DEFAULT uuid_generate_v4(),
	addressline1 varchar(100) NULL,
	addressline2 varchar(100) NULL,
	city varchar(50) NULL,
	state varchar(50) NULL,
	zipode varchar(50) NULL,
	patientmemberrecordid uuid NULL,
	CONSTRAINT address_pk PRIMARY KEY (id),
	CONSTRAINT address_fk FOREIGN KEY (patientmemberrecordid) REFERENCES patient.patientmemberrecord(id)
);

ALTER TABLE patient.address ADD addresstypeid uuid NULL;
ALTER TABLE patient.address ADD CONSTRAINT address_type_fk FOREIGN KEY (addresstypeid) REFERENCES patient.addresstype(id);


CREATE TABLE patient.addresstype (
	id uuid NULL DEFAULT uuid_generate_v4(),
	addresstype varchar(50) NULL,
	CONSTRAINT addresstype_pk PRIMARY KEY (id)
);
