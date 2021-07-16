DELETE FROM absence;
ALTER SEQUENCE absence_seq RESTART WITH 10000;

INSERT INTO absence(id, absence_date, student_id) VALUES
    (10000, '2021-05-02', 4),
    (10001, '2021-05-02', 6),
    (10002, '2021-05-03', 9);

SELECT setval('absence_seq', max(id))
FROM absence;