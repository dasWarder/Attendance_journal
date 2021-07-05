DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS school_class;


CREATE TABLE school_class (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50)
);

CREATE TABLE student (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(120),
    surname VARCHAR(150),
    class_id INT NOT NULL,
    foreign key (class_id) references school_class(id)
);