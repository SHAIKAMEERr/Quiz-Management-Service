package com.example.quiz_management_service.exception;

public class InvalidKeywordException extends RuntimeException {
    public InvalidKeywordException(String message) {
        super(message);
    }
}