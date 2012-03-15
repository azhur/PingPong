/**
 * Copyright U-wiss
 */
package com.pingpong.shared.registration;

import com.pingpong.domain.enumeration.Gender;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 29/01/2012
 */

public class PlayerRegistrationData  implements Serializable {
	private static final long serialVersionUID = 3518498214315728437L;
	@NotBlank(message = "{playerRegistrationCommand.blank.name}")
	private String name;
	@NotBlank(message = "{playerRegistrationCommand.blank.email}")
	@Email(message = "{playerRegistrationCommand.blank.emailFormat}")
	private String email;
	private String password;
	@NotNull(message = "{playerRegistrationCommand.blank.gender}")
	private Gender gender;
	@DateTimeFormat(pattern = "dd/mm/yy")
	@NotNull(message = "{playerRegistrationCommand.blank.birth}")
	@Past
	private LocalDate birth;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public LocalDate getBirth() {
		return birth;
	}

	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}
}
