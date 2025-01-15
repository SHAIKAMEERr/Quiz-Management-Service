package com.example.quiz_management_service.util;

import org.modelmapper.ModelMapper;

import com.example.quiz_management_service.dto.QuestionRequestDTO;
import com.example.quiz_management_service.dto.QuestionResponseDTO;
import com.example.quiz_management_service.entity.QuestionEntity;

public class QuestionMapperUtil {

    private final ModelMapper modelMapper;

    public QuestionMapperUtil(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public QuestionEntity convertToEntity(QuestionRequestDTO questionRequestDTO) {
        return modelMapper.map(questionRequestDTO, QuestionEntity.class);
    }

    public QuestionResponseDTO convertToResponseDTO(QuestionEntity questionEntity) {
        return modelMapper.map(questionEntity, QuestionResponseDTO.class);
    }
}