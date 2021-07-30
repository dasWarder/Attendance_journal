DELETE FROM student_absence;
DELETE FROM absence;
DELETE FROM student_details;
DELETE FROM student;
DELETE FROM school_class;
DELETE FROM users;
DELETE FROM authorities;
DELETE FROM refresh_token;



ALTER SEQUENCE user_seq RESTART WITH 100000;
ALTER SEQUENCE global_seq RESTART WITH 1;
ALTER SEQUENCE token_seq RESTART WITH 1;
ALTER SEQUENCE absence_seq RESTART WITH 10000;
ALTER SEQUENCE details_seq RESTART WITH 1000;


INSERT INTO authorities(id,  authority) VALUES
    (100000,'USER'),
    (100001, 'ADMIN'),
    (100002, 'SUPER_ADMIN');

INSERT INTO users(id, role_id, username, password, enabled) VALUES
    (100003, 100000, 'alex@gmail.com', '$2a$10$o53h1x/Y2shcaj0XTM71ju3G06I9EVxoH1wcNwYpY4cf3vvscNsiu', true),
    (100004, 100001, 'petr@gmail.com', '$2a$10$o53h1x/Y2shcaj0XTM71ju3G06I9EVxoH1wcNwYpY4cf3vvscNsiu', true),
    (100005, 100002, 'admin@gmail.com', '$2a$10$o53h1x/Y2shcaj0XTM71ju3G06I9EVxoH1wcNwYpY4cf3vvscNsiu', true);

ALTER SEQUENCE user_seq RESTART WITH 100006;

INSERT INTO school_class(id, name, user_id) VALUES
    (12, '1A', 100003),
    (13, '2A', 100003);

INSERT INTO student(id, name, surname, class_id) VALUES
    (6, 'Alex', 'Petrov', 12),
    (7, 'Jack', 'Sparrow', 12),
    (8, 'David', 'Blane', 12);

ALTER SEQUENCE global_seq RESTART WITH 9;

INSERT INTO refresh_token(id, subject, token, expirydate) VALUES
    (1234, 'alex@gmail.com', '111111111111111111111111111111111111111111', CURRENT_TIMESTAMP());

ALTER SEQUENCE token_seq RESTART WITH 1235;

INSERT INTO absence(id, absence_date) VALUES
    (10000, '2021-07-27'),
    (10001, '2021-07-28');

ALTER SEQUENCE absence_seq RESTART WITH 10002;

INSERT INTO student_absence(absence_id, student_id) VALUES
    (10000, 6),
    (10000, 7),
    (10001, 6);

INSERT INTO student_details(id, student_id, first_parent, second_parent, parents_email, contact_num, bio) VALUES
    (1000, 6, 'Petr Petrov', 'Olga Petrova', 'petr@gmail.com', '+79113880777', 'Just a poor boy'),
    (1001, 7, 'Unknown Pirate', '', '', '', 'PIRAAAATE!!!');

ALTER SEQUENCE details_seq RESTART WITH 1002;