package com.example.quiz_management_service.util;

import org.modelmapper.ModelMapper;

import com.example.quiz_management_service.dto.QuizRequestDTO;
import com.example.quiz_management_service.dto.QuizResponseDTO;
import com.example.quiz_management_service.entity.QuizEntity;

public class QuizMapperUtil {

    private final ModelMapper modelMapper;

    public QuizMapperUtil(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public QuizEntity convertToEntity(QuizRequestDTO quizRequestDTO) {
        return modelMapper.map(quizRequestDTO, QuizEntity.class);
    }

    public QuizResponseDTO convertToResponseDTO(QuizEntity quizEntity) {
        return modelMapper.map(quizEntity, QuizResponseDTO.class);
    }
}