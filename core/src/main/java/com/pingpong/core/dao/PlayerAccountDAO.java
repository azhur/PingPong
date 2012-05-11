package com.pingpong.core.dao;

import com.pingpong.domain.PlayerAccount;
import net.sf.oval.constraint.NotNull;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 25/12/2011
 */

public interface PlayerAccountDAO extends DAO<Integer, PlayerAccount> {
	PlayerAccount getByPlayer(@NotNull Integer playerId);
}
