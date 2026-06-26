package com.coditas.cohorttheplatform.exception;

public class ExceptionMessages {
    private ExceptionMessages() {
        /* This utility class should not be instantiated */
    }

    public static final String USER_NOT_FOUND = "User Not found";
    public static final String REFRESH_TOKEN_EXPIRED = "Refresh Token Expired";
    public static final String JWT_TOKEN_EXPIRED = "Jwt Token Expired";
    public static final String AUTHENTICATION_EXCEPTION = "Wrong username or password";
    public static final String EMAIL_SENDING_FAILURE = "Something went wrong while sending email";
    public static final String INVALID_ROLE = "Role Not found";
    public static final String INVALID_INVITATION_ROLE = "Invited role not valid";
    public static final String PASSWORD_NOT_MATCHED = "Password not matched";
    public static final String INVALID_INVITATION = "Invitation invalid, please try again!!!";
    public static final String EXPIRED_INVITATION = "Invitation expired, please contact admin.";
    public static final String COURSE_EXISTS = "Course already exists.";
    public static final String COURSE_NOT_FOUND = "Course Not Found.";
    public static final String COURSE_USER_UNAUTHORIZED = "Course Does not belong to this Admin.";
    public static final String INSTRUCTOR_ALREADY_ASSIGNED = "This instructor is already conducting a batch";
    public static final String START_DATE_OR_END_DATE_INVALID = "Start or End date invalid";
    public static final String BATCH_ALREADY_EXISTS = "Batch by this name already exists";
    public static final String BATCH_NOT_FOUND = "Batch Not Found";
    public static final String BATCH_COURSE_MISMATCH = "Course and batch not matched";
    public static final String REFRESH_TOKEN_NOT_FOUND = "Refresh token not found";
    public static final String USER_ALREADY_EXISTS = "User already exists";
    public static final String ALREADY_ENROLLED = "Student already enrolled in this course";
    public static final String BATCH_ALREADY_STARTED = "This batch has already started, enroll in next batch";
    public static final String COURSE_INACTIVE = "Course and batch inActive";
    public static final String NOT_ALLOWED = "Not allowed for this user";
    public static final String FILE_NULL = "File cannot be null";
    public static final String FILE_UPLOAD_EXTENSIONS = "Only PDF, DOC, DOCX, PPT, PPTX and ZIP files are allowed";
    public static final String FILE_UPLOAD_LIMIT = "File size too big, only 10MB allowed.";
    public static final String NOT_ENROLLED = "Student Not enrolled in this batch.";




}
