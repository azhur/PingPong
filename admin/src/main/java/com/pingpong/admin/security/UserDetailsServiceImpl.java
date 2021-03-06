/**
 * Without Copyright
 */
package com.pingpong.admin.security;

import com.pingpong.domain.AdminAccount;
import com.pingpong.domain.Authority;
import com.pingpong.shared.AppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 20/03/2012
 */

public class UserDetailsServiceImpl implements UserDetailsService {
	private static final Logger LOG = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private AppService appService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final AdminAccount account = appService.getAdminAccountByEmail(username);

		if(account == null) {
			LOG.warn("User not found {}", username);
			throw new UsernameNotFoundException(String.format("User not found: %s", username));
		}

		final Collection<SimpleGrantedAuthority> authorities = loadAuthorities(account);

		return createUserDetails(account, authorities);
	}

	protected Collection<SimpleGrantedAuthority> loadAuthorities(AdminAccount account) {
		final List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<SimpleGrantedAuthority>();

		for(Authority authority : account.getAuthorities()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName().toString()));
		}

		return grantedAuthorities;
	}

	protected UserDetails createUserDetails(AdminAccount account, Collection<SimpleGrantedAuthority> authorities) {
		final boolean enabled = account.isEnabled();

		return new AuthUser(account.getEmail(), account.getPassword(), enabled, account.getSalt(), account.getId(), authorities);
	}
}
