/**
 * Copyright U-wiss
 */
package com.pingpong.core.dao.impl;

import com.pingpong.core.dao.PlayerAccountDAO;
import com.pingpong.domain.PlayerAccount;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 19/02/2012
 */
@Guarded
public class PlayerAccountDAOImpl extends AbstractDAO<Integer, PlayerAccount> implements PlayerAccountDAO {
	public PlayerAccountDAOImpl() {
		super(PlayerAccount.class);
	}

	@Override
	public PlayerAccount getByPlayer(@NotNull Integer playerId) {
		return (PlayerAccount)getCurrentSession().createQuery("from PlayerAccount pa where pa.player.id = :playerId")
				.setParameter("playerId", playerId)
				.uniqueResult();
	}
}
