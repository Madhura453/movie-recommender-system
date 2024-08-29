package com.rating.exception;

import com.rating.dto.ExceptionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;

@RestControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(RatingNotFoundException.class)
    public ResponseEntity<Object> handleMovieNotFoundException(RatingNotFoundException exception,
                                                               WebRequest webRequest) {
        ExceptionResponseDto response = new ExceptionResponseDto();
        response.setMessage(exception.getMessage());
        response.setStatusCode(exception.errorCode);
        response.setDescription(Arrays.toString(exception.getStackTrace()));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
