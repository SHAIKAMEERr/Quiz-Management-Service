package com.example.quiz_management_service.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Handle QuizNotFoundException
    @ExceptionHandler(QuizNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleQuizNotFoundException(QuizNotFoundException ex) {
        logger.error("Quiz not found: {}", ex.getMessage(), ex);
        ErrorResponse errorResponse = new ErrorResponse("Quiz not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    // Handle QuestionNotFoundException
    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleQuestionNotFound(QuestionNotFoundException ex) {
        logger.error("Question not found: {}", ex.getMessage(), ex);
        ErrorResponse errorResponse = new ErrorResponse("Question not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    // Handle CategoryNotFoundException
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCategoryNotFoundException(CategoryNotFoundException ex) {
        logger.error("Category not found: {}", ex.getMessage(), ex);
        ErrorResponse errorResponse = new ErrorResponse("Category not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    // Handle generic exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        logger.error("Unexpected error occurred: {}", ex.getMessage(), ex);
        ErrorResponse errorResponse = new ErrorResponse("Internal server error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    // Error response structure
    public static class ErrorResponse {
        private final String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
