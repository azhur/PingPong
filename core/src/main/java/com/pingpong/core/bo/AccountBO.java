/**
 * Copyright U-wiss
 */
package com.pingpong.core.bo;

import com.pingpong.domain.Account;
import net.sf.oval.constraint.NotNull;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 20/02/2012
 */

public interface AccountBO extends BO<Integer, Account>{
	void encodePassword(@NotNull Account account);

	Account getByEmail(@NotNull String email);
}
