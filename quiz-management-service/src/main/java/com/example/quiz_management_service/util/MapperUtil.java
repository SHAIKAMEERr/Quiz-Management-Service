package com.example.quiz_management_service.util;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.quiz_management_service.dto.CategoryResponseDTO;
import com.example.quiz_management_service.dto.QuestionRequestDTO;
import com.example.quiz_management_service.dto.QuestionResponseDTO;
import com.example.quiz_management_service.entity.CategoryEntity;
import com.example.quiz_management_service.entity.QuestionEntity;
import com.example.quiz_management_service.service.CategoryService;

@Component
public class MapperUtil {

    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    public MapperUtil(ModelMapper modelMapper, CategoryService categoryService) {
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }

    /**
     * Map a QuestionEntity to a QuestionResponseDTO.
     *
     * @param questionEntity the source entity to map
     * @return the mapped QuestionResponseDTO
     */
    public QuestionResponseDTO mapToQuestionResponseDTO(QuestionEntity questionEntity) {
        if (questionEntity == null) {
            throw new IllegalArgumentException("QuestionEntity cannot be null");
        }
        return modelMapper.map(questionEntity, QuestionResponseDTO.class);
    }

    /**
     * Map a QuestionRequestDTO to a QuestionEntity.
     *
     * @param questionRequestDTO the source DTO to map
     * @return the mapped QuestionEntity
     */
    public QuestionEntity mapToQuestionEntity(QuestionRequestDTO questionRequestDTO) {
        if (questionRequestDTO == null) {
            throw new IllegalArgumentException("QuestionRequestDTO cannot be null");
        }

        // Logger instance
        Logger logger = LoggerFactory.getLogger(this.getClass());

        logger.info("Mapping QuestionRequestDTO to QuestionEntity: {}", questionRequestDTO);

        // Fetch the CategoryEntity using categoryId
        Long categoryId = questionRequestDTO.getCategoryId();
        CategoryResponseDTO categoryResponseDTO = categoryService.getCategoryById(categoryId);

        logger.debug("Fetched CategoryResponseDTO: {}", categoryResponseDTO);

        // Map DTO to Entity
        CategoryEntity categoryEntity = modelMapper.map(categoryResponseDTO, CategoryEntity.class);
        logger.debug("Mapped CategoryEntity: {}", categoryEntity);

        // Map the remaining fields
        QuestionEntity questionEntity = modelMapper.map(questionRequestDTO, QuestionEntity.class);
        questionEntity.setCategory(categoryEntity);

        logger.info("Successfully mapped QuestionEntity: {}", questionEntity);

        return questionEntity;
    }

    /**
     * Generic mapping method for any type conversion.
     *
     * @param <S>            the source type
     * @param <D>            the destination type
     * @param source         the source object to map
     * @param destinationType the destination class type
     * @return the mapped object of destination type
     */
    public <S, D> D map(final S source, Class<D> destinationType) {
        if (source == null) {
            throw new IllegalArgumentException("Source object cannot be null");
        }
        if (destinationType == null) {
            throw new IllegalArgumentException("Destination type cannot be null");
        }
        return modelMapper.map(source, destinationType);
    }

    /**
     * Provides access to the ModelMapper instance for advanced configurations.
     *
     * @return the ModelMapper instance
     */
    public ModelMapper getModelMapper() {
        return modelMapper;
    }
}
