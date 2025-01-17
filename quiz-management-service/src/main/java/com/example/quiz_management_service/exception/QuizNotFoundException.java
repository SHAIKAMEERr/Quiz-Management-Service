package com.example.quiz_management_service.exception;

@SuppressWarnings("serial")
public class QuizNotFoundException extends RuntimeException {
    public QuizNotFoundException(String message) {
        super(message);
    }
}