/**
 * Copyright U-wiss
 */
package com.pingpong.core;

import com.pingpong.core.dao.PlayerDAO;
import com.pingpong.domain.Player;
import com.pingpong.shared.AppService;
import net.sf.oval.constraint.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 25/01/2012
 */
public class AppServiceImpl implements AppService {
	@Autowired
	private PlayerDAO playerDAO;

	@Override
	public List<Player> listPlayers() {
		return playerDAO.list();
	}

	@Override
	public Integer insertPlayer(@NotNull Player player) {
		return playerDAO.insert(player);
	}
}
