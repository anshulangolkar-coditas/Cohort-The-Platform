package com.coditas.cohorttheplatform.exception;

public class EmailSendingFailureException extends RuntimeException {
  public EmailSendingFailureException(String message) {
    super(message);
  }
}
