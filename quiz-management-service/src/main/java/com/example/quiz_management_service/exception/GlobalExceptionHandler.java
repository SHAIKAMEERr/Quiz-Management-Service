package com.example.quiz_management_service.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.quiz_management_service.constant.CustomError;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(QuizNotFoundException.class)
    public ErrorResponse handleQuizNotFoundException(QuizNotFoundException ex) {
        return new ErrorResponse(CustomError.QUIZ_NOT_FOUND);
    }

    @ExceptionHandler(QuestionNotFoundException.class)
    public ErrorResponse handleQuestionNotFoundException(QuestionNotFoundException ex) {
        return new ErrorResponse(CustomError.QUESTION_NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ErrorResponse handleCategoryNotFoundException(CategoryNotFoundException ex) {
        return new ErrorResponse(CustomError.CATEGORY_NOT_FOUND);
    }

    public static class ErrorResponse {
        private final int statusCode;
        private final String message;

        public ErrorResponse(CustomError error) {
            this.statusCode = error.getStatus().value();
            this.message = error.getMessage();
        }

        public int getStatusCode() {
            return statusCode;
        }

        public String getMessage() {
            return message;
        }
    }
}
