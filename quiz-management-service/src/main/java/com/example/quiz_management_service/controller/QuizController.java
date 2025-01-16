package com.example.quiz_management_service.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.quiz_management_service.dto.QuizRequestDTO;
import com.example.quiz_management_service.dto.QuizResponseDTO;
import com.example.quiz_management_service.service.QuizService;

@RestController
@RequestMapping("/api/v1/quizzes")
public class QuizController {

    private static final Logger logger = LoggerFactory.getLogger(QuizController.class);

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping
    public ResponseEntity<QuizResponseDTO> createQuiz(@RequestBody QuizRequestDTO quizRequestDTO) {
        logger.info("Creating a new quiz: {}", quizRequestDTO);
        QuizResponseDTO createdQuiz = quizService.createQuiz(quizRequestDTO);
        logger.info("Quiz created successfully with ID: {}", createdQuiz.getId());
        return ResponseEntity.ok(createdQuiz);
    }

    @GetMapping
    public ResponseEntity<List<QuizResponseDTO>> getAllQuizzes() {
        logger.info("Fetching all quizzes");
        List<QuizResponseDTO> quizzes = quizService.getAllQuizzes();
        logger.info("Fetched {} quizzes", quizzes.size());
        return ResponseEntity.ok(quizzes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizResponseDTO> getQuizById(@PathVariable Long id) {
        logger.info("Fetching quiz with ID: {}", id);
        QuizResponseDTO quiz = quizService.getQuizById(id);
        logger.info("Fetched quiz: {}", quiz);
        return ResponseEntity.ok(quiz);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuizResponseDTO> updateQuiz(@PathVariable Long id, @RequestBody QuizRequestDTO quizRequestDTO) {
        logger.info("Updating quiz with ID: {}", id);
        QuizResponseDTO updatedQuiz = quizService.updateQuiz(id, quizRequestDTO);
        logger.info("Quiz updated successfully with ID: {}", updatedQuiz.getId());
        return ResponseEntity.ok(updatedQuiz);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id) {
        logger.info("Deleting quiz with ID: {}", id);
        quizService.deleteQuiz(id);
        logger.info("Quiz deleted successfully with ID: {}", id);
        return ResponseEntity.noContent().build();
    }
}