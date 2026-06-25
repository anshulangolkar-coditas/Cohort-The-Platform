package com.coditas.cohorttheplatform.response;

import java.awt.print.Pageable;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApplicationResponse<T> {

    private Integer statusCode;

    private String message;

    private T data;

    private List<ErrorResponse> errors;

    private Pagination pagination;

    public static <T> ApplicationResponse<T> success(
            Integer statusCode,
            String message,
            T data) {

        return ApplicationResponse.<T>builder()
                .statusCode(statusCode)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> ApplicationResponse<T> success(
            Integer statusCode,
            String message,
            T data,
            Pagination pagination) {

        return ApplicationResponse.<T>builder()
                .statusCode(statusCode)
                .message(message)
                .data(data)
                .pagination(pagination)
                .build();
    }

    public static <T> ApplicationResponse<T> error(
            Integer statusCode,
            String message,
            List<ErrorResponse> errors) {

        return ApplicationResponse.<T>builder()
                .statusCode(statusCode)
                .message(message)
                .errors(errors)
                .build();
    }
}

/*
public class ApplicationResponse<T> {

    Integer statusCode;
    String message;
    T data;
    private List<ErrorResponse> errorResponses;

    public ApplicationResponse(Integer statusCode, String message, T data){
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public ApplicationResponse(List<ErrorResponse> errorResponses){
        this.errorResponses = errorResponses;
    }



}
*/
