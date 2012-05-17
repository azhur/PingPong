/**
 * Without Copyright
 */
package com.pingpong.core.bo.impl;

import com.pingpong.core.bo.PlayerAccountBO;
import com.pingpong.core.bo.PlayerBO;
import com.pingpong.core.bo.TournamentBO;
import com.pingpong.core.dao.TournamentDAO;
import com.pingpong.core.hibernate.RestrictionsHelper;
import com.pingpong.core.mail.Mailer;
import com.pingpong.core.web.UrlResolver;
import com.pingpong.domain.Player;
import com.pingpong.domain.Tournament;
import com.pingpong.shared.hibernate.HibernateUtils;
import com.pingpong.shared.hibernate.ListResult;
import com.pingpong.shared.hibernate.PatternSearchData;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;
import org.apache.commons.lang.ArrayUtils;
import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

import static com.google.common.base.Preconditions.checkState;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 12/05/2012
 */
@Guarded
public class TournamentBOImpl extends AbstractBO<Integer, Tournament, TournamentDAO> implements TournamentBO {
	private static final Logger LOG = LoggerFactory.getLogger(TournamentBOImpl.class);

	@Autowired
	private PlayerBO playerBO;
	@Autowired
	private PlayerAccountBO playerAccountBO;
	@Autowired
	private Mailer mailer;
	@Autowired
	private UrlResolver urlResolver;


	@Override
	public ListResult<Tournament> list(@NotNull PatternSearchData<Tournament> searchData) {
		final Tournament pattern = searchData.getPattern();
		final Criteria criteria = createCriteria();

		RestrictionsHelper.eqOpt(criteria, "status", pattern.getStatus());

		return toList(criteria, searchData);
	}

	@Override
	public Tournament getById(@NotNull Integer integer) {
		final Tournament entity = super.getById(integer);

		if (entity != null) {
			HibernateUtils.initializeAndUnproxy(entity.getParticipants());
		}

		return entity;
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteById(@NotNull Integer id) {
		final Tournament tournament = getDao().loadById(id);
		checkStatus(tournament.getStatus(), Tournament.Status.NEW);
		super.deleteById(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void transitToRegistrationStatus(@NotNull Integer id) {
		final Tournament entity = getDao().loadById(id, true);

		checkStatus(entity.getStatus(), Tournament.Status.NEW);

		entity.setStatus(Tournament.Status.REGISTRATION);

		notifyAboutTournamentRegistration(entity);

	}

	private void notifyAboutTournamentRegistration(final Tournament tournament) {
		final Player playerPattern = new Player();
		playerPattern.setStatus(Player.Status.ACTIVE);

		final ListResult<Player> listResult = playerBO.listPlayers(new PatternSearchData<Player>(playerPattern));

		final List<Player> players = listResult.getItems();

		for(final Player player : players) {
			final String toAddress = playerAccountBO.getByPlayer(player.getId()).getEmail();

			final String subject = "Tournament registration";

			mailer.sendEmail(Mailer.EmailTemplate.TOURNAMENT_REGISTRATION, new HashMap<String, Object>() {{
				put("playerName", player.getName());
				put("tournamentName", tournament.getName());
				put("url", urlResolver.getPortalUrl());
			}}, toAddress, subject, null, null, false);
		}

		LOG.info("Sending email about tournament registration...");
	}

	@Override
	@Transactional(readOnly = false)
	public void transitToActiveStatus(@NotNull Integer id) {
		final Tournament tournament = getDao().loadById(id, true);

		checkStatus(tournament.getStatus(), Tournament.Status.REGISTRATION);

		tournament.setStatus(Tournament.Status.ACTIVE);
	}

	@Override
	@Transactional(readOnly = false)
	public void transitToFinishedStatus(@NotNull Integer id) {
		final Tournament tournament = getDao().loadById(id, true);

		checkStatus(tournament.getStatus(), Tournament.Status.ACTIVE);

		tournament.setStatus(Tournament.Status.FINISHED);
	}

	@Override
	@Transactional(readOnly = false)
	public void transitToCanceledStatus(@NotNull Integer id) {
		final Tournament tournament = getDao().loadById(id, true);

		checkStatus(tournament.getStatus(), Tournament.Status.ACTIVE, Tournament.Status.REGISTRATION);

		tournament.setStatus(Tournament.Status.CANCELED);
	}

	private void checkStatus(Tournament.Status tournamentStatus, Tournament.Status... statuses) {
		checkState(ArrayUtils.contains(statuses, tournamentStatus));
	}
}
