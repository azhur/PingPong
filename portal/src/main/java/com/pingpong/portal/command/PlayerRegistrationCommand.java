/**
 * Copyright U-wiss
 */
package com.pingpong.portal.command;

import com.pingpong.shared.registration.PlayerRegistrationData;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 31/01/2012
 */

public class PlayerRegistrationCommand extends PlayerRegistrationData{
	private static final long serialVersionUID = -686792982450583340L;
	@NotBlank(message = "{playerRegistrationCommand.blank.password}")
	@Length(min = 6, max = 50, message = "{playerRegistrationCommand.blank.password.size}")
	private String pass1;
	private String pass2;

	public String getPass1() {
		return pass1;
	}

	public void setPass1(String pass1) {
		this.pass1 = pass1;
	}

	public String getPass2() {
		return pass2;
	}

	public void setPass2(String pass2) {
		this.pass2 = pass2;
	}
}
