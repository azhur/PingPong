package com.pingpong.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 29/01/2012
 */
@Entity
public class Authority  extends AbstractEntity {
	private static final long serialVersionUID = -3309855143494569800L;
	
	public static enum Name {
		ROLE_ADMIN_USER,
		ROLE_PLAYER_USER
	}
	
	@Enumerated(EnumType.STRING)
	private Name name;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Account account;

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
