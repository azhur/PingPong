/**
 * Without Copyright
 */
package com.pingpong.core.dao;

import com.pingpong.domain.Tournament;
import net.sf.oval.constraint.NotNull;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 12/05/2012
 */

public interface TournamentDAO extends DAO<Integer, Tournament> {
	boolean isParticipant(@NotNull Integer playerId, @NotNull Integer tournamentId);
}
