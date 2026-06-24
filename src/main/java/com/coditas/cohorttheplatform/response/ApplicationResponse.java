package com.coditas.cohorttheplatform.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
