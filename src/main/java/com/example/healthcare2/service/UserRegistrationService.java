package com.example.healthcare2.service;

import com.example.healthcare2.dto.SignupRequestDto;

public interface UserRegistrationService {
	void registerUser(SignupRequestDto userDto)throws Exception;
}
