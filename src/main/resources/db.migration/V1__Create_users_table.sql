CREATE TABLE users (
                       id UUID PRIMARY KEY,
                       login VARCHAR(255),
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(255)
);