
SELECT setval('user_seq', max(id))
FROM authorities;

INSERT INTO authorities (id, authority) VALUES
(100004, 'SUPER_ADMIN');

SELECT setval('user_seq', max(id))
FROM authorities;

SELECT setval('user_seq', max(id))
FROM users;

INSERT INTO users(id, role_id, username, password, enabled) VALUES
(100005, 100004, 'admin@gmail.com', '$2a$10$o53h1x/Y2shcaj0XTM71ju3G06I9EVxoH1wcNwYpY4cf3vvscNsiu', true);

SELECT setval('user_seq', max(id))
FROM users;