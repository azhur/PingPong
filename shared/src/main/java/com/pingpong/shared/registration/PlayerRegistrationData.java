/**
 * Without Copyright
 */
package com.pingpong.shared.registration;

import com.pingpong.domain.enumeration.Gender;
import org.joda.time.LocalDate;

import java.io.Serializable;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 29/01/2012
 */

public class PlayerRegistrationData  implements Serializable {
	private static final long serialVersionUID = 3518498214315728437L;
	private String name;
	private String email;
	private String password;
	private Gender gender;
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
