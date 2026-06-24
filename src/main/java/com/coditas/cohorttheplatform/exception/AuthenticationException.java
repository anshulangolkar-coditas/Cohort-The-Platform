package com.coditas.cohorttheplatform.exception;

public class AuthenticationException extends RuntimeException {
  public AuthenticationException(String message) {
    super(message);
  }
}
