package com.example.healthcare2.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.healthcare2.entity.Evaluation;

@Mapper
public interface EvaluationMapper {
	
	// 情報の取り出し
	Evaluation getEvaluationsByUserId(int userId);
	// 情報の登録
	void insertEvaluation(Evaluation evaluation);
	// 情報のアップデート
	void updateEvaluation(Evaluation evaluation);

}
