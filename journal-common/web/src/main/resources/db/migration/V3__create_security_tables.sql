DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS user_seq;

CREATE SEQUENCE user_seq START WITH 100000;

CREATE TABLE authorities (
                             id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('user_seq'),
                             authority VARCHAR(160) NOT NULL
);

CREATE UNIQUE INDEX user_authority ON authorities(id, authority);

CREATE TABLE users (
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('user_seq'),
    role_id BIGINT NOT NULL,
    username varchar(50) NOT NULL,
    password  VARCHAR(160) NOT NULL,
    enabled BOOLEAN NOT NULL,
    FOREIGN KEY (role_id) REFERENCES authorities(id)
);

CREATE UNIQUE INDEX id_username ON users(id, username);


