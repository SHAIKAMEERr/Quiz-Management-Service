package com.example.quiz_management_service.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.quiz_management_service.dao.QuizDAO;
import com.example.quiz_management_service.dto.QuizRequestDTO;
import com.example.quiz_management_service.dto.QuizResponseDTO;
import com.example.quiz_management_service.entity.QuizEntity;
import com.example.quiz_management_service.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuizDAO quizDAO;
    private final ModelMapper modelMapper;

    public QuizServiceImpl(QuizDAO quizDAO, ModelMapper modelMapper) {
        this.quizDAO = quizDAO;
        this.modelMapper = modelMapper;
    }

    @Override
    public QuizResponseDTO createQuiz(QuizRequestDTO quizRequestDTO) {
        QuizEntity quizEntity = modelMapper.map(quizRequestDTO, QuizEntity.class);
        quizEntity = quizDAO.save(quizEntity);
        return modelMapper.map(quizEntity, QuizResponseDTO.class);
    }

    @Override
    public QuizResponseDTO getQuizById(Long id) {
        QuizEntity quizEntity = quizDAO.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found"));
        return modelMapper.map(quizEntity, QuizResponseDTO.class);
    }

    @Override
    public List<QuizResponseDTO> getAllQuizzes() {
        List<QuizEntity> quizEntities = quizDAO.findAll();
        return quizEntities.stream()
                .map(entity -> modelMapper.map(entity, QuizResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public QuizResponseDTO updateQuiz(Long id, QuizRequestDTO quizRequestDTO) {
        QuizEntity existingQuiz = quizDAO.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found"));
        modelMapper.map(quizRequestDTO, existingQuiz);
        existingQuiz = quizDAO.save(existingQuiz);
        return modelMapper.map(existingQuiz, QuizResponseDTO.class);
    }

    @Override
    public void deleteQuiz(Long id) {
        quizDAO.deleteById(id);
    }
}