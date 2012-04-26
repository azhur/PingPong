/**
 * Copyright U-wiss
 */
package com.pingpong.core.dao.impl;

import com.pingpong.core.dao.AuthorityDAO;
import com.pingpong.domain.Authority;
import net.sf.oval.guard.Guarded;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 19/02/2012
 */
@Guarded
public class AuthorityDAOImpl extends AbstractDAO<Integer, Authority> implements AuthorityDAO {
	public AuthorityDAOImpl() {
		super(Authority.class);
	}
}
