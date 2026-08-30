package jp.healthrecord.app.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import jp.healthrecord.app.dto.HealthRecordResult;
import jp.healthrecord.app.dto.UserForm;

@Service
public class HealthRecordService {

	/*
	 * 初期値設定
	 * */
	public void initializeForm(UserForm userForm) {
		
		userForm.setDate(LocalDate.now()); // 当日日付
	}
	
	/*
	 * 入力値加工処理
	 * */
	public HealthRecordResult createResult(UserForm userForm) {
		
    	HealthRecordResult result = new HealthRecordResult();
		
		result.setFullName(createFullName(userForm)); // フルネーム
		result.setHeight(userForm.getHeight().stripTrailingZeros().toPlainString()); // 身長
		result.setWeight(userForm.getWeight().stripTrailingZeros().toPlainString()); // 体重
		result.setBmi(calculateBmi(userForm)); // BMI
		
		return result;
	}
	
	/*
	 * 姓名を連結
	 * */
	private String createFullName(UserForm userForm) {
		return userForm.getLastName() + " " + userForm.getFirstName();
	}

	/*
	 * BMIを計算（体重÷（身長の二乗））
	 * */
	private String calculateBmi(UserForm userForm) {
		
		BigDecimal heightMeter =
				userForm.getHeight().movePointLeft(2);
		BigDecimal bmi = userForm.getWeight().divide(
				heightMeter.pow(2), 1, RoundingMode.HALF_UP);
		return bmi.toPlainString();
	}
}
