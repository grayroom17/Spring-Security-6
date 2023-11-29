--liquibase formatted sql

--changeset grayroom:1
delete
from authorities;

insert into authorities (customer_id, name)
values (1, 'role_user');

insert into authorities (customer_id, name)
values (1, 'role_admin');