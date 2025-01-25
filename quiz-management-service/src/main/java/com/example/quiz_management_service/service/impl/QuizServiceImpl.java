package com.example.quiz_management_service.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.quiz_management_service.dao.QuizDAO;
import com.example.quiz_management_service.dto.QuizRequestDTO;
import com.example.quiz_management_service.dto.QuizResponseDTO;
import com.example.quiz_management_service.entity.QuizEntity;
import com.example.quiz_management_service.exception.QuizCreationException;
import com.example.quiz_management_service.exception.QuizNotFoundException;
import com.example.quiz_management_service.repository.QuizRepository;
import com.example.quiz_management_service.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService {
	
    private final QuizDAO quizDAO; 

    private static final Logger logger = LoggerFactory.getLogger(QuizServiceImpl.class);

    private final QuizRepository quizRepository;
    private final ModelMapper modelMapper;

    public QuizServiceImpl(QuizRepository quizRepository, ModelMapper modelMapper,
    		QuizDAO quizDAO) {
        this.quizRepository = quizRepository;
        this.modelMapper = modelMapper;
        this.quizDAO = quizDAO;
    }

    @Override
    @Transactional
    public QuizResponseDTO createQuiz(QuizRequestDTO quizRequestDTO) {
        logger.info("Entering createQuiz() with quizRequestDTO: {}", quizRequestDTO);

        // Map DTO to Entity
        QuizEntity quizEntity = modelMapper.map(quizRequestDTO, QuizEntity.class);

        // Ensure the entity is new and managed
        if (quizEntity.getId() != null) {
            logger.warn("Quiz ID should be null for a new entity. Clearing it.");
            quizEntity.setId(null);
        }

        try {
            // Save the Quiz entity using the injected quizDAO instance
            quizEntity = quizDAO.save(quizEntity);

            if (quizEntity == null) {
                logger.error("Failed to save the quiz entity.");
                throw new QuizCreationException("Failed to create quiz.");
            }

            // Map the saved entity to response DTO
            QuizResponseDTO responseDTO = modelMapper.map(quizEntity, QuizResponseDTO.class);
            logger.info("Successfully created quiz with ID: {}", responseDTO.getId());

            return responseDTO;
        } catch (Exception e) {
            logger.error("Error creating quiz: {}", e.getMessage(), e);
            throw new QuizCreationException("An error occurred while creating the quiz.", e);
        }
    }

    @Override
    public List<QuizResponseDTO> getAllQuizzes() {
        logger.info("Fetching all quizzes");
        List<QuizEntity> quizEntities = quizRepository.findAll();
        if (quizEntities.isEmpty()) {
            logger.warn("No quizzes found in the database");
        } else {
            logger.info("Fetched {} quizzes", quizEntities.size());
        }
        return quizEntities.stream()
                .map(quiz -> modelMapper.map(quiz, QuizResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public QuizResponseDTO getQuizById(Long id) {
        logger.info("Fetching quiz with ID: {}", id);
        QuizEntity quizEntity = quizRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Quiz with ID: {} not found", id);
                    return new QuizNotFoundException("Quiz not found with ID: " + id);
                });
        logger.info("Successfully fetched quiz with ID: {}", id);
        return modelMapper.map(quizEntity, QuizResponseDTO.class);
    }

    @Override
    @Transactional
    public QuizResponseDTO updateQuiz(Long id, QuizRequestDTO quizRequestDTO) {
        logger.info("Updating quiz with ID: {}", id);
        QuizEntity existingQuiz = quizRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Quiz with ID: {} not found for update", id);
                    return new QuizNotFoundException("Quiz not found with ID: " + id);
                });

        logger.info("Existing quiz details: {}", existingQuiz);
        existingQuiz.setTitle(quizRequestDTO.getTitle());
        existingQuiz.setDescription(quizRequestDTO.getDescription());
        existingQuiz.setDifficultyLevel(quizRequestDTO.getDifficultyLevel());
        existingQuiz.setCategory(modelMapper.map(quizRequestDTO.getCategoryId(),
        		existingQuiz.getCategory().getClass()));

        QuizEntity updatedQuiz = quizRepository.save(existingQuiz);
        logger.info("Quiz with ID: {} updated successfully", id);
        return modelMapper.map(updatedQuiz, QuizResponseDTO.class);
    }

    @Override
    @Transactional
    public void deleteQuiz(Long id) {
        logger.info("Deleting quiz with ID: {}", id);
        QuizEntity quizEntity = quizRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Quiz with ID: {} not found for deletion", id);
                    return new QuizNotFoundException("Quiz not found with ID: " + id);
                });
        quizRepository.delete(quizEntity);
        logger.info("Successfully deleted quiz with ID: {}", id);
    }
}
