/**
 * Copyright U-wiss
 */
package com.pingpong.admin.controller;

import com.pingpong.admin.ErrorInfoMSG;
import com.pingpong.admin.SuccessInfoMSG;
import com.pingpong.admin.command.TournamentCommand;
import com.pingpong.domain.Tournament;
import com.pingpong.shared.AppService;
import com.pingpong.shared.exception.UnknownEntityException;
import com.pingpong.shared.exception.WrongPasswordException;
import com.pingpong.shared.hibernate.ListResult;
import com.pingpong.shared.hibernate.PatternSearchData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 14/05/2012
 */
@Controller
@RequestMapping("/tournament")
public class TournamentController extends AbstractBaseController {
	private static final Logger LOG = LoggerFactory.getLogger(TournamentController.class);

	@Autowired
	private AppService appService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN_USER"})
	public String showListForm(Map model) {
		try {
			final ListResult<Tournament> list = appService.listTournaments(new PatternSearchData<Tournament>(new Tournament()));
			model.put("tournaments", list.getItems());
		} catch(Exception e) {
			LOG.error(ErrorInfoMSG.SERVER_ERROR, e);
			model.put(ERROR_MSG_VAR, ErrorInfoMSG.SERVER_ERROR);
			return "index";
		}
		return "tournament/list";
	}

