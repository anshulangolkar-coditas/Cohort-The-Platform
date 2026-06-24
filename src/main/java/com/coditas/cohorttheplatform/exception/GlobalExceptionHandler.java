package com.coditas.cohorttheplatform.exception;

import com.coditas.cohorttheplatform.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundException(
            NotFoundException ex) {

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorResponse.setDateTime(LocalDateTime.now());
        errorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> authenticationException(
            AuthenticationException ex) {

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatusCode(HttpStatus.UNAUTHORIZED.value());
        errorResponse.setDateTime(LocalDateTime.now());
        errorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(RefreshTokenExpiredException.class)
    public ResponseEntity<ErrorResponse> refreshTokenExpiredException(
            RefreshTokenExpiredException ex) {

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setDateTime(LocalDateTime.now());
        errorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


}
