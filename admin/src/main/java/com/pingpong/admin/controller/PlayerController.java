/**
 * Copyright U-wiss
 */
package com.pingpong.admin.controller;

import com.pingpong.domain.Player;
import com.pingpong.shared.AppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 27/12/2011
 */

@Controller
public class PlayerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PlayerController.class);

	@Autowired
	private AppService appService;

	@RequestMapping("/players")
	public ModelAndView list() {
		LOGGER.info("Returning hello view");
		ModelAndView model = new ModelAndView("player/list");
		model.addObject("players", appService.listPlayers());

		return model;
	}

	@RequestMapping("/player/add")
	public ModelAndView redirectToPlayer() {
		ModelAndView modelAndView = new ModelAndView("player/player");
		modelAndView.addObject("player", new Player());
		return modelAndView;
	}

	@RequestMapping(value = "/player/add", method = RequestMethod.POST)
    public String addContact(@ModelAttribute("player") Player player, BindingResult result) {

        appService.insertPlayer(player);

        return "redirect:/players";
    }
}
