--liquibase formatted sql

--changeset grayroom:1
create table users
(
    id       serial primary key,
    username varchar(50)  not null,
    password varchar(500) not null,
    enabled  boolean      not null
);

--changeset grayroom:2
create table authorities
(
    id        serial primary key,
    username  varchar(50) not null,
    authority varchar(50) not null
);

--changeset grayroom:3
insert into users (username, password, enabled)
values ('happy', '12345', true);

insert into authorities (username, authority)
values ('happy', 'write')