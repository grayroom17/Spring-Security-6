--liquibase formatted sql

--changeset grayroom:1
drop table if exists users cascade;

--changeset grayroom:2
drop table if exists authorities cascade;

--changeset grayroom:3
drop table if exists customer cascade;
