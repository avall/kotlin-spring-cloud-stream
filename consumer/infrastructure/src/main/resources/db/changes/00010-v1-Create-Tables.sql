CREATE TABLE public.attachment (
	id varchar(255) NOT NULL ,
	contenttype varchar(255) NOT NULL,
	description varchar(255),
	url varchar(255) NOT NULL,
	is_private bool NOT NULL
	CONSTRAINT attachment_pk PRIMARY KEY (id)
);

CREATE TABLE public.cousine (
	id varchar(255) NOT NULL ,
	name varchar(255) NOT NULL
	CONSTRAINT cousine_pk PRIMARY KEY (id)
);

CREATE TABLE public.customer (
	id varchar(255) NOT NULL ,
	name varchar(255) NOT NULL,
	email varchar(255) NOT NULL,
	address varchar(255) NOT NULL,
	password varchar(255) NOT NULL
	CONSTRAINT customer_pk PRIMARY KEY (id)
);

CREATE TABLE public.order (
	id varchar(255) NOT NULL ,
	customer_id varchar(255) NOT NULL,
	store_id varchar(255) NOT NULL,
	total numeric(19,2) NOT NULL,
	status varchar(255) NOT NULL,
	created_at timestamp NOT NULL,
	updated_at timestamp NOT NULL
	CONSTRAINT order_pk PRIMARY KEY (id)
);

CREATE TABLE public.order_item (
	id varchar(255) NOT NULL ,
	order_id varchar(255) NOT NULL,
	product_id varchar(255) NOT NULL,
	price numeric(19,2) NOT NULL,
	quantity int NOT NULL,
	total numeric(19,2) NOT NULL,
	created_at timestamp NOT NULL,
	updated_at timestamp NOT NULL

	CONSTRAINT order_pk PRIMARY KEY (id)
);

CREATE TABLE public.product (
	id varchar(255) NOT NULL ,
	name varchar(255) NOT NULL,
	description varchar(255) NOT NULL,
	price numeric(19,2) NOT NULL,
	store_id varchar(255) NOT NULL

	CONSTRAINT order_pk PRIMARY KEY (id)
);

CREATE TABLE public.store (
	id varchar(255) NOT NULL ,
	name varchar(255) NOT NULL,
	address varchar(255) NOT NULL,
	price numeric(19,2) NOT NULL,
	cousine_id varchar(255) NOT NULL

	CONSTRAINT order_pk PRIMARY KEY (id)
);




