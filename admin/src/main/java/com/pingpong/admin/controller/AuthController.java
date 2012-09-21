/**
 * Without Copyright
 */
package com.pingpong.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 01/02/2012
 */
@Controller
public class AuthController extends AbstractBaseController {
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@Secured({"IS_AUTHENTICATED_ANONYMOUSLY"})
	public String showLoginForm() {
		return "auth/login";
	}

	@RequestMapping(value="/loginFailed", method = RequestMethod.GET)
	@Secured({"IS_AUTHENTICATED_ANONYMOUSLY"})
	public String loginError(ModelMap model, Locale locale) {
		String msg = messageSource.getMessage("springSecurity.errors.login.fail", null, locale);
		model.addAttribute(ERROR_MSG_VAR, msg);
		return "auth/login";
	}
}
