/**
 * Copyright U-wiss
 */
package com.pingpong.portal.command;

import java.io.Serializable;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 01/02/2012
 */

public class LoginCommand implements Serializable {
	private static final long serialVersionUID = -4965554901440160379L;
	
	private String email;
	private String pass;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
}
