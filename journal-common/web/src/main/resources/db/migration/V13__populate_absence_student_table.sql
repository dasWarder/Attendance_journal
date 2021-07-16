DELETE FROM student_absence;

INSERT INTO absence VALUES
    (10000, '2021-05-02'),
    (10001, '2021-05-03');

SELECT setval('absence_seq', max(id))
FROM absence;

INSERT INTO student_absence VALUES
    (10000, 4),
    (10000, 5),
    (10001, 6);