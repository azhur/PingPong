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

	public static enum Status {
		REGISTRATION,
		ACTIVE,
		BLOCKED
	}

	@Column
	private String name;
	@Column
	@Type(type="org.joda.time.contrib.hibernate.PersistentLocalDate")
	private LocalDate birth;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	@Column
	private boolean enabled;
	@Enumerated(EnumType.STRING)
	private Status status;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
