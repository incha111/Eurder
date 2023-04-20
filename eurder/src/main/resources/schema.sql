CREATE SEQUENCE customer_id_seq
    MINVALUE 1
    MAXVALUE 9999999
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE item_id_seq
    MINVALUE 1
    MAXVALUE 9999999
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE item_group_id_seq
    MINVALUE 1
    MAXVALUE 9999999
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE order_id_seq
    MINVALUE 1
    MAXVALUE 9999999
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE "item_group"(
    "id" INTEGER NOT NULL,
    "item_id" INTEGER NOT NULL,
    "ordered_item_amount" INTEGER NOT NULL,
    "shipping_date" DATE NOT NULL
);
ALTER TABLE
    "item_group" ADD PRIMARY KEY("id");
ALTER TABLE
    "item_group" ADD CONSTRAINT "item_group_item_id_foreign" FOREIGN KEY("item_id") REFERENCES "item"("id");
ALTER TABLE
    "item_group" ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY;
ALTER SEQUENCE "order".item_group_id_seq
    OWNED BY item_group.id;

CREATE TABLE "customer"(
    "id" INTEGER NOT NULL DEFAULT nextval('"order".customer_id_seq'),
    "firstname" VARCHAR(255) NOT NULL,
    "lastname" VARCHAR(255) NOT NULL,
    "email" VARCHAR(255) NOT NULL,
    "address" VARCHAR(255) NOT NULL,
    "phone" VARCHAR(255) NOT NULL
);
ALTER TABLE
    "customer" ADD PRIMARY KEY("id");
ALTER SEQUENCE
    "order".customer_id_seq
    OWNED BY customer.id;

CREATE TABLE "item"(
    "id" INTEGER NOT NULL,
    "name" VARCHAR(255) NOT NULL,
    "description" VARCHAR(255) NOT NULL,
    "password" VARCHAR(255) NOT NULL,
    "price" DOUBLE PRECISION NOT NULL,
    "stock_amount" INTEGER NOT NULL,
    "urgency_indicator" VARCHAR(255) NOT NULL
);
ALTER TABLE
    "item" ADD PRIMARY KEY("id");
ALTER TABLE
    "item" ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY;
ALTER SEQUENCE "order".item_id_seq OWNED BY item.id;

CREATE TABLE "order"(
    "id" INTEGER NOT NULL,
    "item_group_id" INTEGER NOT NULL,
    "customer_id" INTEGER NOT NULL,
    "order_date" DATE NOT NULL
);
ALTER TABLE
    "order" ADD PRIMARY KEY("id");
ALTER TABLE
    "order" ADD CONSTRAINT "order_item_group_id_foreign" FOREIGN KEY("item_group_id") REFERENCES "item_group"("id");
ALTER TABLE
    "order" ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY;
ALTER SEQUENCE
    "order".order_id_seq OWNED BY order.id;