package com.example.quiz_management_service.exception;

@SuppressWarnings("serial")
public class QuestionNotFoundException extends RuntimeException {
 public QuestionNotFoundException(String message) {
     super(message);
 }
}