package com.coditas.cohorttheplatform.exception;

public class RefreshTokenExpiredException extends RuntimeException {
  public RefreshTokenExpiredException(String message) {
    super(message);
  }
}
