/**
 * Copyright U-wiss
 */
package com.pingpong.admin.controller;

import com.pingpong.admin.ErrorInfoMSG;
import com.pingpong.admin.SuccessInfoMSG;
import com.pingpong.domain.Player;
import com.pingpong.shared.AppService;
import com.pingpong.shared.exception.UnknownEntityException;
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
 * @version 3.0
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
		model.put("players", appService.listPlayers());
		return "player/list";
	}

	@RequestMapping(value = "/{id}/activate", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN_USER"})
	public String activate(@PathVariable("id") String id, Map model) {
		try {
			final int playerId = Integer.parseInt(id);
			final Player player = appService.getPlayerById(playerId);
			appService.activatePlayer(playerId);
			model.put(SUCCESS_MSG_VAR, String.format(SuccessInfoMSG.PLAYER_ACTIVATION, player.getName()));
		} catch(UnknownEntityException uee) {
			model.put(ERROR_MSG_VAR, uee.getMessage());
		} catch(Exception e) {
			LOG.error(ErrorInfoMSG.PLAYER_ACTIVATION);
			model.put(ERROR_MSG_VAR, ErrorInfoMSG.PLAYER_ACTIVATION);
		}

		model.put("players", appService.listPlayers());

		return "player/list";
	}
}
