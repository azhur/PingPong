/**
 * Copyright U-wiss
 */
package com.pingpong.admin.controller;

import com.pingpong.admin.command.TournamentCommand;
import com.pingpong.domain.Tournament;
import com.pingpong.shared.AppService;
import com.pingpong.shared.exception.UnknownEntityException;
import com.pingpong.shared.hibernate.ListResult;
import com.pingpong.shared.hibernate.PatternSearchData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Locale;
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
	@Autowired
	private MessageSource messageSource;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN_USER"})
	public String showListForm(Map model, Locale locale) {
		try {
			final ListResult<Tournament> list = appService.listTournaments(new PatternSearchData<Tournament>(new Tournament()));
			model.put("tournaments", list.getItems());
		} catch(Exception e) {
			LOG.error("Server error", e);
			model.put(ERROR_MSG_VAR, messageSource.getMessage("server.error", null, locale));
			return "index";
		}
		return "tournament/list";
	}

	@RequestMapping(value = "/{id}/registration", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN_USER"})
	public String registration(@PathVariable("id") Integer id, Map model, Locale locale) {
		try {
			final Tournament tournament = loadTournamentById(id);
			appService.transitTournamentToRegistrationStatus(id);
			model.put(SUCCESS_MSG_VAR, messageSource.getMessage("tournament.register.success", new String[] {tournament.getName()}, locale));
		} catch(UnknownEntityException uee) {
			model.put(ERROR_MSG_VAR, uee.getMessage());
		} catch(Exception e) {
			LOG.error("Error", e);
			model.put(ERROR_MSG_VAR, messageSource.getMessage("tournament.register.error", null, locale));
		}

		return view(id, model, locale);
	}

	@RequestMapping(value = "/{id}/activate", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN_USER"})
	public String activate(@PathVariable("id") Integer id, Map model, Locale locale) {
		try {
			final Tournament tournament = loadTournamentById(id);
			appService.transitTournamentToActiveStatus(id);
			model.put(SUCCESS_MSG_VAR, messageSource.getMessage("tournament.activate.success", new String[] {tournament.getName()}, locale));
		} catch(UnknownEntityException uee) {
			model.put(ERROR_MSG_VAR, uee.getMessage());
		} catch(Exception e) {
			LOG.error("Error", e);
			model.put(ERROR_MSG_VAR, messageSource.getMessage("tournament.activate.error", null, locale));
		}

		return view(id, model, locale);
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN_USER"})
	public String delete(@PathVariable("id") Integer id, Map model, Locale locale) {
		try {
			final Tournament tournament = loadTournamentById(id);
			appService.deleteTournament(id);
			model.put(SUCCESS_MSG_VAR, messageSource.getMessage("tournament.delete.success", new String[] {tournament.getName()}, locale));
		} catch(UnknownEntityException uee) {
			model.put(ERROR_MSG_VAR, uee.getMessage());
		} catch(Exception e) {
			LOG.error("Error", e);
			model.put(ERROR_MSG_VAR, messageSource.getMessage("tournament.delete.error", null, locale));
		}

		return view(id, model, locale);
	}

	@RequestMapping(value = "/{id}/finish", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN_USER"})
	public String finish(@PathVariable("id") Integer id, Map model, Locale locale) {
		try {
			final Tournament tournament = loadTournamentById(id);
			appService.transitTournamentToFinishedStatus(id);
			model.put(SUCCESS_MSG_VAR, messageSource.getMessage("tournament.finish.success", new String[] {tournament.getName()}, locale));
		} catch(UnknownEntityException uee) {
			model.put(ERROR_MSG_VAR, uee.getMessage());
		} catch(Exception e) {
			LOG.error("Error", e);
			model.put(ERROR_MSG_VAR, messageSource.getMessage("tournament.finish.error", null, locale));
		}

		return view(id, model, locale);
	}

	@RequestMapping(value = "/{id}/cancel", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN_USER"})
	public String cancel(@PathVariable("id") Integer id, Map model, Locale locale) {
		try {
			final Tournament tournament = loadTournamentById(id);
			appService.transitTournamentToCanceledStatus(id);
			model.put(SUCCESS_MSG_VAR, messageSource.getMessage("tournament.cancel.success", new String[] {tournament.getName()}, locale));
		} catch(UnknownEntityException uee) {
			model.put(ERROR_MSG_VAR, uee.getMessage());
		} catch(Exception e) {
			LOG.error("Error", e);
			model.put(ERROR_MSG_VAR, messageSource.getMessage("tournament.cancel.error", null, locale));
		}

		return view(id, model, locale);
	}

	@RequestMapping(value = "/{id}/view", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN_USER"})
	public String view(@PathVariable("id") Integer id, Map model, Locale locale) {
		try {
			final Tournament tournament = loadTournamentById(id);
			model.put("tournament", tournament);
		} catch(UnknownEntityException uee) {
			model.put(ERROR_MSG_VAR, uee.getMessage());
		} catch(Exception e) {
			LOG.error("Error", e);
			model.put(ERROR_MSG_VAR, messageSource.getMessage("server.error", null, locale));
		}

	    return "tournament/view";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN_USER"})
	public String showCreateForm(Map model) {
		model.put("command", new TournamentCommand());
		return "tournament/create";
	}

	@RequestMapping(value = "/createProcess", method = RequestMethod.POST)
	@Secured({"ROLE_ADMIN_USER"})
	public String createProcess(@ModelAttribute("command") @Valid TournamentCommand command, BindingResult result, Model model, Locale locale) {

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
			model.addAttribute(SUCCESS_MSG_VAR, messageSource.getMessage("tournament.create.success", new String[] {tournament.getName()}, locale));
		}  catch(Exception e) {
			LOG.error("Error", e);
			model.addAttribute("command", new TournamentCommand());
			model.addAttribute(ERROR_MSG_VAR, messageSource.getMessage("tournament.create.error", null, locale));
			return "tournament/create";
		}

		return showListForm(model.asMap(), locale);
	}

	private Tournament loadTournamentById(Integer id) {
		final Tournament tournament = appService.getTournamentById(id);

		if (tournament == null) {
			throw new UnknownEntityException(Tournament.class, id);
		}

		return tournament;
	}
}
