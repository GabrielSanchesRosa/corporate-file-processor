CREATE TABLE customer (
    id       UUID         NOT NULL,
    name     VARCHAR(255) NOT NULL,
    document VARCHAR(18)  NOT NULL,
    email    VARCHAR(255),
    PRIMARY KEY (id)
);