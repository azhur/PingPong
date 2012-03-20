package com.pingpong.domain;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 29/01/2012
 */

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(
    name="discriminator",
    discriminatorType= DiscriminatorType.STRING
)
@DiscriminatorValue("Plane")
public class Account extends AbstractEntity {
	private static final long serialVersionUID = 2225206884273804694L;

	public static enum Discriminator {
		ADMIN_ACCOUNT,
		PLAYER_ACCOUNT
	}
	@Column
	private String email;
	@Column
	private String password;
	@Column
	private String salt;
	@Column
	private boolean enabled;
	@Enumerated(value = EnumType.STRING)
	private Discriminator discriminator;
	@OneToMany(orphanRemoval = true, mappedBy = "account")
	private Set<Authority> authorities = new HashSet<Authority>();

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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Discriminator getDiscriminator() {
		return discriminator;
	}

	public void setDiscriminator(Discriminator discriminator) {
		this.discriminator = discriminator;
	}

	public Set<Authority> getAuthorities() {
		return authorities;
	}
}
