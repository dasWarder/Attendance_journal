DELETE FROM student;
DELETE FROM school_class;

INSERT INTO school_class(id, name) VALUES
    (12, '1A');

INSERT INTO student(id, name, surname, class_id) VALUES
    (6, 'Alex', 'Petrov', 12),
    (7, 'Jack', 'Sparrow', 12),
    (8, 'David', 'Blane', 12);