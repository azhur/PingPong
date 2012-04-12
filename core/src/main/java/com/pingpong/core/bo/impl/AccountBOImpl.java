/**
 * Copyright U-wiss
 */
package com.pingpong.core.bo.impl;

import com.pingpong.core.bo.AccountBO;
import com.pingpong.core.dao.AccountDAO;
import com.pingpong.domain.Account;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 20/02/2012
 */
@Guarded
public class AccountBOImpl extends AbstractBO<Integer, Account, AccountDAO> implements AccountBO {
	private static final int SALT_LENGTH = 12;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Account getByEmail(@NotNull String email) {
		return getDao().getByEmail(email);
	}

	@Override
	public void encodePassword(@NotNull Account account) {
		final String salt = RandomStringUtils.randomAlphanumeric(SALT_LENGTH);
		final String encodedPassword = passwordEncoder.encodePassword(account.getPassword(), salt);

		account.setPassword(encodedPassword);
		account.setSalt(salt);
	}
}
