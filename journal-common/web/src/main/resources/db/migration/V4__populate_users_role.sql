DELETE FROM authorities;
DELETE FROM users;

ALTER SEQUENCE user_seq RESTART WITH 100000;

INSERT INTO users(username, password, enabled) VALUES
    ('alex@gmail.com', '12345', true),
    ('petr@gmail.com', '12345', true);

INSERT INTO authorities (user_id, authority) VALUES
    (100000, 'USER'),
    (100001, 'ADMIN');