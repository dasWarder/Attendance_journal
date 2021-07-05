DELETE FROM school_class;
DELETE FROM student;

ALTER SEQUENCE global_seq RESTART WITH 1;

INSERT INTO school_class(name) VALUES
('1А'),
('1Б'),
('1В');

INSERT INTO student(name, surname, class_id) VALUES
('Vasya', 'Petrov', 1),
('Ivan', 'Ivanov', 1),
('Alex', 'Semenov', 1),
('David', 'Tsvetkov', 2),
('Petr', 'Petrov', 2),
('Daniil', 'Svetlov', 3);