package jp.healthrecord.app.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jp.healthrecord.app.constants.MessageConst;
import jp.healthrecord.app.dto.LoginForm;

@Controller
public class LoginController {
	
	// ログインID
	@Value("${app.login.id}")
	private String loginId;
	
	// ログインPASS
	@Value("${app.login.pass}")
	private String loginPassword;

	/*
	 * ログイン画面表示
	 * */
	@GetMapping("/")
	public String showLogin(
			@ModelAttribute LoginForm loginForm) {
		
	    return "login";
	}

	/*
	 * ログイン処理
	 * */
	@PostMapping("/login")
	public String login(
			@Valid @ModelAttribute LoginForm loginForm,
			BindingResult bindingResult,
			Model model,
			HttpSession session) {
		
		// バリデーションチェック
		if(bindingResult.hasErrors()) {
			return "login";
		}

		// ID・PASSWORD照合
		if(!loginId.equals(loginForm.getId()) 
				|| !loginPassword.equals(loginForm.getPassword())) {
			
			model.addAttribute("loginError", MessageConst.LOGIN_FAILED);
			
			return "login";
		}
		
		// ログイン情報をセッションに格納
		session.setAttribute("loginUserId", loginForm.getId());
		
		// 認証成功
		return "redirect:/index";
	}

	/*
	 * ログアウト処理
	 * */
    @PostMapping("/logout")
    public String logout(HttpSession session) {
    	
    	// セッション破棄
    	session.invalidate();
    	
        return "redirect:/";
    }
}
