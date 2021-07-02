DROP TABLE IF EXISTS school_class;
DROP TABLE IF EXISTS student;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 1;

CREATE TABLE school_class (
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('global_seq'),
    name VARCHAR(50) NOT NULL
);

CREATE UNIQUE INDEX id_name ON school_class(id, name);

CREATE TABLE student (
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('global_seq'),
    name VARCHAR(120) NOT NULL,
    surname VARCHAR(120) NOT NULL,
    class_id BIGINT NOT NULL,
    FOREIGN KEY (class_id) REFERENCES school_class(id)
);

CREATE UNIQUE INDEX student_class_id ON student(id, class_id);