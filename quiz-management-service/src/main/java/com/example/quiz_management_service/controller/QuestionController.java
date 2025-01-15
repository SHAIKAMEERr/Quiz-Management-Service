package com.example.quiz_management_service.controller;

import java.util.List;

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

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<QuestionResponseDTO>> getQuestionsByKeyword(@RequestParam String keyword) {
        List<QuestionResponseDTO> questions = questionService.getQuestionsByKeyword(keyword);
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<QuestionResponseDTO>> getQuestionsByCategory(@PathVariable Long categoryId) {
        List<QuestionResponseDTO> questions = questionService.getQuestionsByCategory(categoryId);
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/difficulty/{difficultyLevel}")
    public ResponseEntity<List<QuestionResponseDTO>> getQuestionsByDifficulty(@PathVariable String difficultyLevel) {
        List<QuestionResponseDTO> questions = questionService.getQuestionsByDifficulty(difficultyLevel);
        return ResponseEntity.ok(questions);
    }

    // Endpoint to retrieve all questions ordered by created date
    @GetMapping("/ordered")
    public ResponseEntity<List<QuestionResponseDTO>> getAllQuestionsOrderedByCreatedDate() {
        List<QuestionResponseDTO> questions = questionService.getAllQuestionsOrderedByCreatedDate();
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/random/category/{categoryId}")
    public ResponseEntity<List<QuestionResponseDTO>> getRandomQuestionsByCategory(@PathVariable Long categoryId) {
        List<QuestionResponseDTO> questions = questionService.getRandomQuestionsByCategory(categoryId);
        return ResponseEntity.ok(questions);
    }

    // Endpoint to retrieve questions by category and difficulty level
    @GetMapping("/category/{categoryId}/difficulty/{difficultyLevel}")
    public ResponseEntity<List<QuestionResponseDTO>> getQuestionsByCategoryAndDifficulty(
            @PathVariable Long categoryId, @PathVariable String difficultyLevel) {
        List<QuestionResponseDTO> questions = questionService.getQuestionsByCategoryAndDifficulty(categoryId, difficultyLevel);
        return ResponseEntity.ok(questions);
    }

    // Endpoint to add a new question
    @PostMapping
    public ResponseEntity<QuestionResponseDTO> addQuestion(@RequestBody QuestionRequestDTO questionRequestDTO) {
        QuestionResponseDTO createdQuestion = questionService.addQuestion(questionRequestDTO);
        return ResponseEntity.ok(createdQuestion);
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long questionId) {
        questionService.deleteQuestion(questionId);
        return ResponseEntity.noContent().build();
    }
}