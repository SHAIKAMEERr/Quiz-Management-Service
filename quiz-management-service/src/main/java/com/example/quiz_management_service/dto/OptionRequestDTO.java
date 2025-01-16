package com.example.quiz_management_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionRequestDTO {

    private Long id;

    private String optionText;

    private boolean isCorrect;

}