-- create sequence customer_id_seq start with 1000 increment by 1;
-- create sequence item_id_seq start with 1000 increment by 1;
-- create sequence item_group_id_seq start with 1000 increment by 1;
-- create sequence order_id_seq start with 1000 increment by 1;

create table customer
(
    id        integer generated always as identity
        primary key,
    firstname varchar(255)                                                 not null,
    lastname  varchar(255)                                                 not null,
    email     varchar(255)                                                 not null,
    password  varchar(255)                                                 not null,
    address   varchar(255)                                                 not null,
    phone     varchar(255)                                                 not null
);

create table item
(
    id                integer generated always as identity
        primary key,
    name              varchar(255)     not null,
    description       varchar(255)     not null,
    price             double precision not null,
    stock_amount      integer          not null,
    urgency_indicator varchar(255)     not null
);

create table orders
(
    id          integer generated always as identity
        primary key,
    customer_id integer not null,
    order_date  date    not null
);

create table item_group
(
    id                  integer generated always as identity
        primary key,
    item_id             integer          not null
        constraint item_group_item_id_foreign
            references item,
    ordered_item_amount integer          not null,
    shipping_date       date             not null,
    fk_order_id         integer
        constraint item_group_order_id_foreign
            references orders,
    item_price          double precision not null
);