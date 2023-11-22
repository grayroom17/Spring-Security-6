create table users
(
    id       serial primary key,
    username varchar(50)  not null,
    password varchar(500) not null,
    enabled  boolean      not null
);

create table authorities
(
    id        serial primary key,
    username  varchar(50) not null,
    authority varchar(50) not null
);

insert into users (username, password, enabled)
values ('happy', '12345', true);

insert into authorities (username, authority)
values ('happy', 'write')