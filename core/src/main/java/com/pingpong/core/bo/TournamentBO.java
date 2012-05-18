/**
 * Without Copyright
 */
package com.pingpong.core.bo;

import com.pingpong.domain.Tournament;
import net.sf.oval.constraint.NotNull;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 12/05/2012
 */

public interface TournamentBO extends BO<Integer, Tournament> {

	void transitToRegistrationStatus(@NotNull Integer id);

	void transitToActiveStatus(@NotNull Integer id);

	void transitToFinishedStatus(@NotNull Integer id);

	void transitToCanceledStatus(@NotNull Integer id);

	boolean isParticipant(@NotNull Integer playerId, @NotNull Integer tournamentId);

	void registerIn(@NotNull Integer playerId, @NotNull Integer tournamentId);

	void giveUp(@NotNull Integer playerId, @NotNull Integer tournamentId);
}
