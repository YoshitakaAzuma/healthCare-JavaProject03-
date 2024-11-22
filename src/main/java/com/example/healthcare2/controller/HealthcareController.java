package com.example.healthcare2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.healthcare2.Helper.HealthDataHelper;
import com.example.healthcare2.form.HealthDataForm;
import com.example.healthcare2.service.EvaluationService;
import com.example.healthcare2.service.GptService;
import com.example.healthcare2.service.HealthDataService;
import com.example.healthcare2.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class HealthcareController {
	
	private final UserService userService;
	private final HealthDataService healthDataService;
	private final GptService gptService;
	private final EvaluationService evaluationService;
	
	@GetMapping("/")
	public String homePage(Model model) {
		model.addAttribute("evaluation", evaluationService.findEvaluationsByUserId(userService.getCurrentUserId()));
		return "healthcare/home";
	}
	
	@GetMapping("/firstForm")
	public String firstInsert(Model model) {
		model.addAttribute("healthData", HealthDataHelper.dummyHealthDataForm(userService.getCurrentUserId()));
		return "healthcare/regist";
	}
	
	@GetMapping("/form")
	public String insert(Model model) {
		model.addAttribute("healthData", HealthDataHelper.convertHealthDataForm(healthDataService.getHealthDataByUserId(userService.getCurrentUserId())));
		return "healthcare/regist";
	}
	
	@PostMapping("/record")
	public String record(HealthDataForm form,Model model) {
		int id = userService.getCurrentUserId();
		healthDataService.insertHealthData(HealthDataHelper.convertHealthData(form));
		
		//AI操作
		gptService.evaluationAndRecord(id);
		return "redirect:/show";
		//return "healthcare/home";
	}
	
	@GetMapping("/show")
	public String show(Model model) {
		int id = userService.getCurrentUserId();
		model.addAttribute("dateArray", healthDataService.getDateArray(id));
		model.addAttribute("weightArray", healthDataService.getWeightArray(id));
		model.addAttribute("bodyFatPersentageArray", healthDataService.getBodyFatPersentageArray(id));
		model.addAttribute("sbpArray", healthDataService.getSbpArray(id));
		model.addAttribute("dbpArray", healthDataService.getDbpArray(id));
		model.addAttribute("stepsArray", healthDataService.getStepsArray(id));
		
		model.addAttribute("bodyFatMinMax", healthDataService.getMinBodyFatPersentage(id));
		model.addAttribute("weightMinMax", healthDataService.getMinMaxWeight(id));
		
		model.addAttribute("evaluation", evaluationService.findEvaluationsByUserId(id));
		
		return "healthcare/healthdata";
	}
	
	

}
