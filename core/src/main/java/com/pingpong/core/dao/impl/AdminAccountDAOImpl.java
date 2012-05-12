/**
 * Without Copyright
 */
package com.pingpong.core.dao.impl;

import com.pingpong.core.dao.AdminAccountDAO;
import com.pingpong.domain.AdminAccount;
import net.sf.oval.guard.Guarded;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 19/02/2012
 */
@Guarded
public class AdminAccountDAOImpl extends AbstractDAO<Integer, AdminAccount> implements AdminAccountDAO {
	public AdminAccountDAOImpl() {
		super(AdminAccount.class);
	}
}
