package com.example.quiz_management_service.constant;

import org.springframework.http.HttpStatus;

public enum CustomError {
    QUIZ_NOT_FOUND(HttpStatus.NOT_FOUND, "Quiz not found"),
    QUESTION_NOT_FOUND(HttpStatus.NOT_FOUND, "Question not found"),
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "Category not found"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");

    private final HttpStatus status;
    private final String message;

    CustomError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
