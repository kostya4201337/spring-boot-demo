package com.example.demo.exception.model;

import java.sql.Timestamp;

public class ApiError {

    public final String message;

    public final Timestamp timestamp;

    public ApiError(final String message, final Timestamp timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

}
