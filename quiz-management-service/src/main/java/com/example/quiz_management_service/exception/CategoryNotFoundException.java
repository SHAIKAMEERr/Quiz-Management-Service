package com.example.quiz_management_service.exception;

@SuppressWarnings("serial")
public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String message) {
        super(message);
    }
}