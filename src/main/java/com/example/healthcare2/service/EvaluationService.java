package com.example.healthcare2.service;

import com.example.healthcare2.entity.Evaluation;

public interface EvaluationService {
	
	Evaluation findEvaluationsByUserId(int userId);
	
	void insertEvaluation(Evaluation evaluation);
	
	void updateEvaluation(Evaluation evaluation);

}
