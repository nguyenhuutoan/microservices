CREATE TYPE payment_status AS ENUM ('COMPLETED', 'CANCELLED', 'FAILED');
--CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE payments
(
    id uuid NOT NULL,
    user_id uuid NOT NULL,
    order_id uuid NOT NULL,
    price numeric(10,2) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    status payment_status NOT NULL,
    CONSTRAINT payments_pkey PRIMARY KEY (id)
);

CREATE TABLE credit_entry
(
    id uuid NOT NULL,
    user_id uuid NOT NULL,
    total_credit_amount numeric(10,2) NOT NULL,
    CONSTRAINT credit_entry_pkey PRIMARY KEY (id)
);

CREATE TYPE transaction_type AS ENUM ('DEBIT', 'CREDIT');
CREATE TABLE credit_history
(
    id uuid NOT NULL,
    user_id uuid NOT NULL,
    amount numeric(10,2) NOT NULL,
    type transaction_type NOT NULL,
    CONSTRAINT credit_history_pkey PRIMARY KEY (id)
);

CREATE TYPE outbox_status AS ENUM ('STARTED', 'COMPLETED', 'FAILED');
CREATE TABLE order_outbox
(
    id uuid NOT NULL,
    saga_id uuid NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    processed_at TIMESTAMP WITH TIME ZONE,
    type character(255) NOT NULL,
    payload json NOT NULL,
    outbox_status outbox_status NOT NULL,
    payment_status payment_status NOT NULL,
    version integer NOT NULL,
    CONSTRAINT order_outbox_pkey PRIMARY KEY (id)
);