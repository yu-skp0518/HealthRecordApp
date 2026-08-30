package jp.healthrecord.app.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class UserForm {
	
	/* 日付 */
	@NotNull(message="日付を入力してください")
	@PastOrPresent(message="未来の日付は入力できません")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)	
	private LocalDate date;
	
	/* 姓 */
	@NotBlank(message="姓を入力してください")
	private String lastName;

	/* 名 */
	@NotBlank(message="名を入力してください")
	private String firstName;

	/* 生年月日 */
	@NotNull(message="生年月日を入力してください")
	@Past(message="生年月日は過去の日付を入力してください")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate birthDay;
	
	/* 身長 */
	@NotNull(message="身長を入力してください")
	@DecimalMin(value = "50")
	@DecimalMax(value = "250")
	private BigDecimal height;
	
	/* 体重 */
	@NotNull(message="体重を入力してください")
	@DecimalMin(value = "20")
	@DecimalMax(value = "300"	)
	private BigDecimal weight;
}
