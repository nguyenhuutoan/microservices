CREATE TYPE order_status AS ENUM ('PENDING', 'PAID', 'APPROVED', 'CANCELLED', 'CANCELLING');
CREATE TYPE saga_status AS ENUM ('STARTED', 'FAILED', 'SUCCEEDED', 'PROCESSING', 'COMPENSATING', 'COMPENSATED');
CREATE TYPE outbox_status AS ENUM ('STARTED', 'COMPLETED', 'FAILED');

--CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE orders
(
    id uuid NOT NULL,
    user_id uuid NOT NULL,
    tracking_id uuid NOT NULL,
    price numeric(10,2) NOT NULL,
    order_status order_status NOT NULL,
    failure_messages character varying(1000),
    CONSTRAINT orders_pkey PRIMARY KEY (id)
);

CREATE TABLE order_items
(
    id bigint NOT NULL,
    order_id uuid NOT NULL,
    product_id uuid NOT NULL,
    price numeric(10,2) NOT NULL,
    quantity integer NOT NULL,
    sub_total numeric(10,2) NOT NULL,
    CONSTRAINT order_items_pkey PRIMARY KEY (id, order_id),
    FOREIGN KEY(order_id) REFERENCES orders
);

CREATE TABLE users
(
    id uuid NOT NULL,
    username character(255) NOT NULL,
    first_name character(255) NOT NULL,
    last_name character(255) NOT NULL,
    user_type INTEGER DEFAULT 0,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);

CREATE TABLE payment_outbox
(
    id uuid NOT NULL,
    saga_id uuid NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    processed_at TIMESTAMP WITH TIME ZONE,
    type character(255),
    payload json NOT NULL,
    outbox_status outbox_status NOT NULL,
    saga_status saga_status NOT NULL,
    order_status order_status NOT NULL,
    version integer NOT NULL,
    CONSTRAINT payment_outbox_pkey PRIMARY KEY (id)
);


