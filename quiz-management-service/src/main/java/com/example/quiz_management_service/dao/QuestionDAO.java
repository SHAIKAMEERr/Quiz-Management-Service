package com.example.quiz_management_service.dao;

import com.example.quiz_management_service.entity.QuestionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionDAO extends JpaRepository<QuestionEntity, Long> {

    // 1. Find questions by a keyword (case-insensitive search)
    @Query("SELECT q FROM QuestionEntity q WHERE LOWER(q.questionText) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<QuestionEntity> findQuestionsByKeyword(@Param("keyword") String keyword);

    // 2. Find questions by category ID
    @Query("SELECT q FROM QuestionEntity q WHERE q.category.id = :categoryId")
    List<QuestionEntity> findByCategoryId(@Param("categoryId") Long categoryId);

    // 3. Find questions by difficulty level
    List<QuestionEntity> findByDifficultyLevel(String difficultyLevel);

    // 4. Find questions by a combination of category and difficulty
    @Query("SELECT q FROM QuestionEntity q WHERE q.category.id = :categoryId AND q.difficultyLevel = :difficulty")
    List<QuestionEntity> findByCategoryAndDifficulty(@Param("categoryId") Long categoryId, @Param("difficulty") String difficulty);

    // 5. Fetch all questions ordered by created date
    List<QuestionEntity> findAllByOrderByCreatedAtAsc();

    @Query("SELECT q FROM QuestionEntity q WHERE q.category.id = :categoryId ORDER BY RANDOM()")
    List<QuestionEntity> findRandomQuestionsByCategory(@Param("categoryId") Long categoryId);

    // 7. Get a random set of questions from a specific category with difficulty level
    @Query(value = "SELECT q FROM QuestionEntity q WHERE q.category.id = :categoryId AND q.difficultyLevel = :difficultyLevel ORDER BY FUNCTION('RAND')")
    List<QuestionEntity> findRandomQuestionsByCategoryAndDifficulty(@Param("categoryId") Long categoryId, @Param("difficultyLevel") String difficultyLevel);

    // 8. Get all questions by quiz ID
    @Query("SELECT q FROM QuestionEntity q WHERE q.quiz.id = :quizId")
    List<QuestionEntity> findByQuizId(@Param("quizId") Long quizId);
    
    @Query("SELECT q FROM QuestionEntity q WHERE q.quiz.category.id = :categoryId AND q.difficultyLevel = :difficultyLevel")
    List<QuestionEntity> findByCategoryIdAndDifficultyLevel(@Param("categoryId") Long categoryId, @Param("difficultyLevel") String difficultyLevel);

}
