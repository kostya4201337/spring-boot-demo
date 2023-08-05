package com.example.demo.services.exception;

public class AgeValidationException extends RuntimeException {
    public AgeValidationException(String message) {
        super(message);
    }
}
