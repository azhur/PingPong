package com.pingpong.core.dao;

import com.pingpong.domain.Account;
import net.sf.oval.constraint.NotNull;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 25/12/2011
 */

public interface AccountDAO extends DAO<Integer,Account> {
	Account getByEmail(@NotNull String email);
}
