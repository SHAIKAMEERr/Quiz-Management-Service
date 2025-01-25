package com.example.quiz_management_service.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.quiz_management_service.dao.CategoryDAO;
import com.example.quiz_management_service.dao.QuestionDAO;
import com.example.quiz_management_service.dto.QuestionRequestDTO;
import com.example.quiz_management_service.dto.QuestionResponseDTO;
import com.example.quiz_management_service.entity.CategoryEntity;
import com.example.quiz_management_service.entity.QuestionEntity;
import com.example.quiz_management_service.exception.CategoryNotFoundException;
import com.example.quiz_management_service.exception.QuestionNotFoundException;
import com.example.quiz_management_service.service.QuestionService;
import com.example.quiz_management_service.util.MapperUtil;

@Service
public class QuestionServiceImpl implements QuestionService {

    private static final Logger logger = LoggerFactory.getLogger(QuestionServiceImpl.class);

    private final QuestionDAO questionDAO;
    
    private final CategoryDAO categoryDAO; 
    
    private final MapperUtil mapperUtil;

    public QuestionServiceImpl(QuestionDAO questionDAO, 
    		CategoryDAO categoryDAO, MapperUtil mapperUtil) {
    	
        this.questionDAO = questionDAO;
        this.categoryDAO = categoryDAO;
        this.mapperUtil = mapperUtil;
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionResponseDTO> getQuestionsByKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            logger.warn("Keyword is null or empty. Returning empty result.");
            return Collections.emptyList();
        }

        // Trim the keyword and escape special characters
        keyword = keyword.trim();
        String safeKeyword = keyword.replaceAll("([%_])", "\\\\$1");

        logger.info("Fetching questions by keyword: {}", safeKeyword);

        List<QuestionEntity> questions = questionDAO.findQuestionsByKeyword(safeKeyword);

        if (questions.isEmpty()) {
            logger.warn("No questions found for keyword: {}", safeKeyword);
            throw new QuestionNotFoundException("No questions found with the given keyword: " + safeKeyword);
        }

        logger.info("Found {} questions for the keyword: {}", questions.size(), safeKeyword);

        return questions.stream()
                .map(mapperUtil::mapToQuestionResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionResponseDTO> getQuestionsByCategory(Long categoryId) {
        logger.info("Fetching questions by category ID: {}", categoryId);
        List<QuestionEntity> questions = questionDAO.findByCategoryId(categoryId);
        if (questions.isEmpty()) {
            throw new QuestionNotFoundException("No questions found for the given category.");
        }
        return questions.stream()
                .map(mapperUtil::mapToQuestionResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionResponseDTO> getQuestionsByDifficulty(String difficultyLevel) {
        logger.info("Fetching questions by difficulty level: {}", difficultyLevel);
        List<QuestionEntity> questions = questionDAO.findByDifficultyLevel(difficultyLevel);
        if (questions.isEmpty()) {
            throw new QuestionNotFoundException("No questions found for the given difficulty level.");
        }
        return questions.stream()
                .map(mapperUtil::mapToQuestionResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionResponseDTO> getAllQuestionsOrderedByCreatedDate() {
        logger.info("Fetching all questions ordered by creation date");
        List<QuestionEntity> questions = questionDAO.findAllByOrderByCreatedAtAsc();
        if (questions.isEmpty()) {
            throw new QuestionNotFoundException("No questions found.");
        }
        return questions.stream()
                .map(mapperUtil::mapToQuestionResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionResponseDTO> getRandomQuestionsByCategory(Long categoryId) {
        logger.info("Fetching random questions by category ID: {}", categoryId);
        List<QuestionEntity> questions = questionDAO.findRandomQuestionsByCategory(categoryId);
        if (questions.isEmpty()) {
            throw new QuestionNotFoundException("No questions found for the given category.");
        }
        return questions.stream()
                .map(mapperUtil::mapToQuestionResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuestionResponseDTO> getQuestionsByCategoryAndDifficulty(Long categoryId, String difficulty) {
        logger.info("Fetching questions by category ID: {} and difficulty: {}", categoryId, difficulty);
        List<QuestionEntity> questions = questionDAO.findByCategoryIdAndDifficultyLevel(categoryId, difficulty);
        if (questions.isEmpty()) {
            throw new QuestionNotFoundException("No questions found for the given category and difficulty.");
        }
        return questions.stream()
                .map(mapperUtil::mapToQuestionResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public QuestionResponseDTO addQuestion(QuestionRequestDTO questionRequestDTO) {
        logger.info("Adding new question: {}", questionRequestDTO);

        // Validate input (could use @Valid on DTO)
        if (questionRequestDTO.getCategoryId() == null) {
            throw new IllegalArgumentException("Category ID must not be null.");
        }

        // Fetch the CategoryEntity using the categoryId from the request
        CategoryEntity category = categoryDAO.findById(questionRequestDTO.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found for ID: " + questionRequestDTO.getCategoryId()));

        // Map the DTO to QuestionEntity and set the category
        QuestionEntity questionEntity = mapperUtil.map(questionRequestDTO, QuestionEntity.class);
        questionEntity.setCategory(category);

        // Save the question
        try {
            QuestionEntity savedEntity = questionDAO.save(questionEntity);
            logger.info("Question added successfully with ID: {}", savedEntity.getId());
            return mapperUtil.map(savedEntity, QuestionResponseDTO.class);
        } catch (DataIntegrityViolationException e) {
            logger.error("Data integrity violation occurred while adding the question: {}", questionRequestDTO, e);
            throw new RuntimeException("Error occurred while adding the question due to data integrity violation.");
        } catch (Exception e) {
            logger.error("Unexpected error occurred while adding the question: {}", questionRequestDTO, e);
            throw new RuntimeException("Error occurred while adding the question.");
        }
    }

    @Override
    @Transactional
    public void deleteQuestion(Long questionId) {
        logger.info("Deleting question with ID: {}", questionId);
        questionDAO.deleteById(questionId);
        logger.info("Question deleted successfully with ID: {}", questionId);
    }
}