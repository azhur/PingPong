/**
 * Without Copyright
 */
package com.pingpong.admin.command;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 12/04/2012
 */

public class ForgotPasswordCommand implements Serializable {
	private static final long serialVersionUID = 7559525678505160672L;
	@NotBlank(message = "{playerRegistrationCommand.blank.email}")
	@Email(message = "{email.format.error}")
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
