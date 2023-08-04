package com.example.demo.exception.model;

import java.sql.Timestamp;

public class ApiError {

    private final String message;

    private final Timestamp timestamp;

    public ApiError(final String message, final Timestamp timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
}
