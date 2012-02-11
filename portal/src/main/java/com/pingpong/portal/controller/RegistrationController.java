/**
 * Copyright U-wiss
 */
package com.pingpong.portal.controller;

import com.pingpong.domain.enumeration.Gender;
import com.pingpong.portal.command.PlayerRegistrationCommand;
import com.pingpong.shared.AppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 31/01/2012
 */
@Controller
public class RegistrationController {
	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

	@Autowired
	private AppService appService;

	@RequestMapping("/registration")
	public ModelAndView registration() {
		ModelAndView model = new ModelAndView("registration/registration");
		model.addObject("registration", new PlayerRegistrationCommand());
		model.addObject("genders", Gender.values());

		return model;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@ModelAttribute("registration") PlayerRegistrationCommand command) {
		return "redirect:/";
	}
}
