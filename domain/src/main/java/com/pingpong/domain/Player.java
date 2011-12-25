/**
 * Copyright U-wiss
 */
package com.pingpong.domain;


import com.pingpong.domain.enumeration.Gender;

import javax.persistence.*;
import javax.persistence.Entity;
import java.sql.Timestamp;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 24/12/2011
 */

@Entity
@Table
public class Player extends AbstractEntity {
	private static final long serialVersionUID = -3951276983281739052L;
	@Id
	@SequenceGenerator(name = "gen", sequenceName = "player_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
	private Integer id;
	@Column
	private String name;
	@Column
	private String email;
	@Column
	private String login;
	@Column
	private String password;
	/*@Column
	@Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
	private LocalDate birth;*/
	@Enumerated(EnumType.STRING)
	private Gender gender;
	@Column
	private boolean enabled;
	@Version
	@Column(name = "version")
	private Timestamp version;


	public Timestamp getVersion() {
		return version;
	}

	public void setVersion(Timestamp version) {
		this.version = version;
	}


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

	/*public LocalDate getBirth() {
		return birth;
	}

	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}*/

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

	public Integer getId() {
		return null;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
