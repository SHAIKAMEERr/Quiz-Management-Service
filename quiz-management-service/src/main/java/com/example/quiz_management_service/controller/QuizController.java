package com.example.quiz_management_service.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz_management_service.dto.QuizRequestDTO;
import com.example.quiz_management_service.dto.QuizResponseDTO;
import com.example.quiz_management_service.exception.QuizNotFoundException;
import com.example.quiz_management_service.service.QuizService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/quizzes")
public class QuizController {

    private static final Logger logger = LoggerFactory.getLogger(QuizController.class);

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping
    public ResponseEntity<?> createQuiz(@Valid @RequestBody QuizRequestDTO quizRequestDTO) {
        logger.info("Received request to create quiz: {}", quizRequestDTO);
        try {
            // Call service to create the quiz
            QuizResponseDTO createdQuiz = quizService.createQuiz(quizRequestDTO);
            
            logger.info("Quiz created successfully with ID: {}", createdQuiz.getId());
            
            // Return successful response
            return ResponseEntity.status(HttpStatus.CREATED).body(createdQuiz);
        } catch (IllegalArgumentException e) {
            // Handle validation or input errors
            logger.error("Failed to create quiz due to invalid input: {}", e.getMessage());
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input: " 
            + e.getMessage());
            
        } catch (Exception e) {
            // Handle unexpected errors
            logger.error("Unexpected error occurred while creating quiz: {}"
            		, e.getMessage());
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            		.body("An error occurred while creating the quiz.");
        }
    }


    @GetMapping
    public ResponseEntity<List<QuizResponseDTO>> getAllQuizzes() {
        logger.info("Fetching all quizzes");
        List<QuizResponseDTO> quizzes = quizService.getAllQuizzes();
        
        if (quizzes.isEmpty()) {
            logger.warn("No quizzes found");
        } else {
            logger.info("Fetched {} quizzes", quizzes.size());
        }
        
        return ResponseEntity.ok(quizzes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizResponseDTO> getQuizById(@PathVariable Long id) {
        logger.info("Fetching quiz with ID: {}", id);
        QuizResponseDTO quiz;
        try {
            quiz = quizService.getQuizById(id);
            logger.info("Fetched quiz: {}", quiz);
        } catch (QuizNotFoundException ex) {
            logger.error("Quiz with ID: {} not found", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(quiz);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuizResponseDTO> updateQuiz(@PathVariable Long id, @RequestBody QuizRequestDTO quizRequestDTO) {
        logger.info("Entering updateQuiz() with ID: {} and quizRequestDTO: {}", id, quizRequestDTO);
        QuizResponseDTO updatedQuiz = quizService.updateQuiz(id, quizRequestDTO);
        logger.info("Quiz updated successfully with ID: {}", updatedQuiz.getId());
        return ResponseEntity.ok(updatedQuiz);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id) {
        logger.info("Deleting quiz with ID: {}", id);
        try {
            quizService.deleteQuiz(id);
            logger.info("Quiz deleted successfully with ID: {}", id);
        } catch (Exception e) {
            logger.error("Error occurred while deleting quiz with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.noContent().build();
    }
}
