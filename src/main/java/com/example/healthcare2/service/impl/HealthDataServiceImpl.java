package com.example.healthcare2.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.healthcare2.entity.HealthData;
import com.example.healthcare2.repository.HealthDataMapper;
import com.example.healthcare2.service.HealthDataService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HealthDataServiceImpl implements HealthDataService{
	
	private final HealthDataMapper healthDataMapper;
	
	@Override
	public List<HealthData> getHealthDataListByUserId(int userId) {
		// ユーザの最初のデータ記録日を取得
		LocalDate firstDate = healthDataMapper.findFirstDateByUserId(userId);
		// 今日から30日前の日付を取得
		LocalDate thirtyDaysAgo = LocalDate.now().minusDays(30);
		
		LocalDate pointDate;
		if(thirtyDaysAgo.isAfter(firstDate)) {
			pointDate = thirtyDaysAgo;
		}else {
			pointDate = firstDate;
		}
		return healthDataMapper.findDataListByUserId(userId,pointDate);
	}
	
	@Override
	public HealthData getHealthDataByUserId(int userId) {
		return healthDataMapper.findLatestDataByUserId(userId);
	}

	@Override
	public void insertHealthData(HealthData data) {
		LocalDate Today = LocalDate.now();
		data.setRecord_date(Today);
		healthDataMapper.insertHealthData(data);
	}

	@Override
	public String[] getDateArray(int userId) {
		List<HealthData> list = getHealthDataListByUserId(userId);
		ArrayList<String> dateList = new ArrayList<>();
		
		for (HealthData data: list) {
			dateList.add(data.getRecord_date().format(DateTimeFormatter.ofPattern("MM/dd")));
		}
		
		String[] dateArray = dateList.toArray(new String[dateList.size()]);
		//test 日付データの確認
		/*for(String s: dateArray) {
			System.out.println(s);
		}*/
		return dateArray;
	}

	@Override
	public double[] getWeightArray(int userId) {
		List<HealthData> list = getHealthDataListByUserId(userId);
		ArrayList<Double> weightList = new ArrayList<>();
		for (HealthData data: list) {
			weightList.add(data.getWeight());
		}
		double[] weightArray = new double[weightList.size()];
		for (int i = 0; i < weightList.size();i++) {
			weightArray[i] = weightList.get(i);
		}
		//test 日付データの確認
		/*for(double s: weightArray) {
			System.out.println(s);
		}*/
		return weightArray;
	}

	@Override
	public int[] getBodyFatPersentageArray(int userId) {
		List<HealthData> list = getHealthDataListByUserId(userId);
		ArrayList<Integer> bodyFatPersentageList = new ArrayList<>();
		for (HealthData data: list) {
			bodyFatPersentageList.add(data.getBody_fat_percentage());
		}
		int[] bodyFatPersentageArray = new int[bodyFatPersentageList.size()];
		for (int i = 0; i < bodyFatPersentageList.size();i++) {
			bodyFatPersentageArray[i] = bodyFatPersentageList.get(i);
		}
		//test 体脂肪率データの確認
		/*for (int data:bodyFatPersentageArray) {
			System.out.println(data);
		}*/
		return bodyFatPersentageArray;
	}

	@Override
	public int[] getSbpArray(int userId) {
		List<HealthData> list = getHealthDataListByUserId(userId);
		ArrayList<Integer> sbpList = new ArrayList<>();
		for (HealthData data: list) {
			sbpList.add(data.getSBP());
		}
		int[] sbpArray = new int[sbpList.size()];
		for (int i = 0; i < sbpList.size();i++) {
			sbpArray[i] = sbpList.get(i);
		}
		
		//test 最高血圧データの確認
		/*for (int data:sbpArray) {
			System.out.println("sBP:" + data);
		}*/
		return sbpArray;
	}

	@Override
	public int[] getDbpArray(int userId) {
		List<HealthData> list = getHealthDataListByUserId(userId);
		ArrayList<Integer> dbpList = new ArrayList<>();
		for (HealthData data: list) {
			dbpList.add(data.getDBP());
		}
		int[] dbpArray = new int[dbpList.size()];
		for (int i = 0; i < dbpList.size();i++) {
			dbpArray[i] = dbpList.get(i);
		}
		//test 最低血圧データの確認
		/*for (int data:dbpArray) {
			System.out.println("dBP" + data);
		}*/
		return dbpArray;
	}

	@Override
	public int[] getStepsArray(int userId) {
		List<HealthData> list = getHealthDataListByUserId(userId);
		ArrayList<Integer> stepsList = new ArrayList<>();
		for (HealthData data: list) {
			stepsList.add(data.getSteps());
		}
		int[] stepsArray = new int[stepsList.size()];
		for (int i = 0; i < stepsList.size();i++) {
			stepsArray[i] = stepsList.get(i);
		}
		//test 歩数データの確認
		/*for (int data:stepsArray) {
			System.out.println(data);
		}*/
		return stepsArray;
	}
	
	@Override
	public double[] getMinMaxWeight(int userId) {
		double min = Integer.MAX_VALUE;
		double max = Integer.MIN_VALUE;
		for (double i : getWeightArray(userId)) {
			if(i < min) {
				min = i;
			}
			if(i > max) {
				max = i;
			}
		}
		min -= 15;
		if(min < 0) {
			min =0;
		}
		double[] array = {min,max + 5};
		//System.out.println(array[0]+ "最大値"+ array[1]);
		return array;
	}

	@Override
	public int[] getMinBodyFatPersentage(int userId) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i : getBodyFatPersentageArray(userId)) {
			if(i < min) {
				min = i;
			}
			if(i > max) {
				max = i;
			}
		}
		min -= 5;
		if(min < 0) {
			min =0;
		}
		int[] array = {min,max + 5};
		return array;
	}


}
