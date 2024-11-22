package com.example.healthcare2.entity;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUserDetails extends User {
	// 【追加部分】追加のフィールド
		private String email;
		private LocalDate birthDate ;
		private Gender gender; 
		
		
	public CustomUserDetails(String username, 
			String password,
			Collection<? extends GrantedAuthority> authorities,
			String email,
			LocalDate birthDate,
			Gender gender) {
		super(username, password, authorities);
		this.email = email;
		this.birthDate = birthDate;
		this.gender = gender;
	}
	
	//【追加部分】
		public String getEmail() {
			return email;
		}
	
		public LocalDate getDisplayname() {
			return birthDate;
		}
		
		public Gender getGender() {
			return gender;
		}
		
}
