/**
 * Without Copyright
 */
package com.pingpong.portal.command;

import com.pingpong.domain.enumeration.Gender;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 08/05/2012
 */

public class ChangeProfileCommand implements Serializable {
	private static final long serialVersionUID = 3044145919531356150L;

	private String email;
	@NotBlank(message = "{playerRegistrationCommand.blank.name}")
	private String name;
	@NotNull(message = "{playerRegistrationCommand.blank.gender}")
	private Gender gender;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "{playerRegistrationCommand.blank.birth}")
	private LocalDate birth;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
