/**
 * Copyright U-wiss
 */
package com.pingpong.core.dao.impl;

import com.pingpong.core.dao.PlayerAccountDAO;
import com.pingpong.domain.PlayerAccount;
import net.sf.oval.guard.Guarded;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 19/02/2012
 */
@Guarded
public class PlayerAccountDAOImpl extends AbstractDAO<Integer, PlayerAccount> implements PlayerAccountDAO {
	public PlayerAccountDAOImpl() {
		super(PlayerAccount.class);
	}
}
