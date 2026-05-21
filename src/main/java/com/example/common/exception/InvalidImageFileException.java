package com.example.common.exception;

public class InvalidImageFileException extends RuntimeException {
    public InvalidImageFileException(String message) {
        super(message);
    }
}
