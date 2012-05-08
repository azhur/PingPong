package com.pingpong.core;

import com.pingpong.core.bo.AccountBO;
import com.pingpong.core.bo.PlayerAccountBO;
import com.pingpong.core.bo.PlayerBO;
import com.pingpong.domain.Account;
import com.pingpong.domain.Player;
import com.pingpong.domain.PlayerAccount;
import com.pingpong.shared.AppService;
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
	private AccountBO accountBO;

	@Override
	public List<Player> listPlayers() {
		return playerBO.list();
	}

	@Override
	public Integer insertPlayer(@NotNull Player player) {
		return playerBO.insert(player);
	}

	@Override
	public void register(@NotNull PlayerRegistrationData registrationData) {
		playerBO.register(registrationData);
	}

	@Override
	public PlayerAccount getAccountByEmail(@NotNull String email) {
		return playerAccountBO.getByEmail(email);
	}

	@Override
	public void requestForgotPassword(@NotNull String email) {
		final PlayerAccount playerAccount = playerAccountBO.getByEmail(email);

		if (playerAccount == null) {
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
}
