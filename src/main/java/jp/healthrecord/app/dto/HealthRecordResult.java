package jp.healthrecord.app.dto;

import lombok.Data;

@Data
public class HealthRecordResult {
	
	/* ユーザー名 */
	private String fullName;
	
	/* 身長 */
	private String height;
	
	/* 体重 */
	private String weight;
	
	/* BMI */
	private String bmi;
}
