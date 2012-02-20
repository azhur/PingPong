/**
 * Copyright U-wiss
 */
package com.pingpong.core.bo.impl;

import com.pingpong.core.bo.AccountBO;
import com.pingpong.core.dao.AccountDAO;
import com.pingpong.domain.Account;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 20/02/2012
 */
@Guarded
public class AccountBOImpl extends AbstractBO<Integer, Account, AccountDAO> implements AccountBO {
	@Override
	public Account getByEmail(@NotNull String email) {
		return getDao().getByEmail(email);
	}
}
