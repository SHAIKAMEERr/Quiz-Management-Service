package com.example.quiz_management_service.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz_management_service.dto.CategoryRequestDTO;
import com.example.quiz_management_service.dto.CategoryResponseDTO;
import com.example.quiz_management_service.service.CategoryService;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) {
        logger.info("Received request to create a new category: {}", categoryRequestDTO);
        try {
            CategoryResponseDTO createdCategory = categoryService.createCategory(categoryRequestDTO);
            logger.info("Successfully created category with ID: {}", createdCategory.getId());
            return ResponseEntity.ok(createdCategory);
        } catch (Exception e) {
            logger.error("Error occurred while creating a category", e);
            throw e; // Or handle exception accordingly
        }
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        logger.info("Received request to fetch all categories");
        try {
            List<CategoryResponseDTO> categories = categoryService.getAllCategories();
            logger.info("Successfully fetched {} categories", categories.size());
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            logger.error("Error occurred while fetching all categories", e);
            throw e; // Or handle exception accordingly
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long id) {
        logger.info("Received request to fetch category with ID: {}", id);
        try {
            CategoryResponseDTO category = categoryService.getCategoryById(id);
            logger.info("Successfully fetched category: {}", category);
            return ResponseEntity.ok(category);
        } catch (Exception e) {
            logger.error("Error occurred while fetching category with ID: {}", id, e);
            throw e; // Or handle exception accordingly
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryRequestDTO categoryRequestDTO) {
        logger.info("Received request to update category with ID: {}, Data: {}", id, categoryRequestDTO);
        try {
            CategoryResponseDTO updatedCategory = categoryService.updateCategory(id, categoryRequestDTO);
            logger.info("Successfully updated category with ID: {}", updatedCategory.getId());
            return ResponseEntity.ok(updatedCategory);
        } catch (Exception e) {
            logger.error("Error occurred while updating category with ID: {}", id, e);
            throw e; // Or handle exception accordingly
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        logger.info("Received request to delete category with ID: {}", id);
        try {
            categoryService.deleteCategory(id);
            logger.info("Successfully deleted category with ID: {}", id);
            return ResponseEntity.ok("Category deleted successfully");
        } catch (Exception e) {
            logger.error("Error occurred while deleting category with ID: {}", id, e);
            throw e; // Or handle exception accordingly
        }
    }
}
