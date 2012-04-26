/**
 * Copyright U-wiss
 */
package com.pingpong.core.dao.impl;

import com.pingpong.core.dao.ForgotPasswordDAO;
import com.pingpong.domain.ForgotPassword;
import net.sf.oval.guard.Guarded;
import org.joda.time.LocalDateTime;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 12/04/2012
 */
@Guarded
public class ForgotPasswordDAOImpl extends AbstractDAO<String, ForgotPassword> implements ForgotPasswordDAO {
	public ForgotPasswordDAOImpl() {
		super(ForgotPassword.class);
	}

	@Override
	public void cleanup() {
		getCurrentSession().createQuery("delete from ForgotPassword fp where fp.validTill <= :now")
				.setParameter("now", new LocalDateTime())
		        .executeUpdate();
	}
}
