package com.example.healthcare2.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.healthcare2.entity.UserEntity;

@Mapper
public interface UserMapper {
	
	UserEntity findByUsername(String username);
	
	UserEntity findByEmail(String email);
	
	void insertUserEntity(UserEntity user);

}
