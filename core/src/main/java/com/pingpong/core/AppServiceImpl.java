package com.pingpong.core;

import com.pingpong.core.bo.AccountBO;
import com.pingpong.core.bo.AdminAccountBO;
import com.pingpong.core.bo.PlayerAccountBO;
import com.pingpong.core.bo.PlayerBO;
import com.pingpong.core.bo.TournamentBO;
import com.pingpong.domain.Account;
import com.pingpong.domain.AdminAccount;
import com.pingpong.domain.Player;
import com.pingpong.domain.PlayerAccount;
import com.pingpong.domain.Tournament;
import com.pingpong.shared.AppService;
import com.pingpong.shared.hibernate.ListResult;
import com.pingpong.shared.hibernate.PatternSearchData;
import com.pingpong.shared.registration.PlayerRegistrationData;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 25/01/2012
 */
@Guarded
@Service
public class AppServiceImpl implements AppService {
	@Autowired
	private PlayerBO playerBO;
	@Autowired
	private PlayerAccountBO playerAccountBO;
	@Autowired
	private AdminAccountBO adminAccountBO;
	@Autowired
	private AccountBO accountBO;
	@Autowired
	private TournamentBO tournamentBO;

	@Override
	public ListResult<Player> listPlayers(@NotNull PatternSearchData<Player> searchData) {
		return playerBO.listPlayers(searchData);
	}

	@Override
	public void register(@NotNull PlayerRegistrationData registrationData) {
		playerBO.register(registrationData);
	}

	@Override
	public PlayerAccount getPlayerAccountByEmail(@NotNull String email) {
		return playerAccountBO.getByEmail(email);
	}

	@Override
	public AdminAccount getAdminAccountByEmail(@NotNull String email) {
		return (AdminAccount)accountBO.getByEmail(email);
	}

	@Override
	public AdminAccount getAdminAccountById(@NotNull Integer id) {
		return adminAccountBO.getById(id);
	}

	@Override
	public void blockAdminAccount(@NotNull Integer adminAccountId) {
		adminAccountBO.block(adminAccountId);
	}

	@Override
	public void unblockAdminAccount(@NotNull Integer adminAccountId) {
		adminAccountBO.unblock(adminAccountId);
	}

	@Override
	public void deleteAdminAccount(@NotNull Integer adminAccountId) {
		adminAccountBO.deleteById(adminAccountId);
	}

	@Override
	public void requestPlayerForgotPassword(@NotNull String email) {
		final PlayerAccount playerAccount = playerAccountBO.getByEmail(email);

		if (playerAccount == null) {
			throw new EntityNotFoundException();
		}

		accountBO.requestForgotPassword(email);
	}

	@Override
	public void requestAdminForgotPassword(@NotNull String email) {
		final AdminAccount adminAccount = (AdminAccount)accountBO.getByEmail(email);

		if (adminAccount == null) {
			throw new EntityNotFoundException();
		}

		accountBO.requestForgotPassword(email);
	}

	@Override
	public Account getAccountByForgotPasswordId(@NotNull String forgotPasswordId) {
		return accountBO.getAccountByForgotPasswordId(forgotPasswordId);
	}

	@Override
	public void resetForgottenPassword(@NotNull String forgotPasswordId, @NotNull String newPassword) {
		accountBO.resetForgottenPassword(forgotPasswordId, newPassword);
	}

	@Override
	public void changePassword(@NotNull Integer accountId, @NotNull String oldPassword, @NotNull String newPassword) {
		accountBO.changePassword(accountId, oldPassword, newPassword);
	}

	@Override
	public void updatePlayer(@NotNull Player player) {
		playerBO.update(player);
	}

	@Override
	public Player getPlayerById(@NotNull Integer id) {
		return playerBO.getById(id);
	}

	@Override
	public void activatePlayer(@NotNull Integer playerId) {
		playerBO.activate(playerId);
	}

	@Override
	public void blockPlayer(@NotNull Integer playerId) {
		playerBO.block(playerId);
	}

	@Override
	public void unblockPlayer(@NotNull Integer playerId) {
		playerBO.unblock(playerId);
	}

	@Override
	public void deletePlayer(@NotNull Integer playerId) {
		playerBO.deleteById(playerId);
	}

	@Override
	@NotNull
	public ListResult<AdminAccount> listAdminAccounts(@NotNull PatternSearchData<AdminAccount> searchData) {
		return adminAccountBO.list(searchData);
	}

	@Override
	public void createAdminAccount(@NotNull AdminAccount account) {
		adminAccountBO.create(account);
	}

	@Override
	@NotNull
	public Integer insertTournament(@NotNull Tournament tournament) {
		return tournamentBO.insert(tournament);
	}

	@Override
	@NotNull
	public ListResult<Tournament> listTournaments(@NotNull PatternSearchData<Tournament> searchData) {
		return tournamentBO.list(searchData);
	}

	@Override
	public void transitTournamentToRegistrationStatus(@NotNull Integer tournamentId) {
		tournamentBO.transitToRegistrationStatus(tournamentId);
	}

	@Override
	public void deleteTournament(@NotNull Integer tournamentId) {
		tournamentBO.deleteById(tournamentId);
	}

	@Override
	public void transitTournamentToActiveStatus(@NotNull Integer tournamentId) {
		tournamentBO.transitToActiveStatus(tournamentId);
	}

	@Override
	public Tournament getTournamentById(@NotNull Integer tournamentId) {
		return tournamentBO.getById(tournamentId);
	}

	@Override
	public void transitTournamentToFinishedStatus(@NotNull Integer tournamentId) {
		tournamentBO.transitToFinishedStatus(tournamentId);
	}

	@Override
	public void transitTournamentToCanceledStatus(@NotNull Integer tournamentId) {
		tournamentBO.transitToCanceledStatus(tournamentId);
	}

	@Override
	public void registerInTournament(@NotNull Integer playerId, @NotNull Integer tournamentId) {
		tournamentBO.registerIn(playerId, tournamentId);
	}

	@Override
	public boolean isParticipant(@NotNull Integer playerId, @NotNull Integer tournamentId) {
		return tournamentBO.isParticipant(playerId, tournamentId);
	}

	@Override
	public void giveUp(@NotNull Integer playerId, @NotNull Integer tournamentId) {
		tournamentBO.giveUp(playerId, tournamentId);
	}
}
