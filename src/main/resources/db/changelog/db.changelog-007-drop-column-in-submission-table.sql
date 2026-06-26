-- liquibase formatted sql

-- changeset Coditas-Admin:1782458858809-1
ALTER TABLE submission
    DROP COLUMN submission_status;

