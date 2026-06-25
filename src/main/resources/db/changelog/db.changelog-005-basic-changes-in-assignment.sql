-- liquibase formatted sql

-- changeset Coditas-Admin:1782377850126-8
ALTER TABLE assignment
    DROP CONSTRAINT fk_assignment_on_course;

-- changeset Coditas-Admin:1782377850126-1
ALTER TABLE assignment
    ADD assignment_description VARCHAR(255);
ALTER TABLE assignment
    ADD assignment_title VARCHAR(255);
ALTER TABLE assignment
    ADD course_batch_id BIGINT;

-- changeset Coditas-Admin:1782377850126-2
ALTER TABLE assignment
    ALTER COLUMN assignment_description SET NOT NULL;

-- changeset Coditas-Admin:1782377850126-4
ALTER TABLE assignment
    ALTER COLUMN assignment_title SET NOT NULL;

-- changeset Coditas-Admin:1782377850126-6
ALTER TABLE assignment
    ALTER COLUMN course_batch_id SET NOT NULL;

-- changeset Coditas-Admin:1782377850126-7
ALTER TABLE assignment
    ADD CONSTRAINT FK_ASSIGNMENT_ON_COURSE_BATCH FOREIGN KEY (course_batch_id) REFERENCES course_batch (course_batch_id);

-- changeset Coditas-Admin:1782377850126-9
ALTER TABLE assignment
    DROP COLUMN assignmnet_description;
ALTER TABLE assignment
    DROP COLUMN assignmnet_title;
ALTER TABLE assignment
    DROP COLUMN course_id;

