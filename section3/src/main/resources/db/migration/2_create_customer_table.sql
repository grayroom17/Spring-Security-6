--liquibase formatted sql

--changeset grayroom:1
create table if not exists customer
(
    id       serial primary key,
    email    varchar(45)  not null,
    password varchar(200) not null,
    role     varchar(45)  not null
);

--changeset grayroom:2
insert into customer (email, password, role)
values ('johndoe@example.com', '12345', 'ADMIN')