/**
 * Copyright U-wiss
 */
package com.pingpong.portal.controller;

import com.pingpong.portal.command.LoginCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 01/02/2012
 */
@Controller
public class AuthController extends AbstractBaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginForm(Map model) {
		model.put("login", new LoginCommand());
		return "auth/login";
	}

	@RequestMapping(value="/loginFailed", method = RequestMethod.GET)
	public String loginError(ModelMap model) {
		model.addAttribute(ERROR_MSG_VAR, "Couldn't find player with specified data, try again please");
		return "auth/login";

	}
}
