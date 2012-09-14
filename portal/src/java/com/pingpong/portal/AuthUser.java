/**
 * Without Copyright
 */
package com.pingpong.portal;

import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUser;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 20/03/2012
 */

public class AuthUser extends GrailsUser {
	private static final long serialVersionUID = -7827961343899065936L;
	
	private String salt;
	private String name;
	//private Integer playerId;

	public AuthUser(String username, String password, boolean enabled, String salt, String name, Integer id, /*Integer playerId,*/ Collection<GrantedAuthority> authorities){
		super(username, password, enabled, true, true, true, authorities, id);
		this.salt = salt;
		this.name = name;
		//this.playerId = playerId;
	}

	public String getSalt() {
		return salt;
	}

	public String getName() {
		return name;
	}

	/*public Integer getPlayerId() {
		return playerId;
	}*/
}
