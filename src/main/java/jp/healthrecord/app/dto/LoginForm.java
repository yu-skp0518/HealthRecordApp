package jp.healthrecord.app.dto;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginForm {
	
	/* 会員ID */
	@NotBlank(message="会員IDを入力してください")
	private String id;
	
	/* パスワード */
	@NotBlank(message="パスワードを入力してください")
	private String password;
}
