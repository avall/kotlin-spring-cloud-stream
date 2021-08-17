CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE IF NOT EXISTS attachment (
        id varchar(255) NOT NULL ,
        contenttype varchar(255) NOT NULL,
        description varchar(255),
        url varchar(255) NOT NULL,
        is_private bool NOT NULL,
        PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS cousine (
                         id uuid NOT NULL DEFAULT uuid_generate_v4(),
                         name VARCHAR(100) NOT NULL,
                         UNIQUE (name),
                         PRIMARY KEY (id)
);

INSERT INTO cousine (name) VALUES ('Chinese');
INSERT INTO cousine (name) VALUES ('Pizza');
INSERT INTO cousine (name) VALUES ('Sushi');
INSERT INTO cousine (name) VALUES ('Vietnamese');

CREATE TABLE IF NOT EXISTS store (
                       id uuid NOT NULL DEFAULT uuid_generate_v4(),
                       name VARCHAR(100) NOT NULL,
                       address VARCHAR(300) NOT NULL,
                       cousine_id uuid NOT NULL,
                       UNIQUE(name),
                       PRIMARY KEY (id),
                       FOREIGN KEY (cousine_id) REFERENCES cousine (id)
);


INSERT INTO store (name, address, cousine_id)
VALUES ('Hai Shang', '2991 Pembina Hwy, Winnipeg, Manitoba R3T 2H5, Canada', (SELECT id FROM cousine WHERE name = 'Chinese'));


INSERT INTO store (name, address, cousine_id)
VALUES ('Za Pizza Bistro', 'E-1220 St Mary s Rd, Winnipeg, Manitoba R2M 3V6, Canada', (SELECT id FROM cousine WHERE name = 'Pizza'));

CREATE TABLE IF NOT EXISTS product (
                         id uuid NOT NULL DEFAULT uuid_generate_v4(),
                         name VARCHAR(100) NOT NULL,
                         description VARCHAR(300) NOT NULL,
                         price DECIMAL(19, 2) NOT NULL,
                         store_id uuid NOT NULL,
                         UNIQUE (name, store_id),
                         PRIMARY KEY (id),
                         FOREIGN KEY (store_id) REFERENCES store (id)
);


INSERT INTO product (name, description, price, store_id)
VALUES ('Shrimp Tempura', 'Fresh shrimp battered and deep fried until golden brown', 10.95, (SELECT id FROM store WHERE name = 'Hai Shang'));

CREATE TABLE IF NOT EXISTS customer (
                          id uuid NOT NULL DEFAULT uuid_generate_v4(),
                          name VARCHAR(50) NOT NULL,
                          email VARCHAR(100) NOT NULL,
                          address VARCHAR(200) NOT NULL,
                          password VARCHAR(100) NOT NULL,
                          UNIQUE (email),
                          PRIMARY KEY (id)
);

INSERT INTO customer (name, email, address, password)
VALUES ('alex vall', 'avall@spring.com', 'addressfake', '12345');

CREATE TABLE IF NOT EXISTS orders (
                        id uuid NOT NULL DEFAULT uuid_generate_v4(),
                        customer_id uuid NOT NULL,
                        store_id uuid NOT NULL,
                        total DECIMAL(19, 2) NOT NULL,
                        status VARCHAR(100) NOT NULL,
                        created_at TIMESTAMP NOT NULL,
                        updated_at TIMESTAMP NOT NULL,
                        PRIMARY KEY (id),
                        FOREIGN KEY (customer_id) REFERENCES customer (id),
                        FOREIGN KEY (store_id) REFERENCES store (id)
);

CREATE TABLE IF NOT EXISTS order_item (
                            id uuid NOT NULL DEFAULT uuid_generate_v4(),
                            order_id uuid NOT NULL,
                            product_id uuid not null,
                            quantity uuid NOT NULL,
                            price DECIMAL(19, 2) NOT NULL,
                            total DECIMAL(19, 2) NOT NULL,
                            PRIMARY KEY (id),
                            FOREIGN KEY (order_id) REFERENCES orders (id),
                            FOREIGN KEY (product_id) REFERENCES product (id)
);
