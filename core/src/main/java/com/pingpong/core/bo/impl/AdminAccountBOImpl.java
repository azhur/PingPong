/**
 * Copyright U-wiss
 */
package com.pingpong.core.bo.impl;

import com.pingpong.core.bo.AdminAccountBO;
import com.pingpong.core.dao.AdminAccountDAO;
import com.pingpong.domain.AdminAccount;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 20/03/2012
 */
@Guarded
public class AdminAccountBOImpl extends AbstractBO<Integer, AdminAccount, AdminAccountDAO> implements AdminAccountBO {
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
}
