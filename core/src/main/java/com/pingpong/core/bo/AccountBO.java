/**
 * Without Copyright
 */
package com.pingpong.core.bo;

import com.pingpong.domain.Account;
import net.sf.oval.constraint.NotNull;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 20/02/2012
 */

public interface AccountBO extends BO<Integer, Account>{
	void encodePassword(@NotNull Account account);

	Account getByEmail(@NotNull String email);

	void requestForgotPassword(@NotNull String email);

	void resetForgottenPassword(@NotNull String forgotPasswordId, @NotNull String newPassword);

	Account getAccountByForgotPasswordId(@NotNull String forgotPasswordId);

	void changePassword(@NotNull Integer accountId, @NotNull String oldPassword, @NotNull String newPassword);
}