	@RequestMapping(value = "/{id}/registration", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN_USER"})
	public String registration(@PathVariable("id") Integer id, Map model) {
		try {
			final Tournament tournament = appService.getTournamentById(id);
			appService.transitTournamentToRegistrationStatus(id);
			model.put(SUCCESS_MSG_VAR, String.format(SuccessInfoMSG.REGISTER_TOURNAMENT, tournament.getName()));
		} catch(UnknownEntityException uee) {
			model.put(ERROR_MSG_VAR, uee.getMessage());
		} catch(Exception e) {
			LOG.error(ErrorInfoMSG.REGISTER_TOURNAMENT);
			model.put(ERROR_MSG_VAR, ErrorInfoMSG.REGISTER_TOURNAMENT);
		}
		try {
			model.put("tournaments", appService.listTournaments(new PatternSearchData<Tournament>(new Tournament())).getItems());
		} catch(Exception e) {
			LOG.error(ErrorInfoMSG.SERVER_ERROR, e);
			model.put(ERROR_MSG_VAR, ErrorInfoMSG.SERVER_ERROR);
			return "index";
		}
		return "tournament/list";
	}

	@RequestMapping(value = "/{id}/activate", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN_USER"})
	public String activate(@PathVariable("id") Integer id, Map model) {
		try {
			final Tournament tournament = appService.getTournamentById(id);
			appService.transitTournamentToActiveStatus(id);
			model.put(SUCCESS_MSG_VAR, String.format(SuccessInfoMSG.ACTIVATE_TOURNAMENT, tournament.getName()));
		} catch(UnknownEntityException uee) {
			model.put(ERROR_MSG_VAR, uee.getMessage());
		} catch(Exception e) {
			LOG.error(ErrorInfoMSG.ACTIVATE_TOURNAMENT);
			model.put(ERROR_MSG_VAR, ErrorInfoMSG.ACTIVATE_TOURNAMENT);
		}

		try {
			model.put("tournaments", appService.listTournaments(new PatternSearchData<Tournament>(new Tournament())).getItems());
		} catch(Exception e) {
			LOG.error(ErrorInfoMSG.SERVER_ERROR, e);
			model.put(ERROR_MSG_VAR, ErrorInfoMSG.SERVER_ERROR);
			return "index";
		}
		return "tournament/list";
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN_USER"})
	public String delete(@PathVariable("id") Integer id, Map model) {
		try {
			final Tournament tournament = appService.getTournamentById(id);
			appService.deleteTournament(id);
			model.put(SUCCESS_MSG_VAR, String.format(SuccessInfoMSG.DELETE_TOURNAMENT, tournament.getName()));
		} catch(UnknownEntityException uee) {
			model.put(ERROR_MSG_VAR, uee.getMessage());
		} catch(Exception e) {
			LOG.error(ErrorInfoMSG.DELETE_TOURNAMENT);
			model.put(ERROR_MSG_VAR, ErrorInfoMSG.DELETE_TOURNAMENT);
		}

		try {
			model.put("tournaments", appService.listTournaments(new PatternSearchData<Tournament>(new Tournament())).getItems());
		} catch(Exception e) {
			LOG.error(ErrorInfoMSG.SERVER_ERROR, e);
			model.put(ERROR_MSG_VAR, ErrorInfoMSG.SERVER_ERROR);
			return "index";
		}
		return "tournament/list";
	}

	@RequestMapping(value = "/{id}/finish", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN_USER"})
	public String finish(@PathVariable("id") Integer id, Map model) {
		try {
			final Tournament tournament = appService.getTournamentById(id);
			appService.transitTournamentToFinishedStatus(id);
			model.put(SUCCESS_MSG_VAR, String.format(SuccessInfoMSG.FINISH_TOURNAMENT, tournament.getName()));
		} catch(UnknownEntityException uee) {
			model.put(ERROR_MSG_VAR, uee.getMessage());
		} catch(Exception e) {
			LOG.error(ErrorInfoMSG.FINISH_TOURNAMENT);
			model.put(ERROR_MSG_VAR, ErrorInfoMSG.FINISH_TOURNAMENT);
		}

		try {
			model.put("tournaments", appService.listTournaments(new PatternSearchData<Tournament>(new Tournament())).getItems());
		} catch(Exception e) {
			LOG.error(ErrorInfoMSG.SERVER_ERROR, e);
			model.put(ERROR_MSG_VAR, ErrorInfoMSG.SERVER_ERROR);
			return "index";
		}
		return "tournament/list";
	}

	@RequestMapping(value = "/{id}/cancel", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN_USER"})
	public String cancel(@PathVariable("id") Integer id, Map model) {
		try {
			final Tournament tournament = appService.getTournamentById(id);
			appService.transitTournamentToCanceledStatus(id);
			model.put(SUCCESS_MSG_VAR, String.format(SuccessInfoMSG.CANCEL_TOURNAMENT, tournament.getName()));
		} catch(UnknownEntityException uee) {
			model.put(ERROR_MSG_VAR, uee.getMessage());
		} catch(Exception e) {
			LOG.error(ErrorInfoMSG.CANCEL_TOURNAMENT);
			model.put(ERROR_MSG_VAR, ErrorInfoMSG.CANCEL_TOURNAMENT);
		}

		try {
			model.put("tournaments", appService.listTournaments(new PatternSearchData<Tournament>(new Tournament())).getItems());
		} catch(Exception e) {
			LOG.error(ErrorInfoMSG.SERVER_ERROR, e);
			model.put(ERROR_MSG_VAR, ErrorInfoMSG.SERVER_ERROR);
			return "index";
		}
		return "tournament/list";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN_USER"})
	public String showCreateForm(Map model) {
		model.put("command", new TournamentCommand());
		return "tournament/create";
	}

	@RequestMapping(value = "/createProcess", method = RequestMethod.POST)
	@Secured({"ROLE_ADMIN_USER"})
	public String createProcess(@ModelAttribute("command") @Valid TournamentCommand command, BindingResult result, Model model) {

		if(result.hasErrors()) {
			model.addAttribute("command", command);
			return "tournament/create";
		}

		try {
			final Tournament tournament = new Tournament();
			tournament.setName(command.getName());
			tournament.setMaxParticipantsCount(command.getMax());
			tournament.setStatus(Tournament.Status.NEW);
			appService.insertTournament(tournament);
			model.addAttribute(SUCCESS_MSG_VAR, String.format(SuccessInfoMSG.CREATE_TOURNAMENT, tournament.getName()));
		} catch(WrongPasswordException wpe) {
			LOG.error(ErrorInfoMSG.CREATE_TOURNAMENT, wpe);
			model.addAttribute(ERROR_MSG_VAR, ErrorInfoMSG.CREATE_TOURNAMENT);
			model.addAttribute("command", new TournamentCommand());
			return "tournament/create";
		} catch(Exception e) {
			LOG.error(ErrorInfoMSG.UNKNOWN, e);
			model.addAttribute("command", new TournamentCommand());
			model.addAttribute(ERROR_MSG_VAR, ErrorInfoMSG.CREATE_TOURNAMENT);
			return "tournament/create";
		}
		try {
			model.addAttribute("tournaments", appService.listTournaments(new PatternSearchData<Tournament>(new Tournament())).getItems());
		} catch(Exception e) {
			LOG.error(ErrorInfoMSG.SERVER_ERROR, e);
			model.addAttribute(ERROR_MSG_VAR, ErrorInfoMSG.SERVER_ERROR);
			return "index";
		}
		return "tournament/list";
	}
}
