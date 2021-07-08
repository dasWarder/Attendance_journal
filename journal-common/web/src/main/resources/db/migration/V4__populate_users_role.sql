DELETE FROM users;
DELETE FROM authorities;


ALTER SEQUENCE user_seq RESTART WITH 100000;


INSERT INTO authorities (authority) VALUES
    ('USER'),
    ('ADMIN');

INSERT INTO users(role_id, username, password, enabled) VALUES
(100000, 'alex@gmail.com', '12345', true),
(100001, 'petr@gmail.com', '12345', true);