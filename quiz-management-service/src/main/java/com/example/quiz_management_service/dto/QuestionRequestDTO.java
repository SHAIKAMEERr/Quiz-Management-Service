package com.example.quiz_management_service.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.quiz_management_service.constant.DifficultyLevel;
import com.example.quiz_management_service.constant.QuestionType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // Exclude null fields from JSON output
public class QuestionRequestDTO {

    private Long id;
    
    @NotNull
    @JsonProperty("quizId") // Maps "quizId" from JSON to this field
    private Long quizId;
    
    @NotNull
    @Size(min = 10, max = 500)
    private String questionText;

    @NotBlank(message = "Question type is required")
    private QuestionType questionType;

    @NotBlank(message = "Difficulty level is required")
    private DifficultyLevel difficultyLevel;
    
    @NotNull(message = "Category ID is required") // Added categoryId field
    private Long categoryId;

    // Consider adding a list of options for the question
    // private List<OptionDTO> options; 
}