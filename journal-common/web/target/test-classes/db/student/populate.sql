DELETE FROM school_class;
DELETE FROM student;


INSERT INTO school_class(id, name) VALUES
    (1, '1А');

INSERT INTO student(id, name, surname, class_id) VALUES
    (2, 'Alex', 'Petrov', 1),
    (3, 'Jack', 'Sparrow', 1),
    (4, 'David', 'Blane', 1);