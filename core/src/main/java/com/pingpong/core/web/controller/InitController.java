/**
 * Copyright U-wiss
 */
package com.pingpong.core.web.controller;

import com.pingpong.core.dao.PlayerDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 27/12/2011
 */

@Controller
public class InitController {

	private static final Logger LOGGER = LoggerFactory.getLogger(InitController.class);

	@Autowired
	private PlayerDAO playerDAO;

	@RequestMapping("/players")
	public ModelAndView list() {
		LOGGER.info("Returning hello view");
		ModelAndView model = new ModelAndView("players");
		model.addObject("players", playerDAO.list());

		return model;
	}
}
