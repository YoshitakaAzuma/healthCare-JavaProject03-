package com.example.healthcare2.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.healthcare2.dto.SignupRequestDto;
import com.example.healthcare2.entity.UserEntity;
import com.example.healthcare2.service.UserRegistrationService;
import com.example.healthcare2.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserRegistrationServiceImpl implements UserRegistrationService{
	
	private final UserService userService;
    private final PasswordEncoder passwordEncoder;
	
	@Override
	public void registerUser(SignupRequestDto userDto) throws Exception {
		// 二重登録のチェック
        if (userService.getUserEntityByUserName(userDto.getUsername()) != null) {
        	System.out.println("名前重複");
            throw new Exception("ユーザ名が既に存在します。");
        }
        if (userService.getUserEntityByEmail(userDto.getEmail()) != null) {
        	System.out.println("メールアドレス重複");
        	throw new Exception("メールアドレスが既に存在します。");
        }
     // パスワード一致のチェック
        if (!userDto.getPassword().equals(userDto.getPasswordConfirm())) {
        	System.out.println("パスワード不一致");
            throw new Exception("パスワードと確認用パスワードが一致しません。");
        }
     // 新しいユーザーエンティティの作成と保存
        UserEntity user = new UserEntity();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setBirth_date(userDto.getBirth_date());
        user.setGender(userDto.getGender());
        // パスワードをハッシュ化してセットする
        user.setPassword_hash(passwordEncoder.encode(userDto.getPassword()));
        
        //System.out.println(user.toString());
        
        
        userService.insertUserEntity(user);
        
        
	}

}
