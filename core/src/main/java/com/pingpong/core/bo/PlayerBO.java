/**
 * Copyright U-wiss
 */
package com.pingpong.core.bo;

import com.pingpong.domain.Player;
import com.pingpong.shared.registration.PlayerRegistrationData;
import net.sf.oval.constraint.NotNull;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 20/02/2012
 */

public interface PlayerBO extends BO<Integer, Player>{
	void register(@NotNull PlayerRegistrationData registrationData);
}
