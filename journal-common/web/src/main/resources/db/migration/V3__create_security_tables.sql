DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS authorities;
DROP SEQUENCE IF EXISTS user_seq;

CREATE SEQUENCE user_seq START WITH 100000;

CREATE TABLE users (
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('user_seq'),
    username varchar(50) NOT NULL,
    password  VARCHAR(50) NOT NULL,
    enabled BOOLEAN NOT NULL
);

CREATE UNIQUE INDEX id_username ON users(id, username);


CREATE TABLE authorities (
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('user_seq'),
    user_id BIGINT NOT NULL,
    authority VARCHAR(50) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE UNIQUE INDEX user_authority ON authorities(user_id, authority);
