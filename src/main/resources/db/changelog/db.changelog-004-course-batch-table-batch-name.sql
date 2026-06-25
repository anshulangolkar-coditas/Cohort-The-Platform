-- liquibase formatted sql

-- changeset Coditas-Admin:1782370247337-1
ALTER TABLE course_batch
    ADD batch_name VARCHAR(255);

-- changeset Coditas-Admin:1782370247337-2
ALTER TABLE course_batch
    ALTER COLUMN batch_name SET NOT NULL;

