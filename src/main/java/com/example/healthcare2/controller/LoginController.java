package com.example.healthcare2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.healthcare2.form.LoginForm;

@Controller
@RequestMapping
public class LoginController {
	
	@GetMapping("/login")
	public String login(@ModelAttribute LoginForm form) {
		return "login";
	}
	
	@GetMapping("/signup")
    public String signup() {
    	return "signup";
    }
	
	
	
	
	
	
	
	
	
}
