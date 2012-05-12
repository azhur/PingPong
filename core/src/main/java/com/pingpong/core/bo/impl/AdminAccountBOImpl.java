/**
 * Without Copyright
 */
package com.pingpong.core.bo.impl;

import com.pingpong.core.bo.AccountBO;
import com.pingpong.core.bo.AdminAccountBO;
import com.pingpong.core.dao.AdminAccountDAO;
import com.pingpong.core.dao.AuthorityDAO;
import com.pingpong.domain.AdminAccount;
import com.pingpong.domain.Authority;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 20/03/2012
 */
@Guarded
public class AdminAccountBOImpl extends AbstractBO<Integer, AdminAccount, AdminAccountDAO> implements AdminAccountBO {
	@Autowired
	private AccountBO accountBO;
	@Autowired
	private AuthorityDAO authorityDAO;

	@Override
	@Transactional(readOnly = false)
	public void unblock(@NotNull Integer id) {
		final AdminAccount entity = getDao().loadById(id, true);

		if(entity.isEnabled()) {
			throw new IllegalStateException();
		}

		entity.setEnabled(true);
	}

	@Override
	@Transactional(readOnly = false)
	public void block(@NotNull Integer id) {
		final AdminAccount entity = getDao().loadById(id, true);

		if(!entity.isEnabled()) {
			throw new IllegalStateException();
		}

		entity.setEnabled(false);
	}

	@Override
	@Transactional(readOnly = false)
	public void create(@NotNull AdminAccount account) {
		accountBO.encodePassword(account);

		insert(account);

		authorityDAO.create(account, Authority.Name.ROLE_ADMIN_USER);
	}
}
