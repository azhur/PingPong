/**
 * Without Copyright
 */
package com.pingpong.portal;

import com.pingpong.domain.Authority;
import com.pingpong.domain.Player;
import com.pingpong.domain.PlayerAccount;
import com.pingpong.shared.AppService;
import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUserDetailsService;
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 20/03/2012
 */

public class AuthUserDetailsServiceImpl implements GrailsUserDetailsService {
	private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(getClass());

	@Autowired
	private AppService appService;

	/**
	 * Some Spring Security classes (e.g. RoleHierarchyVoter) expect at least one role, so
	 * we give a user with no granted roles this one which gets past that restriction but
	 * doesn't grant anything.
	 */
	private static final List<GrantedAuthority> NO_ROLES = Arrays.asList((GrantedAuthority)new SimpleGrantedAuthority(SpringSecurityUtils.NO_ROLE));

	protected Collection<GrantedAuthority> loadAuthorities(PlayerAccount account, boolean loadRoles) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

		if(loadRoles) {
			for(Authority authority : account.getAuthorities()) {
				grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName().toString()));
			}
			if(grantedAuthorities.isEmpty()) {
				grantedAuthorities = NO_ROLES;
			}
		}
		return grantedAuthorities;
	}

	protected UserDetails createUserDetails(PlayerAccount account, Collection<GrantedAuthority> authorities) {
		final boolean enabled = account.getPlayer().getStatus() == Player.Status.ACTIVE && account.isEnabled();

		return new AuthUser(account.getEmail(), account.getPassword(), enabled, account.getSalt(), account.getPlayer().getName(), account.getId(), account.getPlayer().getId(), authorities);
	}

	@Override
	public UserDetails loadUserByUsername(String username, boolean loadRoles) throws UsernameNotFoundException, DataAccessException {
		final PlayerAccount account = appService.getPlayerAccountByEmail(username);

		if(account == null) {
			log.warn("User not found: $username");
			throw new UsernameNotFoundException(String.format("User not found: %s", username));
		}

		final Collection<GrantedAuthority> authorities = loadAuthorities(account, loadRoles);

		return createUserDetails(account, authorities);
	}

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		return loadUserByUsername(s, true);
	}
}
