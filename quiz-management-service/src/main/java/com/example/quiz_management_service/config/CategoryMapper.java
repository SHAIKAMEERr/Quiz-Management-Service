package com.example.quiz_management_service.config;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.quiz_management_service.constant.DifficultyLevel;
import com.example.quiz_management_service.constant.QuestionType;
import com.example.quiz_management_service.dto.CategoryRequestDTO;
import com.example.quiz_management_service.dto.CategoryResponseDTO;
import com.example.quiz_management_service.dto.OptionRequestDTO;
import com.example.quiz_management_service.dto.OptionResponseDTO;
import com.example.quiz_management_service.dto.QuestionRequestDTO;
import com.example.quiz_management_service.dto.QuestionResponseDTO;
import com.example.quiz_management_service.dto.QuizRequestDTO;
import com.example.quiz_management_service.dto.QuizResponseDTO;
import com.example.quiz_management_service.entity.CategoryEntity;
import com.example.quiz_management_service.entity.OptionEntity;
import com.example.quiz_management_service.entity.QuestionEntity;
import com.example.quiz_management_service.entity.QuizEntity;

@Component
public class CategoryMapper {

    private final ModelMapper modelMapper;

    public CategoryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // Category Mappings
    public CategoryEntity toEntity(CategoryRequestDTO dto) {
        return modelMapper.map(dto, CategoryEntity.class);
    }

    public CategoryResponseDTO toDTO(CategoryEntity entity) {
        return modelMapper.map(entity, CategoryResponseDTO.class);
    }

    // Quiz Mappings
    public QuizEntity toEntity(QuizRequestDTO dto) {
        QuizEntity quizEntity = modelMapper.map(dto, QuizEntity.class);
        if (dto.getCategoryId() != null) {
            CategoryEntity category = new CategoryEntity();
            category.setId(dto.getCategoryId());
            quizEntity.setCategory(category);
        }
        return quizEntity;
    }

    public QuizResponseDTO toDTO(QuizEntity entity) {
        QuizResponseDTO quizResponseDTO = new QuizResponseDTO();
        quizResponseDTO.setId(entity.getId());
        quizResponseDTO.setTitle(entity.getTitle());
        quizResponseDTO.setDescription(entity.getDescription());
        if (entity.getCategory() != null) {
            quizResponseDTO.setCategoryId(entity.getCategory().getId());
        }
        quizResponseDTO.setDifficultyLevel(entity.getDifficultyLevel().toString());
        if (entity.getQuestions() != null) {
            quizResponseDTO.setQuestions(
                    entity.getQuestions().stream()
                            .map(this::mapQuestionToDTO)
                            .collect(Collectors.toList())
            );
        }
        return quizResponseDTO;
    }

    public QuestionEntity toEntity(QuestionRequestDTO dto) {
        QuestionEntity questionEntity = modelMapper.map(dto, QuestionEntity.class);
        if (dto.getQuizId() != null) {
            QuizEntity quiz = new QuizEntity();
            quiz.setId(dto.getQuizId());
            questionEntity.setQuiz(quiz);
        }
        if (dto.getQuestionType() != null) { // Check for null before using
            questionEntity.setQuestionType(QuestionType.valueOf(dto.getQuestionType()));
        }
        if (dto.getDifficultyLevel() != null) { // Check for null before using
            questionEntity.setDifficultyLevel(DifficultyLevel.valueOf(dto.getDifficultyLevel()));
        }
        return questionEntity;
    }

    public QuestionResponseDTO toDTO(QuestionEntity entity) {
        QuestionResponseDTO questionResponseDTO = new QuestionResponseDTO();
        questionResponseDTO.setId(entity.getId());
        questionResponseDTO.setQuestionText(entity.getQuestionText());
        questionResponseDTO.setQuestionType(entity.getQuestionType().toString());
        questionResponseDTO.setDifficultyLevel(entity.getDifficultyLevel().toString());
        if (entity.getQuiz() != null) {
            questionResponseDTO.setQuizId(entity.getQuiz().getId());
        }
        if (entity.getOptions() != null) {
            questionResponseDTO.setOptions(
                    entity.getOptions().stream()
                            .map(this::mapOptionToDTO)
                            .collect(Collectors.toList())
            );
        }
        return questionResponseDTO;
    }

    // Option Mappings
    public OptionEntity toEntity(OptionRequestDTO dto) {
        return modelMapper.map(dto, OptionEntity.class);
    }

    public OptionResponseDTO toDTO(OptionEntity entity) {
        return modelMapper.map(entity, OptionResponseDTO.class);
    }

    // Helper Methods
    private QuestionResponseDTO mapQuestionToDTO(QuestionEntity questionEntity) {
        return toDTO(questionEntity);
    }

    private OptionResponseDTO mapOptionToDTO(OptionEntity optionEntity) {
        return toDTO(optionEntity);
    }
}