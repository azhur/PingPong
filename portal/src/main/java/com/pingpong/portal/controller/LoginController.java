/**
 * Copyright U-wiss
 */
package com.pingpong.portal.controller;

import com.pingpong.portal.command.LoginCommand;
import com.pingpong.shared.AppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 01/02/2012
 */
@Controller
@RequestMapping("/login")
public class LoginController extends AbstractBaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private AppService appService;

	@RequestMapping(method = RequestMethod.GET)
	public String showLoginForm(Map model) {
		model.put("login", new LoginCommand());
		return "login/login";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String login(@ModelAttribute("login") LoginCommand command) {
		return "redirect:/";
	}
}