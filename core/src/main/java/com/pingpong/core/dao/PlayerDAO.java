package com.pingpong.core.dao;

import com.pingpong.domain.Player;
import net.sf.oval.constraint.NotNull;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 25/12/2011
 */

public interface PlayerDAO extends DAO<Integer,Player> {
	boolean isParticipant(@NotNull Integer playerId, @NotNull Integer tournamentId);
}
