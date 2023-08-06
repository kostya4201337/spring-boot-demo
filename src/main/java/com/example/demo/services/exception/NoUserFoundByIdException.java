package com.example.demo.services.exception;

public class NoUserFoundByIdException extends RuntimeException {
    public NoUserFoundByIdException(final String message) {
        super(message);
    }
}
