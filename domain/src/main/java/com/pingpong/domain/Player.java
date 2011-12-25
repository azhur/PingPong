/**
 * Copyright U-wiss
 */
package com.pingpong.domain;


import com.pingpong.domain.enumeration.Gender;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 24/12/2011
 */

@Entity
@Table
public class Player extends AbstractEntity {
	private static final long serialVersionUID = -3951276983281739052L;

	@Column
	private String name;
	@Column
	private String email;
	@Column
	private String login;
	@Column
	private String password;
	@Column
	@Type(type="org.joda.time.contrib.hibernate.PersistentLocalDate")
	private LocalDate birth;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	@Column
	private boolean enabled;


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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getBirth() {
		return birth;
	}

	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
