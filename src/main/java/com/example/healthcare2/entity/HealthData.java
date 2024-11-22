package com.example.healthcare2.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class HealthData {
	private int user_id;
	private LocalDate record_date;
	private double weight;
	private double height;
	private int body_fat_percentage;
	private int sBP;
	private int dBP;
	private int steps;
	
	@Override
	public String toString() {
		String s = record_date + ": 身長"+ height + "cm,体重" + weight + "kg,体脂肪率" + body_fat_percentage
				+ "%,最高血圧" + sBP + "最低血圧" + dBP + "この日歩いた歩数" + steps + "歩;";
		return s;
	}
}
