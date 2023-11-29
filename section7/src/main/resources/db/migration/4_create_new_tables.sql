--liquibase formatted sql

--changeset grayroom:1
create table if not exists customer
(
    id            serial primary key,
    name          varchar(100) not null,
    email         varchar(100) not null,
    mobile_number varchar(20)  not null,
    pwd           varchar(500) not null,
    role          varchar(100) not null,
    created_at    date default null
);

--changeset grayroom:2
insert into customer (name, email, mobile_number, pwd, role, created_at)
values ('happy', 'happy@example.com', '9876548337', '$2y$12$orrbknfwur8ug4mlzh5foeui.//1mkd.rsoajmbyktsupvy.x/vb2',
        'admin', now());

--changeset grayroom:3
create table if not exists account
(
    id             serial primary key,
    customer_id    int references customer (id) on delete cascade,
    account_type   varchar(100) not null,
    branch_address varchar(200) not null,
    created_at     date default null
);

--changeset grayroom:4
insert into account (customer_id, account_type, branch_address, created_at)
values (1, 'savings', '123 main street, new york', now());

--changeset grayroom:5
create table if not exists transaction
(
    uuid                uuid primary key,
    account_id          int          not null references account (id) on delete cascade,
    customer_id         int          not null references customer (id) on delete cascade,
    transaction_date    date         not null,
    transaction_summary varchar(200) not null,
    transaction_type    varchar(100) not null,
    transaction_amt     int          not null,
    closing_balance     int          not null,
    created_at          date default null
);

--changeset grayroom:6
insert into transaction (uuid, account_id, customer_id, transaction_date, transaction_summary, transaction_type,
                         transaction_amt, closing_balance, created_at)
values (gen_random_uuid(), 1, 1, now() - interval '7 day', 'coffee shop', 'withdrawal', 30, 34500,
        now() - interval '7 day'),
       (gen_random_uuid(), 1, 1, now() - interval '6 day', 'uber', 'withdrawal', 100, 34400, now() - interval '6 day'),
       (gen_random_uuid(), 1, 1, now() - interval '5 day', 'self deposit', 'deposit', 500, 34900,
        now() - interval '5 day'),
       (gen_random_uuid(), 1, 1, now() - interval '4 day', 'ebay', 'withdrawal', 600, 34300, now() - interval '4 day'),
       (gen_random_uuid(), 1, 1, now() - interval '2 day', 'onlinetransfer', 'deposit', 700, 35000,
        now() - interval '2 day'),
       (gen_random_uuid(), 1, 1, now() - interval '1 day', 'amazon.com', 'withdrawal', 100, 34900,
        now() - interval '1 day');


--changeset grayroom:7
create table if not exists loan
(
    id                 serial primary key,
    customer_id        int          not null references customer (id) on delete cascade,
    started_at         date         not null,
    loan_type          varchar(100) not null,
    total_loan         int          not null,
    amount_paid        int          not null,
    outstanding_amount int          not null,
    created_at         date default null
);

--changeset grayroom:8
insert into loan (customer_id, started_at, loan_type, total_loan, amount_paid, outstanding_amount, created_at)
values (1, '2020-10-13', 'home', 200000, 50000, 150000, '2020-10-13'),
       (1, '2020-06-06', 'vehicle', 40000, 10000, 30000, '2020-06-06'),
       (1, '2018-02-14', 'home', 50000, 10000, 40000, '2018-02-14'),
       (1, '2018-02-14', 'personal', 10000, 3500, 6500, '2018-02-14');

--changeset grayroom:9
create table if not exists card
(
    id               serial primary key,
    card_number      varchar(100) not null,
    customer_id      int          not null references customer (id) on delete cascade,
    card_type        varchar(100) not null,
    total_limit      int          not null,
    amount_used      int          not null,
    available_amount int          not null,
    created_at       date default null
);

--changeset grayroom:10
insert into card (card_number, customer_id, card_type, total_limit, amount_used, available_amount, created_at)
values ('4565xxxx4656', 1, 'credit', 10000, 500, 9500, now()),
       ('3455xxxx8673', 1, 'credit', 7500, 600, 6900, now()),
       ('2359xxxx9346', 1, 'credit', 20000, 4000, 16000, now());

--changeset grayroom:11
create table if not exists notice_detail
(
    id             serial primary key,
    notice_summary varchar(200) not null,
    notice_details varchar(500) not null,
    notic_beg_dt   date         not null,
    notic_end_dt   date default null,
    created_at     date default null,
    updated_at     date default null
);

--changeset grayroom:12
insert into notice_detail (notice_summary, notice_details, notic_beg_dt, notic_end_dt, created_at, updated_at)
values ('home loan interest rates reduced',
        'home loan interest rates are reduced as per the goverment guidelines. the updated rates will be effective immediately',
        now() - interval '30 day', now() + interval '30 day', now(), null),
       ('net banking offers',
        'customers who will opt for internet banking while opening a saving account will get a $50 amazon voucher',
        now() - interval '30 day', now() + interval '30 day', now(), null),
       ('mobile app downtime',
        'the mobile application of the eazybank will be down from 2am-5am on 12/05/2020 due to maintenance activities',
        now() - interval '30 day', now() + interval '30 day', now(), null),
       ('e auction notice',
        'there will be a e-auction on 12/08/2020 on the bank website for all the stubborn arrears.interested parties can participate in the e-auction',
        now() - interval '30 day', now() + interval '30 day', now(), null),
       ('launch of millennia cards',
        'millennia credit cards are launched for the premium customers of eazybank. with these cards, you will get 5% cashback for each purchase',
        now() - interval '30 day', now() + interval '30 day', now(), null),
       ('covid-19 insurance',
        'eazybank launched an insurance policy which will cover covid-19 expenses. please reach out to the branch for more details',
        now() - interval '30 day', now() + interval '30 day', now(), null);

--changeset grayroom:13
create table if not exists contact_message
(
    id            varchar(50)   not null primary key,
    contact_name  varchar(50)   not null,
    contact_email varchar(100)  not null,
    subject       varchar(500)  not null,
    message       varchar(2000) not null,
    created_at    date default null
);