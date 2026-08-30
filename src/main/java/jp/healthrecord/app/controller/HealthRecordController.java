package jp.healthrecord.app.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.healthrecord.app.dto.HealthRecordResult;
import jp.healthrecord.app.dto.UserForm;
import jp.healthrecord.app.service.HealthRecordService;

@Controller
public class HealthRecordController {
	
	/* 健康記録サービスクラス */
	private final HealthRecordService service;
	
    public HealthRecordController(HealthRecordService service) {
        this.service = service;
    }
	

	@GetMapping("/index")
	public String showInput(
			@ModelAttribute UserForm userForm,
			HttpSession session) {
		
		if (session.getAttribute("loginUserId") == null) {
		    return "redirect:/";
		}
		
		/* 初期値設定 */
		service.initializeForm(userForm);
		
		return "index";
	}	

	/*
	 * フォーム送信
	 * */
    @PostMapping("/submit")
    public String submit(
    		@Valid @ModelAttribute UserForm userForm,
    		BindingResult bindingResult,
    		RedirectAttributes redirectAttributes,
    		HttpSession session) {
    	
		if (session.getAttribute("loginUserId") == null) {
		    return "redirect:/";
		}
    	
    	if(bindingResult.hasErrors()) {
    		return "index";
    	}
    	
    	// 入力値加工処理
    	HealthRecordResult result = service.createResult(userForm);
    	
    	// 結果をセッションに格納
    	session.setAttribute("result", result);
    	
        return "redirect:/result";
    }
    
	/*
	 * 結果画面表示
	 * */
	@GetMapping("/result")
	public String showResult(
			Model model,
			HttpSession session) {
		
		if (session.getAttribute("loginUserId") == null) {
		    return "redirect:/";
		}
		
		// セッションから計算結果を受取
		HealthRecordResult result = (HealthRecordResult)session.getAttribute("result");
		
		// F5対策（resultがない場合は入力画面に遷移）
		if(result == null) {
			return "redirect:/index";
		}
		
		model.addAttribute("result", result);
		
	    return "result";
	}
}
