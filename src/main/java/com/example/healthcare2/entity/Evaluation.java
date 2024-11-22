package com.example.healthcare2.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evaluation {
	private int user_id;
	private LocalDate record_date;
	private String total;
	private String weight_and_fat;
	private String blood_pressure;
	private String steps;

}
