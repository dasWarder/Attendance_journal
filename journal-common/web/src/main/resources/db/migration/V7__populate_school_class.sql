DELETE FROM student;
DELETE FROM school_class;


ALTER SEQUENCE global_seq RESTART WITH 1;

INSERT INTO school_class(id, name, user_id) VALUES
(1, '1А', 100002),
(2, '1Б', 100002),
(3, '1В', 100002);

SELECT setval('global_seq', max(id))
FROM school_class;

INSERT INTO student(id, name, surname, class_id) VALUES
(4, 'Vasya', 'Petrov', 1),
(5, 'Ivan', 'Ivanov', 1),
(6, 'Alex', 'Semenov', 1),
(7, 'David', 'Tsvetkov', 2),
(8, 'Petr', 'Petrov', 2),
(9, 'Daniil', 'Svetlov', 3);

SELECT setval('global_seq', max(id))
FROM student;

ALTER TABLE school_class
ALTER COLUMN user_id SET NOT NULL;