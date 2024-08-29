package com.recommendation.exception;

import org.springframework.http.HttpStatus;

public class MovieNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    final Integer errorCode;
    final HttpStatus httpErrorCode;

    public MovieNotFoundException(Integer errorCode, String errorMessage, HttpStatus httpErrorCode) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.httpErrorCode = httpErrorCode;
    }
}
