ALTER TABLE school_class
ADD COLUMN user_id BIGINT;

ALTER TABLE school_class
ADD FOREIGN KEY (user_id) REFERENCES users(id);
