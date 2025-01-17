package com.example.quiz_management_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // Exclude null fields from JSON output
public class QuestionRequestDTO {

    private Long id;

    @NotBlank(message = "Question text is required")
    private String questionText;

    @NotBlank(message = "Question type is required")
    private String questionType;

    @NotBlank(message = "Difficulty level is required")
    private String difficultyLevel;

    @NotNull(message = "Quiz ID is required")
    private Long quizId;

    // Consider adding a list of options for the question
    // private List<OptionDTO> options; 
}