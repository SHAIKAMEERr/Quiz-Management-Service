package com.example.quiz_management_service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.quiz_management_service.constant.DifficultyLevel;
import com.example.quiz_management_service.constant.QuestionType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) 
public class QuestionRequestDTO {

    private Long id;
    
    @NotNull(message = "Quiz ID is required") 
    @JsonProperty("quizId") 
    private Long quizId;
    
    @NotNull(message = "Question text is required")
    @Size(min = 10, max = 500, message = "Question text must be between 10 and 500 characters")
    private String questionText;

    @NotNull(message = "Question type is required")
    private QuestionType questionType;

    @NotNull(message = "Difficulty level is required")
    private DifficultyLevel difficultyLevel;
    
    @NotNull(message = "Category ID is required")
    private Long categoryId;
    
}