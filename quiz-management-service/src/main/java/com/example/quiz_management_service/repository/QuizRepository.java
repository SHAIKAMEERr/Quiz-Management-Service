package com.example.quiz_management_service.repository;

import com.example.quiz_management_service.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<QuizEntity, Long> {
    List<QuizEntity> findByCategoryId(Long categoryId);
    List<QuizEntity> findByTitleContaining(String title);
}