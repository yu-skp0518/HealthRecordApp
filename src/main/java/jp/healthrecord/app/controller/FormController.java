package jp.healthrecord.app.controller;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jp.healthrecord.app.HealthRecordApplication;
import jp.healthrecord.app.dto.UserForm;

@Controller
public class FormController {
	
	private final HealthRecordApplication healthRecordApplication;

	FormController(HealthRecordApplication healthRecordApplication) {
		this.healthRecordApplication = healthRecordApplication;
	}

	/*
	 * 初期表示
	 * */
	@GetMapping("/")
	public String showForm(Model model) {

		/* もしログイン画面等があって、ユーザーが特定できるなら
		 * 事前に入力できる部分は入力してあげることもできる */
		model.addAttribute("userForm", new UserForm());
		
	    return "index";
	}

	/*
	 * フォーム送信
	 * */
    @PostMapping("/submit")
    public String submit(
    		@Valid @ModelAttribute("userForm") UserForm userform,
    		BindingResult bindingResult,
    		Model model) {
    	
    	if(bindingResult.hasErrors()) {
    		return "index";
    	}
    	
    	model.addAttribute("fullName", userform.getLastName() + " " + userform.getFirstName());
        return "result";
    }
}
