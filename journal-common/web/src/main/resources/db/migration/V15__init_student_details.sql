DROP TABLE IF EXISTS student_details;

DROP SEQUENCE IF EXISTS details_seq;

CREATE SEQUENCE details_seq START WITH 1000;

CREATE TABLE student_details (
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('details_seq'),
    student_id BIGINT NOT NULL UNIQUE,
    first_parent VARCHAR(128) DEFAULT '-',
    second_parent VARCHAR(128) DEFAULT '-',
    parents_email VARCHAR(128) DEFAULT '-',
    contact_num VARCHAR(12) DEFAULT '-',
    bio VARCHAR(1024) DEFAULT '-',
    FOREIGN KEY (student_id) REFERENCES student(id)
);

INSERT INTO student_details VALUES
    (1000, 4, 'Alex Petrov', 'Irina Petrova', 'alex_p@gmail.com', '+79113880851', 'A good boy'),
    (1001, 5, 'Petr Ivanov', 'Olga Ivanova', 'olga_i@gmail.com', '+79212221944', 'Slightly bad guy');

SELECT setval('details_seq', max(id))
FROM student_details;