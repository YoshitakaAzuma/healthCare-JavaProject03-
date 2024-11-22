package com.example.healthcare2.Helper;

import com.example.healthcare2.entity.HealthData;
import com.example.healthcare2.form.HealthDataForm;

public class HealthDataHelper {
	
	public static HealthData convertHealthData(HealthDataForm form) {
		HealthData data = new HealthData();
		data.setUser_id(form.getUser_id());
		data.setRecord_date(null);
		data.setWeight(form.getWeight());
		data.setHeight(form.getHeight());
		data.setBody_fat_percentage(form.getBody_fat_percentage());
		data.setSBP(form.getSBP());
		data.setDBP(form.getDBP());
		data.setSteps(form.getSteps());
		//test
		System.out.println(data);
		return data;
	}
	
	public static HealthDataForm convertHealthDataForm(HealthData data) {
		HealthDataForm form = new HealthDataForm();
		form.setUser_id(data.getUser_id());
		form.setWeight(data.getWeight());
		form.setHeight(data.getHeight());
		form.setBody_fat_percentage(data.getBody_fat_percentage());
		form.setSBP(data.getSBP());
		form.setDBP(data.getDBP());
		form.setSteps(data.getSteps());
		return form;
	}
	
	public static HealthDataForm dummyHealthDataForm(int userID) {
		HealthDataForm form = new HealthDataForm();
		form.setUser_id(userID);
		form.setHeight(0);
		return form;
	}

	

}
