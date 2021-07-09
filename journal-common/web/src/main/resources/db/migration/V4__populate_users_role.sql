DELETE FROM users;
DELETE FROM authorities;


ALTER SEQUENCE user_seq RESTART WITH 100000;


INSERT INTO authorities (authority) VALUES
    ('USER'),
    ('ADMIN');

INSERT INTO users(role_id, username, password, enabled) VALUES
(100000, 'alex@gmail.com', '$2a$10$o53h1x/Y2shcaj0XTM71ju3G06I9EVxoH1wcNwYpY4cf3vvscNsiu', true),
(100001, 'petr@gmail.com', '$2a$10$o53h1x/Y2shcaj0XTM71ju3G06I9EVxoH1wcNwYpY4cf3vvscNsiu', true);