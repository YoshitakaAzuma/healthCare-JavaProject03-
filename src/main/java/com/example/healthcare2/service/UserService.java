package com.example.healthcare2.service;

import com.example.healthcare2.entity.Gender;
import com.example.healthcare2.entity.UserEntity;

public interface UserService {
	
	UserEntity getUserEntityByUserName(String userName);
	
	UserEntity getUserEntityByEmail(String email);
	
	// 年齢の取得
	int getAgeByUsername(String userName);
	// 性別の取得
	Gender getGenderByUsername(String userName);
	
	// 現在ログインしているユーザの名前
	String getCurrentUserName();
	// ログインしているユーザのid
	int getCurrentUserId();
	
	void insertUserEntity(UserEntity user);
	
}
