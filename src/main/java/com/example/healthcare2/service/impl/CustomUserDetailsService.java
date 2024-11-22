package com.example.healthcare2.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.healthcare2.entity.CustomUserDetails;
import com.example.healthcare2.entity.Role;
import com.example.healthcare2.entity.UserEntity;
import com.example.healthcare2.repository.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	
	private final UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// 「認証テーブル」からデータを取得
		UserEntity user = userMapper.findByUsername(username);
		// 対象データがあれば、UserDetailsの実装クラスを返す
		if (user != null) {
			// 対象データが存在する
			// UserDetailsの実装クラスを返す
			return new CustomUserDetails(user.getUsername(),
					user.getPassword_hash(),
					getAuthorityList(user.getAuthority()),
					user.getEmail(),
					user.getBirth_date(),
					user.getGender());
		} else {
			// 対象データが存在しない
			throw new UsernameNotFoundException(
					username + " => 指定しているユーザー名は存在しません");
		}
	}
	

	/**
	* 権限情報をリストで取得する
	*/
	private List<GrantedAuthority> getAuthorityList(Role role) {
		// 権限リスト
		List<GrantedAuthority> authorities = new ArrayList<>();
		// 列挙型からロールを取得
		authorities.add(new SimpleGrantedAuthority(role.name()));
		// ADMIN ロールの場合、USERの権限も付与
		if (role == Role.ADMIN) {
			authorities.add(
					new SimpleGrantedAuthority(Role.USER.toString()));
		}
		return authorities;
	}
	
	
	
	
	
	
	
	
	
	
	
}