package com.example.demo.services.exception;

public class NoUserFoundByIdException extends RuntimeException {
    public NoUserFoundByIdException(String message) {
        super(message);
    }
}
