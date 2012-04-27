/**
 * Copyright U-wiss
 */
package com.pingpong.portal.controller;

import com.pingpong.domain.Account;
import com.pingpong.portal.command.ChangePasswordCommand;
import com.pingpong.portal.command.ForgotPasswordCommand;
import com.pingpong.portal.command.ResetPasswordCommand;
import com.pingpong.portal.security.AuthUser;
import com.pingpong.portal.validator.ChangePasswordValidator;
import com.pingpong.portal.validator.ResetPasswordValidator;
import com.pingpong.shared.AppService;
import com.pingpong.shared.exception.WrongPasswordException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Map;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 12/04/2012
 */
@Controller
@RequestMapping("/account")
public class AccountController extends AbstractBaseController {
	private static final Logger LOG = LoggerFactory.getLogger(AccountController.class);

	private static final String FORGOT_PASSWORD_REDIRECT = "account/forgotPassword";
	private static final String RESET_PASSWORD_ERROR_REDIRECT = "account/resetPasswordError";
	private static final String RESET_PASSWORD_REDIRECT = "account/resetPassword";
	private static final String CHANGE_PASSWORD_REDIRECT = "account/changePassword";


	@Autowired
	private AppService appService;
	@Autowired
	private ResetPasswordValidator resetPasswordValidator;
	@Autowired
	private ChangePasswordValidator changePasswordValidator;

	@RequestMapping(value = "/forgot_password", method = RequestMethod.GET)
	@Secured(value = "IS_AUTHENTICATED_ANONYMOUSLY")
	public String showForgotPasswordForm(Map model) {
		model.put("command", new ForgotPasswordCommand());
		return FORGOT_PASSWORD_REDIRECT;
	}

	@RequestMapping(value = "/reset_password/{id}", method = RequestMethod.GET)
	@Secured(value = "IS_AUTHENTICATED_ANONYMOUSLY")
	public String showResetPasswordForm(@PathVariable("id") String id, Map model) {
		final ResetPasswordCommand command = new ResetPasswordCommand();
		command.setForgotPasswordId(id);
		model.put("command", command);

		try {
			final Account account = appService.getAccountByForgotPasswordId(id);
			model.put("account", account);
		} catch (EntityNotFoundException enfe) {
			LOG.error("Reset link is not valid anymore", enfe);
			model.put(ERROR_MSG_VAR, "Reset link is not valid anymore");
			return RESET_PASSWORD_ERROR_REDIRECT;
		} catch (Exception e) {
			LOG.error("Unknown error", e);
			model.put(ERROR_MSG_VAR, "Unknown error");
			return RESET_PASSWORD_ERROR_REDIRECT;
		}

		return RESET_PASSWORD_REDIRECT;
	}

	@RequestMapping(value = "reset_password/resetPasswordProcess", method = RequestMethod.POST)
	@Secured(value = "IS_AUTHENTICATED_ANONYMOUSLY")
	public String resetPasswordProcess(@ModelAttribute("command") @Valid ResetPasswordCommand command, BindingResult result, Model model) {
		final Account account = appService.getAccountByForgotPasswordId(command.getForgotPasswordId());

		resetPasswordValidator.validate(command, result);

		if(result.hasErrors()) {
			model.addAttribute("account", account);
			return RESET_PASSWORD_REDIRECT;
		}

		try {
			appService.resetForgottenPassword(command.getForgotPasswordId(), command.getPass1());
		} catch(EntityNotFoundException enfe) {
			LOG.error("Not found account", enfe);
			model.addAttribute(ERROR_MSG_VAR, "Can't find such account");
			model.addAttribute("account", account);
			return RESET_PASSWORD_REDIRECT;
		} catch(Exception e) {
			LOG.error("ERROR", e);
			model.addAttribute("account", account);
			model.addAttribute(ERROR_MSG_VAR, "Couldn't send request about forgot password, try again please");
			return RESET_PASSWORD_REDIRECT;
		}
		return "account/resetPasswordSuccess";
	}

	@RequestMapping(value = "/forgotPasswordProcess", method = RequestMethod.POST)
	@Secured(value = "IS_AUTHENTICATED_ANONYMOUSLY")
	public String forgotPasswordProcess(@ModelAttribute("command") @Valid ForgotPasswordCommand command, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return FORGOT_PASSWORD_REDIRECT;
		}
		try {
			appService.requestForgotPassword(command.getUsername());
		} catch(EntityNotFoundException enfe) {
			LOG.error("Not found account", enfe);
			model.addAttribute(ERROR_MSG_VAR, "Can't find such account");
			return FORGOT_PASSWORD_REDIRECT;
		} catch(Exception e) {
			LOG.error("ERROR", e);
			model.addAttribute(ERROR_MSG_VAR, "Couldn't send request about forgot password, try again please");
			return FORGOT_PASSWORD_REDIRECT;
		}
		return "account/forgotPasswordThanks";
	}


	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	@Secured(value = "ROLE_PLAYER_USER")
	public String showChangePasswordForm(Map model) {
		model.put("command", new ChangePasswordCommand());
		return CHANGE_PASSWORD_REDIRECT;
	}
	@RequestMapping(value = "/changePasswordProcess", method = RequestMethod.POST)
	@Secured(value = "ROLE_PLAYER_USER")
	public String changePasswordProcess(@ModelAttribute("command") @Valid ChangePasswordCommand command, BindingResult result, Model model) {
		final AuthUser authUser = (AuthUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		changePasswordValidator.validate(command, result);

		if(result.hasErrors()) {
			model.addAttribute("command", command);
			return CHANGE_PASSWORD_REDIRECT;
		}

		try {
			appService.changePassword(authUser.getId(), command.getOldPass(), command.getNewPass1());
			model.addAttribute(SUCCESS_MSG_VAR, "Password was changed successfully");
		} catch(WrongPasswordException wpe) {
			LOG.error("Wrong old password", wpe);
			model.addAttribute(ERROR_MSG_VAR, "Wrong old password");
			model.addAttribute("command", new ChangePasswordCommand());
			return CHANGE_PASSWORD_REDIRECT;
		} catch(Exception e) {
			LOG.error("ERROR", e);
			model.addAttribute("command", new ChangePasswordCommand());
			model.addAttribute(ERROR_MSG_VAR, "Couldn't change password, try again please");
			return CHANGE_PASSWORD_REDIRECT;
		}
		return "index";
	}

	@RequestMapping(value = "/forgotPasswordThanks", method = RequestMethod.GET)
	@Secured(value = "IS_AUTHENTICATED_ANONYMOUSLY")
	public String showForgotPasswordThanks() {
		return "account/forgotPasswordThanks";
	}

	@RequestMapping(value = "/resetPasswordError", method = RequestMethod.GET)
	@Secured(value = "IS_AUTHENTICATED_ANONYMOUSLY")
	public String showResetPasswordError() {
		return RESET_PASSWORD_ERROR_REDIRECT;
	}

	@RequestMapping(value = "/resetPasswordSuccess", method = RequestMethod.GET)
	@Secured(value = "IS_AUTHENTICATED_ANONYMOUSLY")
	public String showResetPasswordSuccess() {
		return "account/resetPasswordSuccess";
	}
}
