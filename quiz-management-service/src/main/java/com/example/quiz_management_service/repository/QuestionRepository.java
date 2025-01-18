package com.example.quiz_management_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quiz_management_service.entity.QuestionEntity;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
    
    // Find questions by quiz ID
    List<QuestionEntity> findByQuizId(Long quizId);
    
    // Find questions by question text containing a given string
    List<QuestionEntity> findByQuestionTextContaining(String questionText);
    
}
