/**
 * Without Copyright
 */
package com.pingpong.core.bo;

import com.pingpong.domain.Player;
import com.pingpong.shared.hibernate.ListResult;
import com.pingpong.shared.hibernate.PatternSearchData;
import com.pingpong.shared.registration.PlayerRegistrationData;
import net.sf.oval.constraint.NotNull;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 20/02/2012
 */

public interface PlayerBO extends BO<Integer, Player>{
	void register(@NotNull PlayerRegistrationData registrationData);

	void activate(@NotNull Integer playerId);

	void block(@NotNull Integer playerId);

	void unblock(@NotNull Integer playerId);

	ListResult<Player> listPlayers(@NotNull PatternSearchData<Player> searchData);

	boolean isParticipant(@NotNull Integer playerId, @NotNull Integer tournamentId);

	void registerIn(@NotNull Integer playerId, @NotNull Integer tournamentId);

	void giveUp(@NotNull Integer playerId, @NotNull Integer tournamentId);
}
