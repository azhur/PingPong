/**
 * Copyright U-wiss
 */
package com.pingpong.core.bo;

import com.pingpong.domain.PlayerAccount;
import net.sf.oval.constraint.NotNull;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 20/03/2012
 */

public interface PlayerAccountBO extends BO<Integer, PlayerAccount>{
	PlayerAccount getByEmail(@NotNull String email);

	PlayerAccount getByPlayer(@NotNull Integer playerId);
}
