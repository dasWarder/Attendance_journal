DROP TABLE IF EXISTS absence;
DROP SEQUENCE IF EXISTS absence_seq;

CREATE SEQUENCE absence_seq START WITH 10000;

CREATE TABLE absence (
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('absence_seq'),
    absence_date TIMESTAMP NOT NULL,
    student_id BIGINT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES student(id)
);

CREATE UNIQUE INDEX student_date ON absence(absence_date, student_id);