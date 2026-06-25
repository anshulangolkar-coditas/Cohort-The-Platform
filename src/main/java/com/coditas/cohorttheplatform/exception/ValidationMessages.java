package com.coditas.cohorttheplatform.exception;

public class ValidationMessages {
    private ValidationMessages() {
        /* This utility class should not be instantiated */
    }

    public static final String EMAIL_REQUIRED = "Email field should not be blank";
    public static final String PASSWORD_REQUIRED = "Password field cannot be blank";
    public static final String VALID_EMAIL = "Please enter a valid email";
    public static final String FIELD_NOT_BLANK = "This field should not be blank";

}
