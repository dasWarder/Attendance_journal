DELETE FROM absence;
ALTER SEQUENCE absence_seq RESTART WITH 10000;

ALTER TABLE absence
DROP COLUMN student_id;

CREATE TABLE student_absence (
    absence_id BIGINT NOT NULL,
    student_id BIGINT NOT NULL,
    PRIMARY KEY (absence_id, student_id),
    FOREIGN KEY (absence_id) REFERENCES absence(id),
    FOREIGN KEY (student_id) REFERENCES student(id)
);