/**
 * Copyright U-wiss
 */
package com.pingpong.core.dao.impl;

import com.pingpong.core.dao.ForgotPasswordDAO;
import com.pingpong.domain.ForgotPassword;
import net.sf.oval.guard.Guarded;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 12/04/2012
 */
@Guarded
public class ForgotPasswordDAOImpl extends AbstractDAO<String, ForgotPassword> implements ForgotPasswordDAO {
	public ForgotPasswordDAOImpl() {
		super(ForgotPassword.class);
	}
}
