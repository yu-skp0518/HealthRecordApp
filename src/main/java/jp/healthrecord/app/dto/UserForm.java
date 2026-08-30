package jp.healthrecord.app.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	
	/* 生年月日（年）*/
	@NotNull(message="生年月日の年を入力してください")
	@Min(value=1900, message="年を正しく入力してください")
	private Integer birthYear;

	/* 生年月日（月）*/
	@NotNull(message="生年月日の月を入力してください")
	@Min(value=1)
	@Max(value=12)
	private Integer birthMonth;
	
	/* 生年月日（日）*/
	@NotNull(message="生年月日の日を入力してください")
	@Min(value=1)
	@Max(value=31)
	private Integer birthDay;
	
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
