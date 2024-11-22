package com.example.healthcare2.dto;

import java.time.LocalDate;

import com.example.healthcare2.entity.Gender;

import lombok.Data;

@Data
public class SignupRequestDto {
	
	
	private String username;
	
	private String email;
	
	private LocalDate birth_date;
	
	private Gender gender;
	
	private String password;
	
	private String passwordConfirm;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
