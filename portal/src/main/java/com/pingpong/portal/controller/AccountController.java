/**
 * Copyright U-wiss
 */
package com.pingpong.portal.controller;

import com.pingpong.portal.command.ForgotPasswordCommand;
import com.pingpong.shared.AppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Map;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 12/04/2012
 */
@Controller
@RequestMapping("/account")
public class AccountController extends AbstractBaseController {
	private static final Logger LOG = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AppService appService;

	@RequestMapping(value = "/forgot_password", method = RequestMethod.GET)
	/*@Secured(value = "isAnonymous()")*/
	public String showForgotPasswordForm(Map model) {
		model.put("command", new ForgotPasswordCommand());
		return "account/forgotPassword";
	}

	@RequestMapping(value = "/forgotPasswordProcess", method = RequestMethod.POST)
	/*@Secured(value = "isAnonymous()")*/
	public String forgotPasswordProcess(@ModelAttribute("command") @Valid ForgotPasswordCommand command, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "account/forgotPassword";
		}
		try {
			appService.requestForgotPassword(command.getUsername());
		} catch(EntityNotFoundException enfe) {
			LOG.error("Not found account", enfe);
			model.addAttribute(ERROR_MSG_VAR, "Can't find such account");
			return "account/forgotPassword";
		} catch(Exception e) {
			LOG.error("ERROR", e);
			model.addAttribute(ERROR_MSG_VAR, "Couldn't send request about forgot password, try again please");
			return "account/forgotPassword";
		}
		return "account/forgotPasswordThanks";
	}

	@RequestMapping(value = "/forgotPasswordThanks", method = RequestMethod.GET)
	/*@Secured(value = "isAnonymous()")*/
	public String showForgotPasswordThanks() {
		return "account/forgotPasswordThanks";
	}
}
