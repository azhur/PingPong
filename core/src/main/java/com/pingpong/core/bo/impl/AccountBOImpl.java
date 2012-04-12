/**
 * Copyright U-wiss
 */
package com.pingpong.core.bo.impl;

import com.pingpong.core.bo.AccountBO;
import com.pingpong.core.bo.ForgotPasswordBO;
import com.pingpong.core.dao.AccountDAO;
import com.pingpong.core.mail.Mailer;
import com.pingpong.core.web.UrlResolver;
import com.pingpong.domain.Account;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 20/02/2012
 */
@Guarded
public class AccountBOImpl extends AbstractBO<Integer, Account, AccountDAO> implements AccountBO {
	private static final Logger LOG = LoggerFactory.getLogger(AccountBOImpl.class);

	private static final int SALT_LENGTH = 12;

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private ForgotPasswordBO forgotPasswordBO;
	@Autowired
	private UrlResolver urlResolver;
	@Autowired
	private Mailer mailer;

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

	@Override
	@Transactional(readOnly = false)
	public void requestForgotPassword(@NotNull String email) {
		final Account account = getDao().getByEmail(email);
		if(account == null) {
			throw new EntityNotFoundException();
		}

		final String uid = forgotPasswordBO.createForAccount(account.getId());
		final String url = urlResolver.getPortalResetPasswordUrl(uid);

		final String subject = "Forgot password";
		mailer.sendEmail(Mailer.EmailTemplate.FORGOT_PASSWORD, new HashMap<String, Object>(){{
			put("resetPasswordUrl", url);
		}}, email, subject, null, null, false);

		LOG.info("Sending email({}) about forgot password...", email);
	}
}
