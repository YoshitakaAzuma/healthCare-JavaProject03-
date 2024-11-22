package com.example.healthcare2.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.healthcare2.entity.Gender;
import com.example.healthcare2.entity.UserEntity;
import com.example.healthcare2.repository.UserMapper;
import com.example.healthcare2.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserMapper userMapper;

	
	@Override
	public UserEntity getUserEntityByUserName(String userName) {
		return userMapper.findByUsername(userName);
	}
	
	@Override
	public UserEntity getUserEntityByEmail(String email) {
		return userMapper.findByEmail(email);
	}
	
	@Override
	public int getAgeByUsername(String userName) {
		// 年齢計算
        int age = Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy"))) - Integer.parseInt(userMapper.findByUsername(userName).getBirth_date().format(DateTimeFormatter.ofPattern("yyyy")));
              
        if (Integer.parseInt(userMapper.findByUsername(userName).getBirth_date().format(DateTimeFormatter.ofPattern("MM"))) > Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("MM")))) {
        	age--;
        } else if (Integer.parseInt(userMapper.findByUsername(userName).getBirth_date().format(DateTimeFormatter.ofPattern("MM"))) == Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("MM")))) {
        	if(Integer.parseInt(userMapper.findByUsername(userName).getBirth_date().format(DateTimeFormatter.ofPattern("dd"))) > Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("dd")))) {
        		age--;
        	}
        }
		return age;
	}

	@Override
	public Gender getGenderByUsername(String userName) {
		return userMapper.findByUsername(userName).getGender();
	}

	@Override
	public String getCurrentUserName() {
		final String name = SecurityContextHolder.getContext().getAuthentication().getName();
		if(name == null) {
			System.out.println("username取得失敗");
			return "Noname";
		}
		//System.out.println("取得成功");
		return name;
	}

	@Override
	public int getCurrentUserId() {
		return userMapper.findByUsername(getCurrentUserName()).getId();
	}

	
	@Override
	public void insertUserEntity(UserEntity user) {
		userMapper.insertUserEntity(user);
	}

}
