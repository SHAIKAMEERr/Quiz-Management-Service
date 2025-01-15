package com.example.quiz_management_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRequestDTO {
    private String text;
    private String[] options;
    private String correctAnswer;
    private Long quizId;
}
