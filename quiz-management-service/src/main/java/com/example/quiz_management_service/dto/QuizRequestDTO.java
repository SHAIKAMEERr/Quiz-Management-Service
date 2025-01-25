package com.example.quiz_management_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizRequestDTO {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotNull(message = "Category ID is required")
    private Long categoryId;

    @NotBlank(message = "Difficulty Level is required")
    @Pattern(regexp = "EASY|MEDIUM|HARD", message = "Invalid difficulty level. Allowed values: EASY, MEDIUM, HARD")
    private String difficultyLevel;
}