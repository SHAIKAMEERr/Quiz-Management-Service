package com.example.quiz_management_service.exception;

public class QuizCreationException extends RuntimeException {
    public QuizCreationException(String message) {
        super(message);
    }

    public QuizCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
