DROP TABLE IF EXISTS refresh_token;
DROP SEQUENCE IF EXISTS token_seq;

CREATE SEQUENCE token_seq START WITH 1;

CREATE TABLE refresh_token (
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('token_seq'),
    subject VARCHAR(156) NOT NULL,
    token VARCHAR(256) NOT NULL,
    expiryDate TIMESTAMP NOT NULL
);