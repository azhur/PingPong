/**
 * Without Copyright
 */
package com.pingpong.admin.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 20/03/2012
 */

public class AuthUser extends User {
	private static final long serialVersionUID = 9203321947817449398L;

	private String salt;
	private Integer id;

	public AuthUser(String username, String password, boolean enabled, String salt, Integer id, Collection<SimpleGrantedAuthority> authorities){
		super(username, password, enabled, true, true, true, authorities);
		this.salt = salt;
		this.id = id;
	}

	public String getSalt() {
		return salt;
	}

	public Integer getId() {
		return id;
	}
}
