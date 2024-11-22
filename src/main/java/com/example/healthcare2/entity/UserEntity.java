package com.example.healthcare2.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserEntity {
	private int id;
    private String username;
    private String email;
    private String password_hash;
    private Role authority;
    private LocalDate birth_date;
    private Gender gender;
}
