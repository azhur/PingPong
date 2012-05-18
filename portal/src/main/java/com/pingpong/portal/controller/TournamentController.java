/**
 * Without Copyright
 */
package com.pingpong.portal.controller;

import com.pingpong.domain.Tournament;
import com.pingpong.portal.ErrorInfoMSG;
import com.pingpong.portal.SuccessInfoMSG;
import com.pingpong.portal.extension.TournamentEx;
import com.pingpong.portal.security.SpringSecurityUtils;
import com.pingpong.shared.AppService;
import com.pingpong.shared.exception.FullTournamentException;
import com.pingpong.shared.exception.RepeatActionException;
import com.pingpong.shared.exception.UnknownEntityException;
import com.pingpong.shared.hibernate.PatternSearchData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 15/05/2012
 */
@Controller
public class TournamentController extends AbstractBaseController {
	private static final Logger LOG = LoggerFactory.getLogger(TournamentController.class);

	@Autowired
	private AppService appService;

	@RequestMapping(value = "/tournaments/registration", method = RequestMethod.GET)
	@Secured({"ROLE_PLAYER_USER"})
	public String showRegistrationTournaments(Map model) {
		try {
			model.put("tournaments", findTournaments(Tournament.Status.REGISTRATION));
			return "tournament/registration";
		} catch(Exception e) {
			LOG.error(ErrorInfoMSG.SERVER_ERROR, e);
			model.put(ERROR_MSG_VAR, ErrorInfoMSG.SERVER_ERROR);
		}

		return "index";
	}

	@RequestMapping(value = "/tournament/{id}/participate", method = RequestMethod.GET)
	@Secured({"ROLE_PLAYER_USER"})
	public String participate(@PathVariable("id") Integer id, Map model) {
		try {
			appService.registerInTournament(SpringSecurityUtils.getCurrentUser().getPlayerId(), id);
			model.put(SUCCESS_MSG_VAR, SuccessInfoMSG.SUCCESS_REGISTRATION);
			return view(id, model);
		} catch(RepeatActionException e) {
			LOG.error(ErrorInfoMSG.REPEAT_REGISTRATION, e);
			model.put(ERROR_MSG_VAR, ErrorInfoMSG.REPEAT_REGISTRATION);
			return view(id,model);
		} catch(UnknownEntityException uee) {
			LOG.error(uee.getMessage(), uee);
			model.put(ERROR_MSG_VAR, uee.getMessage());
			return view(id,model);
		} catch(FullTournamentException fte) {
			LOG.error(ErrorInfoMSG.FULL_TOURNAMENT, fte);
			model.put(ERROR_MSG_VAR, ErrorInfoMSG.FULL_TOURNAMENT);
			return view(id,model);
		} catch(Exception e) {
			LOG.error(ErrorInfoMSG.SERVER_ERROR, e);
			model.put(ERROR_MSG_VAR, ErrorInfoMSG.SERVER_ERROR);
		}

		return "index";
	}

	@RequestMapping(value = "/tournament/{id}/giveUp", method = RequestMethod.GET)
	@Secured({"ROLE_PLAYER_USER"})
	public String giveUp(@PathVariable("id") Integer id, Map model) {
		try {
			appService.giveUp(SpringSecurityUtils.getCurrentUser().getPlayerId(), id);
			model.put(SUCCESS_MSG_VAR, SuccessInfoMSG.SUCCESS_GIVE_UP);
			return view(id,model);
		} catch(RepeatActionException e) {
			LOG.error(ErrorInfoMSG.REPEAT_GIVE_UP, e);
			model.put(ERROR_MSG_VAR, ErrorInfoMSG.REPEAT_GIVE_UP);
			return view(id,model);
		} catch(UnknownEntityException uee) {
			LOG.error(uee.getMessage(), uee);
			model.put(ERROR_MSG_VAR, uee.getMessage());
			return view(id,model);
		} catch(Exception e) {
			LOG.error(ErrorInfoMSG.SERVER_ERROR, e);
			model.put(ERROR_MSG_VAR, ErrorInfoMSG.SERVER_ERROR);
		}

		return "index";
	}

	@RequestMapping(value = "/tournament/{id}/view", method = RequestMethod.GET)
	@Secured({"ROLE_PLAYER_USER"})
	public String view(@PathVariable("id") Integer id, Map model) {
		try {
			final Tournament item = loadTournamentById(id);

			final TournamentEx tournament = convert(item);
			model.put("tournament", tournament);
			return "tournament/view";
		} catch(UnknownEntityException uee) {
			LOG.error(uee.getMessage(), uee);
			model.put(ERROR_MSG_VAR, uee.getMessage());
			return "index";
		} catch(Exception e) {
			LOG.error(ErrorInfoMSG.SERVER_ERROR, e);
			model.put(ERROR_MSG_VAR, ErrorInfoMSG.SERVER_ERROR);
		}

		return "index";
	}

	private List<TournamentEx> findTournaments(Tournament.Status status) {
		final Tournament pattern = new Tournament();
		pattern.setStatus(status);

		final List<Tournament> items = appService.listTournaments(new PatternSearchData<Tournament>(pattern)).getItems();

		final List<TournamentEx> tournaments = new LinkedList<TournamentEx>();

		for(Tournament item : items) {
			tournaments.add(convert(item));
		}

		return tournaments;
	}

	private TournamentEx convert(Tournament item) {
		final TournamentEx tournamentEx = new TournamentEx();
		tournamentEx.setId(item.getId());
		tournamentEx.setParticipants(item.getParticipants());
		tournamentEx.setCurrentPlayerRegistered(appService.isParticipant(SpringSecurityUtils.getCurrentUser().getPlayerId(), item.getId()));
		tournamentEx.setMaxParticipantsCount(item.getMaxParticipantsCount());
		tournamentEx.setName(item.getName());
		tournamentEx.setStatus(item.getStatus());
		tournamentEx.setVersion(item.getVersion());

		return tournamentEx;
	}

	private Tournament loadTournamentById(Integer id) {
		final Tournament tournament = appService.getTournamentById(id);

		if (tournament == null) {
			throw new UnknownEntityException(Tournament.class, id);
		}

		return tournament;
	}
}
