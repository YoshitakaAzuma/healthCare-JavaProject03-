package com.example.healthcare2.service.impl;

import org.springframework.stereotype.Service;

import com.example.healthcare2.entity.Evaluation;
import com.example.healthcare2.repository.EvaluationMapper;
import com.example.healthcare2.service.EvaluationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EvaluationServiceImpl implements EvaluationService{
	
	private final EvaluationMapper evaluationMapper;

	@Override
	public Evaluation findEvaluationsByUserId(int userId) {
		return evaluationMapper.getEvaluationsByUserId(userId);
	}

	@Override
	public void insertEvaluation(Evaluation evaluation) {
		evaluationMapper.insertEvaluation(evaluation);
	}

	@Override
	public void updateEvaluation(Evaluation evaluation) {
		evaluationMapper.updateEvaluation(evaluation);
	}

}
