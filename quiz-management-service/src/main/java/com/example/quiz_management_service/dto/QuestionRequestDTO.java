package com.example.quiz_management_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRequestDTO {
    
    private Long id;
    
    private String questionText;
    
    private String questionType; 
    
    private String difficultyLevel;
    
    private Long quizId; 
}
