package com.example.quiz_management_service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.quiz_management_service.entity.QuizEntity;

@Repository
public interface QuizDAO extends JpaRepository<QuizEntity, Long> {

    @Query("SELECT q FROM QuizEntity q WHERE q.category.id = :categoryId")
    List<QuizEntity> findQuizzesByCategoryId(@Param("categoryId") Long categoryId);

    @Query("SELECT q FROM QuizEntity q WHERE q.title LIKE %:title%")
    List<QuizEntity> findQuizzesByTitle(@Param("title") String title);
    
    
}