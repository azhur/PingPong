package com.pingpong.shared;

import com.pingpong.domain.Account;
import com.pingpong.domain.AdminAccount;
import com.pingpong.domain.Player;
import com.pingpong.domain.PlayerAccount;
import com.pingpong.domain.Tournament;
import com.pingpong.shared.hibernate.ListResult;
import com.pingpong.shared.hibernate.PatternSearchData;
import com.pingpong.shared.registration.PlayerRegistrationData;
import net.sf.oval.constraint.NotNull;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 25/01/2012
 */
public interface AppService {

	@NotNull
	ListResult<Player> listPlayers(@NotNull PatternSearchData<Player> searchData);

	void register(@NotNull PlayerRegistrationData registrationData);

	PlayerAccount getPlayerAccountByEmail(@NotNull String email);

	AdminAccount getAdminAccountByEmail(@NotNull String email);

	AdminAccount getAdminAccountById(@NotNull Integer id);

	void blockAdminAccount(@NotNull Integer adminAccountId);

	void unblockAdminAccount(@NotNull Integer adminAccountId);

	void deleteAdminAccount(@NotNull Integer adminAccountId);

	void requestPlayerForgotPassword(@NotNull String email);

	void requestAdminForgotPassword(@NotNull String email);

	Account getAccountByForgotPasswordId(@NotNull String forgotPasswordId);

	void resetForgottenPassword(@NotNull String forgotPasswordId, @NotNull String newPassword);

	void changePassword(@NotNull Integer accountId, @NotNull String oldPassword, @NotNull String newPassword);

	void updatePlayer(@NotNull Player player);

	Player getPlayerById(@NotNull Integer id);

	void activatePlayer(@NotNull Integer playerId);

	void blockPlayer(@NotNull Integer playerId);

	void unblockPlayer(@NotNull Integer playerId);

	void deletePlayer(@NotNull Integer playerId);

	@NotNull
	ListResult<AdminAccount> listAdminAccounts(@NotNull PatternSearchData<AdminAccount> searchData);

	void createAdminAccount(@NotNull AdminAccount account);

	@NotNull
	Integer insertTournament(@NotNull Tournament tournament);

	@NotNull
	ListResult<Tournament> listTournaments(@NotNull PatternSearchData<Tournament> searchData);

	void deleteTournament(@NotNull Integer tournamentId);

	void transitTournamentToRegistrationStatus(@NotNull Integer tournamentId);

	void transitTournamentToActiveStatus(@NotNull Integer tournamentId);

	void transitTournamentToFinishedStatus(@NotNull Integer tournamentId);

	void transitTournamentToCanceledStatus(@NotNull Integer tournamentId);

	Tournament getTournamentById(@NotNull Integer tournamentId);

	void registerInTournament(@NotNull Integer playerId, @NotNull Integer tournamentId);

	void giveUp(@NotNull Integer playerId, @NotNull Integer tournamentId);

	boolean isParticipant(@NotNull Integer playerId, @NotNull Integer tournamentId);
}
