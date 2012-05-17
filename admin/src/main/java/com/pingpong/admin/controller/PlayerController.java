/**
 * Without Copyright
 */
package com.pingpong.admin.controller;

import com.pingpong.admin.ErrorInfoMSG;
import com.pingpong.admin.SuccessInfoMSG;
import com.pingpong.domain.Player;
import com.pingpong.shared.AppService;
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


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN_USER"})
	public String showListForm(Map model) {
		try {
			final Player player = new Player();
			model.put("players", appService.listPlayers(new PatternSearchData<Player>(player)).getItems());
		} catch(Exception e) {
			LOG.error(ErrorInfoMSG.SERVER_ERROR, e);
			model.put(ERROR_MSG_VAR, ErrorInfoMSG.SERVER_ERROR);
			return "index";
		}

		return "player/list";
	}

	@RequestMapping(value = "/{id}/activate", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN_USER"})
	public String activate(@PathVariable("id") Integer id, Map model) {
		try {
			final Player player = appService.getPlayerById(id);
			appService.activatePlayer(id);
			model.put(SUCCESS_MSG_VAR, String.format(SuccessInfoMSG.PLAYER_ACTIVATION, player.getName()));
		} catch(UnknownEntityException uee) {
			model.put(ERROR_MSG_VAR, uee.getMessage());
		} catch(Exception e) {
			LOG.error(ErrorInfoMSG.PLAYER_ACTIVATION);
			model.put(ERROR_MSG_VAR, ErrorInfoMSG.PLAYER_ACTIVATION);
		}

		try {
			model.put("players", appService.listPlayers(new PatternSearchData<Player>(new Player())).getItems());
		} catch(Exception e) {
			LOG.error(ErrorInfoMSG.SERVER_ERROR, e);
			model.put(ERROR_MSG_VAR, ErrorInfoMSG.SERVER_ERROR);
			return "index";
		}

		return "player/list";
	}

	@RequestMapping(value = "/{id}/block", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN_USER"})
	public String block(@PathVariable("id") Integer id, Map model) {
		try {
			final Player player = appService.getPlayerById(id);
			appService.blockPlayer(id);
			model.put(SUCCESS_MSG_VAR, String.format(SuccessInfoMSG.PLAYER_BLOCKING, player.getName()));
		} catch(UnknownEntityException uee) {
			model.put(ERROR_MSG_VAR, uee.getMessage());
		} catch(Exception e) {
			LOG.error(ErrorInfoMSG.PLAYER_BLOCKING);
			model.put(ERROR_MSG_VAR, ErrorInfoMSG.PLAYER_BLOCKING);
		}

		try {
			model.put("players", appService.listPlayers(new PatternSearchData<Player>(new Player())).getItems());
		} catch(Exception e) {
			LOG.error(ErrorInfoMSG.SERVER_ERROR, e);
			model.put(ERROR_MSG_VAR, ErrorInfoMSG.SERVER_ERROR);
			return "index";
		}

		return "player/list";
	}

	@RequestMapping(value = "/{id}/unblock", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN_USER"})
	public String unblock(@PathVariable("id") Integer id, Map model) {
		try {
			final Player player = appService.getPlayerById(id);
			appService.unblockPlayer(id);
			model.put(SUCCESS_MSG_VAR, String.format(SuccessInfoMSG.PLAYER_UNBLOCKING, player.getName()));
		} catch(UnknownEntityException uee) {
			model.put(ERROR_MSG_VAR, uee.getMessage());
		} catch(Exception e) {
			LOG.error(ErrorInfoMSG.PLAYER_UNBLOCKING);
			model.put(ERROR_MSG_VAR, ErrorInfoMSG.PLAYER_UNBLOCKING);
		}

		try {
			model.put("players", appService.listPlayers(new PatternSearchData<Player>(new Player())).getItems());
		} catch(Exception e) {
			LOG.error(ErrorInfoMSG.SERVER_ERROR, e);
			model.put(ERROR_MSG_VAR, ErrorInfoMSG.SERVER_ERROR);
			return "index";
		}

		return "player/list";
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN_USER"})
	public String delete(@PathVariable("id") Integer id, Map model) {
		try {
			final Player player = appService.getPlayerById(id);
			appService.deletePlayer(id);
			model.put(SUCCESS_MSG_VAR, String.format(SuccessInfoMSG.PLAYER_DELETING, player.getName()));
		} catch(UnknownEntityException uee) {
			model.put(ERROR_MSG_VAR, uee.getMessage());
		} catch(Exception e) {
			LOG.error(ErrorInfoMSG.PLAYER_DELETING);
			model.put(ERROR_MSG_VAR, ErrorInfoMSG.PLAYER_DELETING);
		}
		try {
			model.put("players", appService.listPlayers(new PatternSearchData<Player>(new Player())).getItems());
		} catch(Exception e) {
			LOG.error(ErrorInfoMSG.SERVER_ERROR, e);
			model.put(ERROR_MSG_VAR, ErrorInfoMSG.SERVER_ERROR);
			return "index";
		}

		return "player/list";
	}
}
