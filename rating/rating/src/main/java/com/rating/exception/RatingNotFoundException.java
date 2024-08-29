package com.rating.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class RatingNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    final Integer errorCode;
    final HttpStatus httpErrorCode;

    public RatingNotFoundException(Integer errorCode, String errorMessage, HttpStatus httpErrorCode) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.httpErrorCode = httpErrorCode;
    }
}
