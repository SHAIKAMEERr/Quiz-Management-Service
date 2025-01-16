package com.example.quiz_management_service.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.quiz_management_service.dao.CategoryDAO;
import com.example.quiz_management_service.dto.CategoryRequestDTO;
import com.example.quiz_management_service.dto.CategoryResponseDTO;
import com.example.quiz_management_service.entity.CategoryEntity;
import com.example.quiz_management_service.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private final CategoryDAO categoryDAO;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryDAO categoryDAO, ModelMapper modelMapper) {
        this.categoryDAO = categoryDAO;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO) {
        logger.info("Creating new category: {}", categoryRequestDTO);
        CategoryEntity categoryEntity = modelMapper.map(categoryRequestDTO, CategoryEntity.class);
        categoryEntity = categoryDAO.save(categoryEntity);
        logger.info("Category created successfully: {}", categoryEntity);
        return modelMapper.map(categoryEntity, CategoryResponseDTO.class);
    }

    @Override
    public CategoryResponseDTO getCategoryById(Long id) {
        logger.info("Fetching category by ID: {}", id);
        CategoryEntity categoryEntity = categoryDAO.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        return modelMapper.map(categoryEntity, CategoryResponseDTO.class);
    }

    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        logger.info("Fetching all categories");
        List<CategoryEntity> categoryEntities = categoryDAO.findAll();
        return categoryEntities.stream()
                .map(entity -> modelMapper.map(entity, CategoryResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO categoryRequestDTO) {
        logger.info("Updating category with ID: {}", id);
        CategoryEntity existingCategory = categoryDAO.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        modelMapper.map(categoryRequestDTO, existingCategory);
        existingCategory = categoryDAO.save(existingCategory);
        return modelMapper.map(existingCategory, CategoryResponseDTO.class);
    }

    @Override
    public void deleteCategory(Long id) {
        logger.info("Deleting category with ID: {}", id);
        categoryDAO.deleteById(id);
    }
}