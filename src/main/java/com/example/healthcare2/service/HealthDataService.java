package com.example.healthcare2.service;

import java.util.List;

import com.example.healthcare2.entity.HealthData;

public interface HealthDataService {
	//各データごとにリスト形式で取得
	List<HealthData> getHealthDataListByUserId(int userId);
	// ユーザの最新データを取得
	HealthData getHealthDataByUserId(int userId);
	
	// 日付データの配列
	String[] getDateArray(int userId);
	// 体重データの配列
	double[] getWeightArray(int userId);
	// 体脂肪率データの配列
	int[] getBodyFatPersentageArray(int userId);
	// 最高血圧データの配列
	int[] getSbpArray(int userId);
	// 最低血圧データの配列
	int[] getDbpArray(int userId);
	// 歩数データの配列
	
	int[] getStepsArray(int userId);
	//体重データの最小値最大値取得
	double[] getMinMaxWeight(int userId);
	//体脂肪率データの最小値最大値取得
	int[] getMinBodyFatPersentage(int userId);
	
	
	void insertHealthData(HealthData data);
	
	
}
