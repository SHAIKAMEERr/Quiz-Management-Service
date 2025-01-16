package com.example.quiz_management_service.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponseDTO {

    private Long id;
    private String questionText;
    private String questionType; 
    private String difficultyLevel;
    private Long quizId;
    private List<OptionResponseDTO> options; 
}