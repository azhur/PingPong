package com.pingpong.shared;

import com.pingpong.domain.Account;
import com.pingpong.domain.Player;
import com.pingpong.domain.PlayerAccount;
import com.pingpong.shared.registration.PlayerRegistrationData;
import net.sf.oval.constraint.NotNull;

import java.util.List;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 25/01/2012
 */
public interface AppService {
	@NotNull
	List<Player> listPlayers();

	@NotNull
	Integer insertPlayer(@NotNull Player player);

	void register(@NotNull PlayerRegistrationData registrationData);
	
	PlayerAccount getAccountByEmail(@NotNull String email);

	void requestForgotPassword(@NotNull String email);

	Account getAccountByForgotPasswordId(@NotNull String forgotPasswordId);

	void resetForgottenPassword(@NotNull String forgotPasswordId, @NotNull String newPassword);
}
