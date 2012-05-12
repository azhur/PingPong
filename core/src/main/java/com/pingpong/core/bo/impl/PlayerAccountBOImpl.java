/**
 * Without Copyright
 */
package com.pingpong.core.bo.impl;

import com.pingpong.core.bo.AccountBO;
import com.pingpong.core.bo.PlayerAccountBO;
import com.pingpong.core.dao.PlayerAccountDAO;
import com.pingpong.domain.PlayerAccount;
import com.pingpong.shared.util.HibernateUtils;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 20/03/2012
 */
@Guarded
public class PlayerAccountBOImpl extends AbstractBO<Integer, PlayerAccount, PlayerAccountDAO> implements PlayerAccountBO {
	@Autowired
	private AccountBO accountBO;

	@Override
	public PlayerAccount getByEmail(@NotNull String email) {
		final PlayerAccount entity = (PlayerAccount)accountBO.getByEmail(email);
		if(entity != null) {
			HibernateUtils.initializeAndUnproxy(entity.getPlayer());
		}
		return entity;
	}

	@Override
	public PlayerAccount getByPlayer(@NotNull Integer playerId) {
		return getDao().getByPlayer(playerId);
	}
}
