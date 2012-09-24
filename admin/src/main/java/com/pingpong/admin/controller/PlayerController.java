/**
 * Without Copyright
 */
package com.pingpong.admin.controller;

import com.pingpong.domain.Player;
import com.pingpong.shared.AppService;
import com.pingpong.shared.exception.UnknownEntityException;
import com.pingpong.shared.hibernate.PatternSearchData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;
import java.util.Map;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 11/05/2012
 */
@Controller
@RequestMapping("/player")
public class PlayerController extends AbstractBaseController {
	private static final Logger LOG = LoggerFactory.getLogger(PlayerController.class);

	@Autowired
	private AppService appService;
	@Autowired
	private MessageSource messageSource;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN_USER"})
	public String showListForm(Map model, Locale locale) {
		try {
			final Player player = new Player();
			model.put("players", appService.listPlayers(new PatternSearchData<Player>(player)).getItems());
		} catch(Exception e) {
			LOG.error("Error", e);
			model.put(ERROR_MSG_VAR, messageSource.getMessage("server.error", null, locale));
			return "index";
		}

		return "player/list";
	}

	@RequestMapping(value = "/{id}/activate", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN_USER"})
	public String activate(@PathVariable("id") Integer id, Map model, Locale locale) {
		try {
			final Player player = appService.getPlayerById(id);
			appService.activatePlayer(id);
			model.put(SUCCESS_MSG_VAR, messageSource.getMessage("player.activate.success", new String[] {player.getName()}, locale));
		} catch(UnknownEntityException uee) {
			model.put(ERROR_MSG_VAR, uee.getMessage());
		} catch(Exception e) {
			LOG.error("Error", e);
			model.put(ERROR_MSG_VAR, messageSource.getMessage("player.activate.error", null, locale));
		}

		return showListForm(model, locale);
	}

	@RequestMapping(value = "/{id}/block", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN_USER"})
	public String block(@PathVariable("id") Integer id, Map model, Locale locale) {
		try {
			final Player player = appService.getPlayerById(id);
			appService.blockPlayer(id);
			model.put(SUCCESS_MSG_VAR, messageSource.getMessage("player.block.success", new String[] {player.getName()}, locale));
		} catch(UnknownEntityException uee) {
			model.put(ERROR_MSG_VAR, uee.getMessage());
		} catch(Exception e) {
			LOG.error("Error", e);
			model.put(ERROR_MSG_VAR, messageSource.getMessage("player.block.error", null, locale));
		}

		return showListForm(model, locale);
	}

	@RequestMapping(value = "/{id}/unblock", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN_USER"})
	public String unblock(@PathVariable("id") Integer id, Map model, Locale locale) {
		try {
			final Player player = appService.getPlayerById(id);
			appService.unblockPlayer(id);
			model.put(SUCCESS_MSG_VAR, messageSource.getMessage("player.unblock.success", new String[] {player.getName()}, locale));
		} catch(UnknownEntityException uee) {
			model.put(ERROR_MSG_VAR, uee.getMessage());
		} catch(Exception e) {
			LOG.error("Error", e);
			model.put(ERROR_MSG_VAR, messageSource.getMessage("player.unblock.error", null, locale));
		}

		return showListForm(model, locale);
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN_USER"})
	public String delete(@PathVariable("id") Integer id, Map model, Locale locale) {
		try {
			final Player player = appService.getPlayerById(id);
			appService.deletePlayer(id);
			model.put(SUCCESS_MSG_VAR, messageSource.getMessage("player.delete.success", new String[] {player.getName()}, locale));
		} catch(UnknownEntityException uee) {
			model.put(ERROR_MSG_VAR, uee.getMessage());
		} catch(Exception e) {
			LOG.error("Error", e);
			model.put(ERROR_MSG_VAR, messageSource.getMessage("player.delete.error", null, locale));
		}

		return showListForm(model, locale);
	}
}
