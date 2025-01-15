package com.example.quiz_management_service.util;

import org.modelmapper.ModelMapper;

import com.example.quiz_management_service.dto.CategoryRequestDTO;
import com.example.quiz_management_service.dto.CategoryResponseDTO;
import com.example.quiz_management_service.entity.CategoryEntity;

public class CategoryMapperUtil {

    private final ModelMapper modelMapper;

    public CategoryMapperUtil(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CategoryEntity convertToEntity(CategoryRequestDTO categoryRequestDTO) {
        return modelMapper.map(categoryRequestDTO, CategoryEntity.class);
    }

    public CategoryResponseDTO convertToResponseDTO(CategoryEntity categoryEntity) {
        return modelMapper.map(categoryEntity, CategoryResponseDTO.class);
    }
}
