package com.pingpong.domain;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 29/01/2012
 */
@Entity
@Table(name = "player_account")
@DiscriminatorValue(value = "PLAYER_ACCOUNT")
public class PlayerAccount extends Account {
	private static final long serialVersionUID = 1504312397288000421L;

	@OneToOne(cascade = CascadeType.ALL)
	private Player player;

	public PlayerAccount() {
		setDiscriminator(Discriminator.PLAYER_ACCOUNT);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
