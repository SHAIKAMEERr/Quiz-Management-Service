package com.example.quiz_management_service.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DifficultyLevel {
    EASY, MEDIUM, HARD, VERY_HARD;

    @JsonCreator
    public static DifficultyLevel fromValue(String value) {
        return DifficultyLevel.valueOf(value.toUpperCase());
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}
