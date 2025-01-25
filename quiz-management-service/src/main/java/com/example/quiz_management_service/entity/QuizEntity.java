package com.example.quiz_management_service.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.example.quiz_management_service.constant.DifficultyLevel;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class QuizEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionEntity> questions;

    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty_level", nullable = false)
    private DifficultyLevel difficultyLevel;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
    
    @Version
    private Integer version;

    // Custom setter for difficultyLevel
    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = DifficultyLevel.valueOf(difficultyLevel.toUpperCase());
    }
}
