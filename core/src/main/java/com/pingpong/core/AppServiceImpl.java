package com.pingpong.core;

import com.pingpong.core.bo.AccountBO;
import com.pingpong.core.bo.AdminAccountBO;
import com.pingpong.core.bo.PlayerAccountBO;
import com.pingpong.core.bo.PlayerBO;
import com.pingpong.domain.Account;
import com.pingpong.domain.AdminAccount;
import com.pingpong.domain.Player;
import com.pingpong.domain.PlayerAccount;
import com.pingpong.shared.AppService;
import com.pingpong.shared.hibernate.ListResult;
import com.pingpong.shared.hibernate.PatternSearchData;
import com.pingpong.shared.registration.PlayerRegistrationData;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

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

	@Override
	public List<Player> listPlayers() {
		return playerBO.list();
	}

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
	public List<AdminAccount> listAdminAccounts() {
		return adminAccountBO.list();
	}

	@Override
	public void createAdminAccount(@NotNull AdminAccount account) {
		adminAccountBO.create(account);
	}
}
