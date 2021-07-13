DELETE FROM student;
DELETE FROM school_class;
DELETE FROM users;
DELETE FROM authorities;


INSERT INTO authorities(id,  authority) VALUES
    (100000,'USER'),
    (100001, 'ADMIN');

INSERT INTO users(id, role_id, username, password, enabled) VALUES
    (100002, 100000, 'alex@gmail.com', '$2a$10$o53h1x/Y2shcaj0XTM71ju3G06I9EVxoH1wcNwYpY4cf3vvscNsiu', true),
    (100003, 100001, 'petr@gmail.com', '$2a$10$o53h1x/Y2shcaj0XTM71ju3G06I9EVxoH1wcNwYpY4cf3vvscNsiu', true);

ALTER SEQUENCE user_seq RESTART WITH 100004;

INSERT INTO school_class(id, name, user_id) VALUES
    (12, '1A', 100002);

INSERT INTO student(id, name, surname, class_id) VALUES
    (6, 'Alex', 'Petrov', 12),
    (7, 'Jack', 'Sparrow', 12),
    (8, 'David', 'Blane', 12);

ALTER SEQUENCE global_seq RESTART WITH 9;