/**
 * Without Copyright
 */
package com.pingpong.admin.command;

import com.pingpong.shared.Constraints;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 12/04/2012
 */

public class ResetPasswordCommand implements Serializable {
	private static final long serialVersionUID = -8218153121321844058L;

	@NotNull
	private String forgotPasswordId;
	@NotBlank(message = "{password.blank.error}")
	@Length(min = Constraints.MIN_PASSWORD_LENGTH, max = Constraints.MAX_PASSWORD_LENGTH, message = "{password.length.error}")
	private String pass1;
	private String pass2;

	public String getForgotPasswordId() {
		return forgotPasswordId;
	}

	public void setForgotPasswordId(String forgotPasswordId) {
		this.forgotPasswordId = forgotPasswordId;
	}

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
