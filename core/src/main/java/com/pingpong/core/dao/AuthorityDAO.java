package com.pingpong.core.dao;

import com.pingpong.domain.Account;
import com.pingpong.domain.Authority;
import net.sf.oval.constraint.NotNull;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 25/12/2011
 */

public interface AuthorityDAO extends DAO<Integer,Authority> {
	@NotNull
	Authority create(@NotNull Account account, @NotNull Authority.Name name);
}
