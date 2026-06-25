-- liquibase formatted sql

-- changeset Coditas-Admin:1782368515710-1
ALTER TABLE course
    ADD created_by BIGINT;

-- changeset Coditas-Admin:1782368515710-2
ALTER TABLE course
    ALTER COLUMN created_by SET NOT NULL;

-- changeset Coditas-Admin:1782368515710-3
ALTER TABLE course
    ADD CONSTRAINT FK_COURSE_ON_CREATED_BY FOREIGN KEY (created_by) REFERENCES cohort_user (user_id);

