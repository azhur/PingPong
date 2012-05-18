/**
 * Without Copyright
 */
package com.pingpong.core.bo.impl;

import com.pingpong.core.bo.PlayerAccountBO;
import com.pingpong.core.bo.PlayerBO;
import com.pingpong.core.bo.TournamentBO;
import com.pingpong.core.dao.PlayerDAO;
import com.pingpong.core.dao.TournamentDAO;
import com.pingpong.core.hibernate.RestrictionsHelper;
import com.pingpong.core.mail.Mailer;
import com.pingpong.core.web.UrlResolver;
import com.pingpong.domain.Player;
import com.pingpong.domain.Tournament;
import com.pingpong.shared.Constraints;
import com.pingpong.shared.exception.FullTournamentException;
import com.pingpong.shared.exception.RepeatActionException;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 12/05/2012
 */
@Guarded
public class TournamentBOImpl extends AbstractBO<Integer, Tournament, TournamentDAO> implements TournamentBO {
	private static final Logger LOG = LoggerFactory.getLogger(TournamentBOImpl.class);

	@Value("${mail.group.admin}")
	private String adminEmails;

	@Autowired
	private PlayerBO playerBO;
	@Autowired
	private PlayerDAO playerDAO;
	@Autowired
	private PlayerAccountBO playerAccountBO;
	@Autowired
	private Mailer mailer;
	@Autowired
	private UrlResolver urlResolver;


	@Override
	@Transactional(readOnly = false)
	public Integer insert(@NotNull Tournament entity) {
		checkArgument(entity.getMaxParticipantsCount() >= Constraints.MIN_PARTICIPANT_COUNT);
		return super.insert(entity);
	}

	@Override
	@Transactional(readOnly = false)
	public void update(@NotNull Tournament entity) {
		checkArgument(entity.getMaxParticipantsCount() >= Constraints.MIN_PARTICIPANT_COUNT);
		super.update(entity);
	}

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

	@Override
	public boolean isParticipant(@NotNull Integer playerId, @NotNull Integer tournamentId) {
		return getDao().isParticipant(playerId, tournamentId);
	}

	@Override
	@Transactional(readOnly = false)
	public void registerIn(@NotNull Integer playerId, @NotNull Integer tournamentId) {
		final Player player = playerDAO.loadById(playerId);
		final Tournament tournament = getDao().loadById(tournamentId, true);

		if (isParticipant(playerId, tournamentId)){
			throw new RepeatActionException();
		}

		final int participantCounts = tournament.getParticipants().size();
		final Integer maxParticipantsCount = tournament.getMaxParticipantsCount();

		if (participantCounts >= maxParticipantsCount) {
			throw new FullTournamentException();
		}

		checkStatus(tournament.getStatus(), Tournament.Status.REGISTRATION);
		checkState(Player.Status.ACTIVE == player.getStatus());

		tournament.getParticipants().add(player);


		if (participantCounts == maxParticipantsCount - 1) {
			LOG.info("Full tournament");
			notifyAboutFullTournament(tournament);
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void giveUp(@NotNull Integer playerId, @NotNull Integer tournamentId) {
		final Player player = playerDAO.loadById(playerId);
		final Tournament tournament = getDao().loadById(tournamentId);

		if (!isParticipant(playerId, tournamentId)){
			throw new RepeatActionException();
		}

		checkStatus(tournament.getStatus(), Tournament.Status.REGISTRATION);
		checkState(Player.Status.ACTIVE == player.getStatus());

		tournament.getParticipants().remove(player);
	}

	private void notifyAboutFullTournament(final Tournament tournament) {
		final String toAddress = adminEmails;
		final String subject = "Full tournament";

		mailer.sendEmail(Mailer.EmailTemplate.FULL_TOURNAMENT, new HashMap<String, Object>() {{
			put("tournamentName", tournament.getName());
			put("url", urlResolver.getAdminTournamentViewUrl(tournament.getId()));
		}}, toAddress, subject, null, null, false);

		LOG.info("Sending email about full tournament...");
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
				put("url", urlResolver.getPortalTournamentViewUrl(tournament.getId()));
			}}, toAddress, subject, null, null, false);
		}

		LOG.info("Sending email about tournament registration...");
	}

	private void checkStatus(Tournament.Status tournamentStatus, Tournament.Status... statuses) {
		checkState(ArrayUtils.contains(statuses, tournamentStatus));
	}
}
