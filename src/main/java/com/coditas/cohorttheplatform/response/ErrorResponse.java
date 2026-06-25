package com.coditas.cohorttheplatform.response;

import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {

    Integer statusCode;
    String message;
    LocalDateTime dateTime;

}
