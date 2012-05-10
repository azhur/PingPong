/**
 * Copyright U-wiss
 */
package com.pingpong.admin.security;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 12/04/2012
 */

public class SpringSecurityUtils  {
	private SpringSecurityUtils() {
	}

	public static AuthUser getCurrentUser() {
		final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		return principal instanceof AuthUser ? (AuthUser)principal : null;
	}

	public static boolean isLoggedIn() {
		return getCurrentUser() != null;
	}
}
