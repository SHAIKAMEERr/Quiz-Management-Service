package com.example.quiz_management_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quiz_management_service.entity.CategoryEntity;
import com.example.quiz_management_service.entity.QuestionEntity;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
    List<QuestionEntity> findByQuizId(Long quizId);
    List<QuestionEntity> findByContentContaining(String content);
}