/**
 * Copyright U-wiss
 */
package com.pingpong.core.dao.impl;

import com.pingpong.core.dao.AccountDAO;
import com.pingpong.domain.Account;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 19/02/2012
 */
@Guarded
public class AccountDAOImpl extends AbstractDAO<Integer, Account> implements AccountDAO {
	public AccountDAOImpl() {
		super(Account.class);
	}

	@Override
	public Account getByEmail(@NotNull String email) {
		return (Account)getCurrentSession().createQuery("from Account a where a.email = :email")
				.setParameter("email", email)
				.uniqueResult();
	}
}
