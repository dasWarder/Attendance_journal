DELETE FROM school_class;
DELETE FROM student;

ALTER SEQUENCE global_seq RESTART WITH 1;

INSERT INTO school_class(id, name) VALUES
(1, '1А'),
(2, '1Б'),
(3, '1В');

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