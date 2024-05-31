-- liquibase formatted sql

-- changeset ismail:1717185929825-1
ALTER TABLE public."user" ADD password VARCHAR(255) NOT NULL;

