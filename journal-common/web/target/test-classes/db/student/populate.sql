DELETE FROM student;
DELETE FROM school_class;

INSERT INTO school_class(id, name) VALUES
    (12, '1А');

INSERT INTO student(id, name, surname, class_id) VALUES
    (2, 'Alex', 'Petrov', 12),
    (3, 'Jack', 'Sparrow', 12),
    (4, 'David', 'Blane', 12);