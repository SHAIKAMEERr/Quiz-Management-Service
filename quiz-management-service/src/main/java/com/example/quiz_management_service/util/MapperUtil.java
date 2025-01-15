package com.example.quiz_management_service.util;

import com.example.quiz_management_service.dto.QuestionRequestDTO;
import com.example.quiz_management_service.dto.QuestionResponseDTO;
import com.example.quiz_management_service.entity.QuestionEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MapperUtil {

    private final ModelMapper modelMapper;

    public MapperUtil(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * Map a QuestionEntity to a QuestionResponseDTO
     */
    public QuestionResponseDTO mapToQuestionResponseDTO(QuestionEntity questionEntity) {
        return modelMapper.map(questionEntity, QuestionResponseDTO.class);
    }

    /**
     * Map a QuestionRequestDTO to a QuestionEntity
     */
    public QuestionEntity mapToQuestionEntity(QuestionRequestDTO questionRequestDTO) {
        return modelMapper.map(questionRequestDTO, QuestionEntity.class);
    }

    /**
     * Generic mapping method for any type conversion
     */
    public <S, D> D map(final S source, Class<D> destinationType) {
        return modelMapper.map(source, destinationType);
    }

    public ModelMapper getModelMapper() {
        return modelMapper;
    }
}