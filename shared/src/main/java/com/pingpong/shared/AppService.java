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

	@NotNull
	Integer insertPlayer(@NotNull Player player);

	void register(@NotNull PlayerRegistrationData registrationData);
	
	PlayerAccount getPlayerAccountByEmail(@NotNull String email);

	AdminAccount getAdminAccountByEmail(@NotNull String email);

	void requestPlayerForgotPassword(@NotNull String email);

	void requestAdminForgotPassword(@NotNull String email);

	Account getAccountByForgotPasswordId(@NotNull String forgotPasswordId);

	void resetForgottenPassword(@NotNull String forgotPasswordId, @NotNull String newPassword);

	void changePassword(@NotNull Integer accountId, @NotNull String oldPassword, @NotNull String newPassword);

	void updatePlayer(@NotNull Player player);

	Player getPlayerById(@NotNull Integer id);

	void activatePlayer(@NotNull Integer playerId);
}
