package com.example.quiz_management_service.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.quiz_management_service.dao.CategoryDAO;
import com.example.quiz_management_service.dto.CategoryRequestDTO;
import com.example.quiz_management_service.dto.CategoryResponseDTO;
import com.example.quiz_management_service.entity.CategoryEntity;
import com.example.quiz_management_service.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDAO categoryDAO;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryDAO categoryDAO, ModelMapper modelMapper) {
        this.categoryDAO = categoryDAO;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO) {
        CategoryEntity categoryEntity = modelMapper.map(categoryRequestDTO, CategoryEntity.class);
        categoryEntity = categoryDAO.save(categoryEntity);
        return modelMapper.map(categoryEntity, CategoryResponseDTO.class);
    }

    @Override
    public CategoryResponseDTO getCategoryById(Long id) {
        CategoryEntity categoryEntity = categoryDAO.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        return modelMapper.map(categoryEntity, CategoryResponseDTO.class);
    }

    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        List<CategoryEntity> categoryEntities = categoryDAO.findAll();
        return categoryEntities.stream().map(entity -> modelMapper.map(entity, CategoryResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO categoryRequestDTO) {
        CategoryEntity existingCategory = categoryDAO.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        modelMapper.map(categoryRequestDTO, existingCategory);
        existingCategory = categoryDAO.save(existingCategory);
        return modelMapper.map(existingCategory, CategoryResponseDTO.class);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryDAO.deleteById(id);
    }
}


