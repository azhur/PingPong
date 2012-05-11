package com.pingpong.shared;

import com.pingpong.domain.Account;
import com.pingpong.domain.AdminAccount;
import com.pingpong.domain.Player;
import com.pingpong.domain.PlayerAccount;
import com.pingpong.shared.registration.PlayerRegistrationData;
import net.sf.oval.constraint.NotNull;

import java.util.List;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 25/01/2012
 */
public interface AppService {
	@NotNull
	List<Player> listPlayers();

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
	List<AdminAccount> listAdminAccounts();
}
