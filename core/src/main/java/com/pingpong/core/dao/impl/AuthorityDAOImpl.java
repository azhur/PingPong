/**
 * Without Copyright
 */
package com.pingpong.core.dao.impl;

import com.pingpong.core.dao.AuthorityDAO;
import com.pingpong.domain.Account;
import com.pingpong.domain.Authority;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 19/02/2012
 */
@Guarded
public class AuthorityDAOImpl extends AbstractDAO<Integer, Authority> implements AuthorityDAO {
	public AuthorityDAOImpl() {
		super(Authority.class);
	}

	@Override
	@Transactional(readOnly = false)
	@NotNull
	public Authority create(@NotNull Account account, @NotNull Authority.Name name) {
		final Authority authority = new Authority();
		authority.setAccount(account);
		authority.setName(name);

		insert(authority);

		return authority;
	}
}
