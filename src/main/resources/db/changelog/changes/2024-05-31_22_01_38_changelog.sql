-- liquibase formatted sql

-- changeset ismail:1717185699780-1
CREATE TABLE public."user" (id VARCHAR(255) NOT NULL, email VARCHAR(255) NOT NULL, firstname VARCHAR(255) NOT NULL, lastname VARCHAR(255), role VARCHAR(255) NOT NULL, CONSTRAINT "userPK" PRIMARY KEY (id));

-- changeset ismail:1717185699780-2
ALTER TABLE public."user" ADD CONSTRAINT UC_USEREMAIL_COL UNIQUE (email);
