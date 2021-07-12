DELETE FROM users;
DELETE FROM authorities;


ALTER SEQUENCE user_seq RESTART WITH 100000;


INSERT INTO authorities (id, authority) VALUES
    (100000,'USER'),
    (100001, 'ADMIN');

SELECT setval('user_seq', max(id))
FROM authorities;

INSERT INTO users(id, role_id, username, password, enabled) VALUES
    (100002, 100000, 'alex@gmail.com', '$2a$10$o53h1x/Y2shcaj0XTM71ju3G06I9EVxoH1wcNwYpY4cf3vvscNsiu', true),
    (100003, 100001, 'petr@gmail.com', '$2a$10$o53h1x/Y2shcaj0XTM71ju3G06I9EVxoH1wcNwYpY4cf3vvscNsiu', true);

SELECT setval('user_seq', max(id))
FROM users;