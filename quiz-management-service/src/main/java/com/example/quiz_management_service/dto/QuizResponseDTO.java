package com.example.quiz_management_service.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizResponseDTO {
	 private Long id;
	    private String title;
	    private String description;
	    private Long categoryId; 
	    private String difficultyLevel; 
	    private List<QuestionResponseDTO> questions;
}