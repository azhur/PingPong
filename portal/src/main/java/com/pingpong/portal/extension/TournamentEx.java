/**
 * Copyright U-wiss
 */
package com.pingpong.portal.extension;

import com.pingpong.domain.Tournament;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 17/05/2012
 */

public class TournamentEx extends Tournament {
	private static final long serialVersionUID = 6725833364901016363L;

	private boolean currentPlayerRegistered;

	public boolean isCurrentPlayerRegistered() {
		return currentPlayerRegistered;
	}

	public void setCurrentPlayerRegistered(boolean currentPlayerRegistered) {
		this.currentPlayerRegistered = currentPlayerRegistered;
	}
}
