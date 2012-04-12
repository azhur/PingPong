/**
 * Copyright U-wiss
 */
package com.pingpong.portal.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 20/03/2012
 */

public class AuthUser extends User {
	private static final long serialVersionUID = -7827961343899065936L;
	
	private String salt;
	private String name;

	public AuthUser(String username, String password, boolean enabled, String salt, String name, Collection<SimpleGrantedAuthority> authorities){
		super(username, password, enabled, true, true, true, authorities);
		this.salt = salt;
		this.name = name;
	}

	public String getSalt() {
		return salt;
	}

	public String getName() {
		return name;
	}
}
