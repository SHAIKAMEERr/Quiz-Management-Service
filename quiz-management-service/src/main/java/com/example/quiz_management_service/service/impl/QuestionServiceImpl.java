package com.example.quiz_management_service.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.quiz_management_service.dao.QuestionDAO;
import com.example.quiz_management_service.dto.QuestionRequestDTO;
import com.example.quiz_management_service.dto.QuestionResponseDTO;
import com.example.quiz_management_service.entity.QuestionEntity;
import com.example.quiz_management_service.exception.QuestionNotFoundException;
import com.example.quiz_management_service.service.QuestionService;
import com.example.quiz_management_service.util.MapperUtil;

@Service
public class QuestionServiceImpl implements QuestionService {

    private static final Logger logger = LoggerFactory.getLogger(QuestionServiceImpl.class);

    private final QuestionDAO questionDAO;
    private final MapperUtil mapperUtil;

    @Autowired
    public QuestionServiceImpl(QuestionDAO questionDAO, MapperUtil mapperUtil) {
        this.questionDAO = questionDAO;
        this.mapperUtil = mapperUtil;
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionResponseDTO> getQuestionsByKeyword(String keyword) {
        logger.info("Fetching questions by keyword: {}", keyword);
        List<QuestionEntity> questions = questionDAO.findQuestionsByKeyword(keyword);
        if (questions.isEmpty()) {
            throw new QuestionNotFoundException("No questions found with the given keyword.");
        }
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
        QuestionEntity questionEntity = mapperUtil.map(questionRequestDTO, QuestionEntity.class);
        QuestionEntity savedEntity = questionDAO.save(questionEntity);
        logger.info("Question added successfully: {}", savedEntity);
        return mapperUtil.map(savedEntity, QuestionResponseDTO.class);
    }

    @Override
    @Transactional
    public void deleteQuestion(Long questionId) {
        logger.info("Deleting question with ID: {}", questionId);
        questionDAO.deleteById(questionId);
        logger.info("Question deleted successfully with ID: {}", questionId);
    }
}