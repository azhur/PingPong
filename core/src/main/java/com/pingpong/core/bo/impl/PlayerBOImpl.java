/**
 * Copyright U-wiss
 */
package com.pingpong.core.bo.impl;

import com.pingpong.core.bo.AccountBO;
import com.pingpong.core.bo.PlayerBO;
import com.pingpong.core.dao.AuthorityDAO;
import com.pingpong.core.dao.PlayerAccountDAO;
import com.pingpong.core.dao.PlayerDAO;
import com.pingpong.domain.Account;
import com.pingpong.domain.Authority;
import com.pingpong.domain.Player;
import com.pingpong.domain.PlayerAccount;
import com.pingpong.shared.exception.NotUniqueEmailException;
import com.pingpong.shared.registration.PlayerRegistrationData;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 20/02/2012
 */
@Guarded
public class PlayerBOImpl extends AbstractBO<Integer, Player, PlayerDAO> implements PlayerBO {

	@Autowired
	private PlayerAccountDAO playerAccountDAO;
	@Autowired
	private AuthorityDAO authorityDAO;
	@Autowired
	private AccountBO accountBO;

	@Override
	@Transactional(readOnly = false)
	public void register(@NotNull PlayerRegistrationData registrationData) {
		final Account emailAccount = accountBO.getByEmail(registrationData.getEmail());

		if (emailAccount != null) {
			throw new NotUniqueEmailException("Not unique email!");
		}

		final Player player = new Player();
		player.setStatus(Player.Status.REGISTRATION);
		player.setBirth(registrationData.getBirth());
		player.setGender(registrationData.getGender());
		player.setName(registrationData.getName());

		insert(player);

		//create account
		final PlayerAccount account = createAccount(registrationData, player);

		//create authorities
		createAuthority(account);
	}

	private Authority createAuthority(PlayerAccount account) {
		final Authority authority = new Authority();
		authority.setAccount(account);
		authority.setName(Authority.Name.ROLE_PLAYER_USER);

		authorityDAO.insert(authority);

		return authority;
	}

	private PlayerAccount createAccount(PlayerRegistrationData registrationData, Player player) {
		final PlayerAccount account = new PlayerAccount();
		account.setPlayer(player);
		account.setEmail(registrationData.getEmail());
		account.setEnabled(true);
		account.setPassword(registrationData.getPassword());
		account.setSalt("salt");

		playerAccountDAO.insert(account);

		return account;
	}
}