package com.example.healthcare2.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HealthDataForm {
	private int user_id;
	private double weight;
	private double height;
	private int body_fat_percentage;
	private int sBP;
	private int dBP;
	private int steps;
}
