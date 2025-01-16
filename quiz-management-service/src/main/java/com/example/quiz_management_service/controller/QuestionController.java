package com.example.quiz_management_service.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz_management_service.dto.QuestionRequestDTO;
import com.example.quiz_management_service.dto.QuestionResponseDTO;
import com.example.quiz_management_service.service.QuestionService;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<QuestionResponseDTO>> getQuestionsByKeyword(@RequestParam String keyword) {
        logger.info("Received request to search questions with keyword: {}", keyword);
        try {
            List<QuestionResponseDTO> questions = questionService.getQuestionsByKeyword(keyword);
            logger.info("Successfully retrieved {} questions for keyword: {}", questions.size(), keyword);
            return ResponseEntity.ok(questions);
        } catch (Exception e) {
            logger.error("Error occurred while searching questions with keyword: {}", keyword, e);
            throw e;
        }
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<QuestionResponseDTO>> getQuestionsByCategory(@PathVariable Long categoryId) {
        logger.info("Received request to get questions by category ID: {}", categoryId);
        try {
            List<QuestionResponseDTO> questions = questionService.getQuestionsByCategory(categoryId);
            logger.info("Successfully retrieved {} questions for category ID: {}", questions.size(), categoryId);
            return ResponseEntity.ok(questions);
        } catch (Exception e) {
            logger.error("Error occurred while retrieving questions by category ID: {}", categoryId, e);
            throw e;
        }
    }

    @GetMapping("/difficulty/{difficultyLevel}")
    public ResponseEntity<List<QuestionResponseDTO>> getQuestionsByDifficulty(@PathVariable String difficultyLevel) {
        logger.info("Received request to get questions by difficulty level: {}", difficultyLevel);
        try {
            List<QuestionResponseDTO> questions = questionService.getQuestionsByDifficulty(difficultyLevel);
            logger.info("Successfully retrieved {} questions for difficulty level: {}", questions.size(), difficultyLevel);
            return ResponseEntity.ok(questions);
        } catch (Exception e) {
            logger.error("Error occurred while retrieving questions by difficulty level: {}", difficultyLevel, e);
            throw e;
        }
    }

    @GetMapping("/ordered")
    public ResponseEntity<List<QuestionResponseDTO>> getAllQuestionsOrderedByCreatedDate() {
        logger.info("Received request to get all questions ordered by created date");
        try {
            List<QuestionResponseDTO> questions = questionService.getAllQuestionsOrderedByCreatedDate();
            logger.info("Successfully retrieved {} questions ordered by created date", questions.size());
            return ResponseEntity.ok(questions);
        } catch (Exception e) {
            logger.error("Error occurred while retrieving questions ordered by created date", e);
            throw e;
        }
    }

    @GetMapping("/random/category/{categoryId}")
    public ResponseEntity<List<QuestionResponseDTO>> getRandomQuestionsByCategory(@PathVariable Long categoryId) {
        logger.info("Received request to get random questions for category ID: {}", categoryId);
        try {
            List<QuestionResponseDTO> questions = questionService.getRandomQuestionsByCategory(categoryId);
            logger.info("Successfully retrieved {} random questions for category ID: {}", questions.size(), categoryId);
            return ResponseEntity.ok(questions);
        } catch (Exception e) {
            logger.error("Error occurred while retrieving random questions for category ID: {}", categoryId, e);
            throw e;
        }
    }

    @GetMapping("/category/{categoryId}/difficulty/{difficultyLevel}")
    public ResponseEntity<List<QuestionResponseDTO>> getQuestionsByCategoryAndDifficulty(
            @PathVariable Long categoryId, @PathVariable String difficultyLevel) {
        logger.info("Received request to get questions for category ID: {} and difficulty level: {}", categoryId, difficultyLevel);
        try {
            List<QuestionResponseDTO> questions = questionService.getQuestionsByCategoryAndDifficulty(categoryId, difficultyLevel);
            logger.info("Successfully retrieved {} questions for category ID: {} and difficulty level: {}", questions.size(), categoryId, difficultyLevel);
            return ResponseEntity.ok(questions);
        } catch (Exception e) {
            logger.error("Error occurred while retrieving questions for category ID: {} and difficulty level: {}", categoryId, difficultyLevel, e);
            throw e;
        }
    }

    @PostMapping
    public ResponseEntity<QuestionResponseDTO> addQuestion(@RequestBody QuestionRequestDTO questionRequestDTO) {
        logger.info("Received request to add a new question: {}", questionRequestDTO);
        try {
            QuestionResponseDTO createdQuestion = questionService.addQuestion(questionRequestDTO);
            logger.info("Successfully added a new question with ID: {}", createdQuestion.getId());
            return ResponseEntity.ok(createdQuestion);
        } catch (Exception e) {
            logger.error("Error occurred while adding a new question", e);
            throw e;
        }
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long questionId) {
        logger.info("Received request to delete question with ID: {}", questionId);
        try {
            questionService.deleteQuestion(questionId);
            logger.info("Successfully deleted question with ID: {}", questionId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Error occurred while deleting question with ID: {}", questionId, e);
            throw e;
        }
    }
}
