package com.example.quiz_management_service.service;

import java.util.List;

import com.example.quiz_management_service.dto.QuestionRequestDTO;
import com.example.quiz_management_service.dto.QuestionResponseDTO;

public interface QuestionService {

    List<QuestionResponseDTO> getQuestionsByKeyword(String keyword);

    List<QuestionResponseDTO> getQuestionsByCategory(Long categoryId);

    List<QuestionResponseDTO> getQuestionsByDifficulty(String difficultyLevel);

    List<QuestionResponseDTO> getQuestionsByCategoryAndDifficulty(Long categoryId, String difficulty);

    List<QuestionResponseDTO> getAllQuestionsOrderedByCreatedDate();

    List<QuestionResponseDTO> getRandomQuestionsByCategory(Long categoryId);
    
    QuestionResponseDTO addQuestion(QuestionRequestDTO questionRequestDTO);
    
    void deleteQuestion(Long questionID);
}
