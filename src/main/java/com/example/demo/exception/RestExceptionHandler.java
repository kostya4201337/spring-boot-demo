package com.example.demo.exception;

import com.example.demo.exception.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Timestamp;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler
    ApiError defaultHandler(final Throwable t) {
        return new ApiError(t.getMessage(), new Timestamp(System.currentTimeMillis()));
    }
}
