package com.example.quiz_management_service.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.quiz_management_service.entity.CategoryEntity;

@Repository
public interface CategoryDAO extends JpaRepository<CategoryEntity, Long> {

    @Query("SELECT c FROM CategoryEntity c WHERE c.name LIKE %:name%")
    List<CategoryEntity> findCategoriesByName(@Param("name") String name);
    
    Optional<CategoryEntity> findByName(String name);

    boolean existsByName(String name);

}