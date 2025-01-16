package com.example.quiz_management_service.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.example.quiz_management_service.constant.DifficultyLevel;
import com.example.quiz_management_service.constant.QuestionType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "questions")
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private QuizEntity quiz;

    @Column(name = "question_text", nullable = false, columnDefinition = "TEXT")
    private String questionText; 

    @Enumerated(EnumType.STRING)
    @Column(name = "question_type", nullable = false)
    private QuestionType questionType;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt = new Timestamp(new Date().getTime());

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt = new Timestamp(new Date().getTime());

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OptionEntity> options;
    
    @ManyToOne
    @JoinColumn(name = "category_id") // Adjust the column name if different
    private CategoryEntity category;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty_level", nullable = false)
    private DifficultyLevel difficultyLevel; 
    
    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content; 
}
