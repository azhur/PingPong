/**
 * Without Copyright
 */
package com.pingpong.core.dao.impl;

import com.pingpong.core.dao.TournamentDAO;
import com.pingpong.domain.Tournament;
import net.sf.oval.guard.Guarded;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 12/05/2012
 */
@Guarded
public class TournamentDAOImpl extends AbstractDAO<Integer, Tournament> implements TournamentDAO {
	public TournamentDAOImpl() {
		super(Tournament.class);
	}
}
