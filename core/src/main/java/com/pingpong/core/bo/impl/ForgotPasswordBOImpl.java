/**
 * Without Copyright
 */
package com.pingpong.core.bo.impl;

import com.pingpong.core.bo.AccountBO;
import com.pingpong.core.bo.ForgotPasswordBO;
import com.pingpong.core.dao.ForgotPasswordDAO;
import com.pingpong.domain.Account;
import com.pingpong.domain.ForgotPassword;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 12/04/2012
 */
@Guarded
public class ForgotPasswordBOImpl extends AbstractBO<String, ForgotPassword, ForgotPasswordDAO> implements ForgotPasswordBO {
	@Autowired
	private AccountBO accountBO;

	@Override
	@Transactional(readOnly = false)
	public String createForAccount(@NotNull Integer accountId) {
		final Account account = accountBO.getById(accountId);
		checkNotNull(account, "Can't find account with id " + accountId);

		final ForgotPassword remindPassword = new ForgotPassword();
		remindPassword.setAccount(account);
		remindPassword.setValidTill(new LocalDateTime().plusDays(1));

		return insert(remindPassword);
	}

	@Override
	@Transactional(readOnly = false)
	public void cleanup() {
		getDao().cleanup();
	}
}
