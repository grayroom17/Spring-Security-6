--liquibase formatted sql

--changeset grayroom:1
create table if not exists authorities
(
    id          serial primary key,
    customer_id int         not null references customer (id),
    name        varchar(50) not null
);

insert into authorities (customer_id, name)
values (1, 'viewaccount');

insert into authorities (customer_id, name)
values (1, 'viewcards');

insert into authorities (customer_id, name)
values (1, 'viewloans');

insert into authorities (customer_id, name)
values (1, 'viewbalance');