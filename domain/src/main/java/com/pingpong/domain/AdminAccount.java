package com.pingpong.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 29/01/2012
 */
@Entity
@DiscriminatorValue(value = "ADMIN_ACCOUNT")
public class AdminAccount extends Account{
	private static final long serialVersionUID = 7973114071404139089L;

	public AdminAccount() {
		setDiscriminator(Discriminator.ADMIN_ACCOUNT);
	}
}
