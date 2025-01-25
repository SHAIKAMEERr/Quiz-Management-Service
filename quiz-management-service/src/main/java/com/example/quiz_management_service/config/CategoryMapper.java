package com.example.quiz_management_service.config;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.quiz_management_service.dto.*;
import com.example.quiz_management_service.entity.*;

@Component
public class CategoryMapper {

    private static final Logger logger = LoggerFactory
    		.getLogger(CategoryMapper.class);

    private final ModelMapper modelMapper;

    public CategoryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // Category Mappings
    public CategoryEntity toEntity(CategoryRequestDTO dto) {
        logger.info("Mapping CategoryRequestDTO to CategoryEntity: {}", dto);
        CategoryEntity entity = modelMapper.map(dto, CategoryEntity.class);
        logger.info("Mapped CategoryEntity: {}", entity);
        return entity;
    }

    public CategoryResponseDTO toDTO(CategoryEntity entity) {
        logger.info("Mapping CategoryEntity to CategoryResponseDTO: {}", entity);
        CategoryResponseDTO dto = modelMapper.map(entity, CategoryResponseDTO.class);
        logger.info("Mapped CategoryResponseDTO: {}", dto);
        return dto;
    }

    // Quiz Mappings
    public QuizEntity toEntity(QuizRequestDTO dto) {
        logger.info("Mapping QuizRequestDTO to QuizEntity: {}", dto);
        QuizEntity quizEntity = modelMapper.map(dto, QuizEntity.class);

        if (dto.getCategoryId() != null) {
            logger.debug("Mapping categoryId {} to CategoryEntity", dto.getCategoryId());
            CategoryEntity category = new CategoryEntity();
            category.setId(dto.getCategoryId());
            quizEntity.setCategory(category);
        }

        logger.info("Mapped QuizEntity: {}", quizEntity);
        return quizEntity;
    }

    public QuizResponseDTO toDTO(QuizEntity entity) {
        logger.info("Mapping QuizEntity to QuizResponseDTO: {}", entity);
        QuizResponseDTO dto = modelMapper.map(entity, QuizResponseDTO.class);

        if (entity.getCategory() != null) {
            logger.debug("Mapping nested CategoryEntity to categoryId: {}", entity.getCategory().getId());
            dto.setCategoryId(entity.getCategory().getId());
        }

        // Ensure this is handled correctly without circular dependency
        if (entity.getQuestions() != null) {
            logger.debug("Mapping questions from QuizEntity to QuestionResponseDTO list");
            dto.setQuestions(
                entity.getQuestions().stream()
                      .map(this::mapQuestionToDTO)  // Use dedicated method for question mapping
                      .collect(Collectors.toList())
            );
        }

        logger.info("Mapped QuizResponseDTO: {}", dto);
        return dto;
    }

    private QuestionResponseDTO mapQuestionToDTO(QuestionEntity questionEntity) {
        logger.debug("Mapping QuestionEntity to QuestionResponseDTO: {}", questionEntity);
        return modelMapper.map(questionEntity, QuestionResponseDTO.class);
    }

    // Question Mappings
    public QuestionEntity toEntity(QuestionRequestDTO dto) {
        logger.info("Mapping QuestionRequestDTO to QuestionEntity: {}", dto);
        QuestionEntity questionEntity = modelMapper.map(dto, QuestionEntity.class);

        if (dto.getQuizId() != null) {
            logger.debug("Mapping quizId {} to QuizEntity", dto.getQuizId());
            QuizEntity quiz = new QuizEntity();
            quiz.setId(dto.getQuizId());
            questionEntity.setQuiz(quiz);
        }

        logger.info("Mapped QuestionEntity: {}", questionEntity);
        return questionEntity;
    }

    public QuestionResponseDTO toDTO(QuestionEntity entity) {
        logger.info("Mapping QuestionEntity to QuestionResponseDTO: {}", entity);
        QuestionResponseDTO dto = modelMapper.map(entity, QuestionResponseDTO.class);

        if (entity.getQuiz() != null) {
            logger.debug("Mapping nested QuizEntity to quizId: {}", entity.getQuiz().getId());
            dto.setQuizId(entity.getQuiz().getId());
        }

        if (entity.getOptions() != null) {
            logger.debug("Mapping options from QuestionEntity to OptionResponseDTO list");
            dto.setOptions(
                entity.getOptions().stream()
                      .map(this::toDTO)  // Call the existing method for Option mappings
                      .collect(Collectors.toList())
            );
        }

        logger.info("Mapped QuestionResponseDTO: {}", dto);
        return dto;
    }

    // Option Mappings
    public OptionEntity toEntity(OptionRequestDTO dto) {
        logger.info("Mapping OptionRequestDTO to OptionEntity: {}", dto);
        OptionEntity entity = modelMapper.map(dto, OptionEntity.class);
        logger.info("Mapped OptionEntity: {}", entity);
        return entity;
    }

    public OptionResponseDTO toDTO(OptionEntity entity) {
        logger.info("Mapping OptionEntity to OptionResponseDTO: {}", entity);
        OptionResponseDTO dto = modelMapper.map(entity, OptionResponseDTO.class);
        logger.info("Mapped OptionResponseDTO: {}", dto);
        return dto;
    }

    // Helper Method for DTO Mapping
    private <E, D> D toDTO(E entity, Class<D> dtoClass) {
        logger.debug("Mapping entity {} to DTO class {}", entity, dtoClass);
        return modelMapper.map(entity, dtoClass);
    }
}
