/**
 * Without Copyright
 */
package com.pingpong.core.dao.impl;

import com.pingpong.core.dao.TournamentDAO;
import com.pingpong.domain.Tournament;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;

import java.math.BigInteger;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 12/05/2012
 */
@Guarded
public class TournamentDAOImpl extends AbstractDAO<Integer, Tournament> implements TournamentDAO {
	public TournamentDAOImpl() {
		super(Tournament.class);
	}

	@Override
	public boolean isParticipant(@NotNull Integer playerId, @NotNull Integer tournamentId) {
		final BigInteger count = (BigInteger)getCurrentSession().createSQLQuery("select count(player_id) from player_tournament where player_id = :playerId and tournament_id = :tournamentId")
				.setParameter("playerId", playerId)
				.setParameter("tournamentId", tournamentId)
				.uniqueResult();
		return count != null && count.equals(BigInteger.ONE);
	}
}
