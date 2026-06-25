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


}
