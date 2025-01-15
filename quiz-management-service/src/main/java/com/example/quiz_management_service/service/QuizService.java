package com.example.quiz_management_service.service;

import java.util.List;

import com.example.quiz_management_service.dto.QuizRequestDTO;
import com.example.quiz_management_service.dto.QuizResponseDTO;

public interface QuizService {
    QuizResponseDTO createQuiz(QuizRequestDTO quizRequestDTO);
    QuizResponseDTO getQuizById(Long id);
    List<QuizResponseDTO> getAllQuizzes();
    QuizResponseDTO updateQuiz(Long id, QuizRequestDTO quizRequestDTO);
    void deleteQuiz(Long id);
}