/**
 * Copyright U-wiss
 */
package com.pingpong.shared;

import com.pingpong.domain.Player;
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
}
