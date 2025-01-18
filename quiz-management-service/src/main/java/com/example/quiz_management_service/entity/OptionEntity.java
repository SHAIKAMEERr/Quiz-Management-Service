package com.example.quiz_management_service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private QuestionEntity question;

    @Column(name = "option_text", nullable = false)
    private String optionText;

    @Column(name = "is_correct")
    private Boolean isCorrect;
}
