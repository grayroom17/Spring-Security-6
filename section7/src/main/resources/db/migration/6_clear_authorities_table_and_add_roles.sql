--liquibase formatted sql

--changeset grayroom:1
delete
from authorities;

insert into authorities (customer_id, name)
values (1, 'ROLE_USER');

insert into authorities (customer_id, name)
values (1, 'ROLE_ADMIN');