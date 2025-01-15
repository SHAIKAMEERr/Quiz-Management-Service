package com.example.quiz_management_service.exception;

public class QuestionNotFoundException extends RuntimeException {
 public QuestionNotFoundException(String message) {
     super(message);
 }
}