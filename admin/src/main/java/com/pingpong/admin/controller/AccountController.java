/**
 * Copyright U-wiss
 */
package com.pingpong.admin.controller;

import com.pingpong.admin.ErrorInfoMSG;
import com.pingpong.admin.SuccessInfoMSG;
import com.pingpong.admin.command.ChangePasswordCommand;
import com.pingpong.admin.command.ForgotPasswordCommand;
import com.pingpong.admin.command.ResetPasswordCommand;
import com.pingpong.admin.security.AuthUser;
import com.pingpong.admin.security.SpringSecurityUtils;
import com.pingpong.admin.validator.ChangePasswordValidator;
import com.pingpong.admin.validator.ResetPasswordValidator;
import com.pingpong.domain.Account;
import com.pingpong.shared.AppService;
import com.pingpong.shared.exception.WrongPasswordException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
	private static final String RESET_PASSWORD_REDIRECT = "account/resetPassword";
	private static final String CHANGE_PASSWORD_REDIRECT = "account/changePassword";


	@Autowired
	private AppService appService;
	@Autowired
	private ResetPasswordValidator resetPasswordValidator;
	@Autowired
	private ChangePasswordValidator changePasswordValidator;

	@RequestMapping(value = "/forgot_password", method = RequestMethod.GET)
	@Secured({"IS_AUTHENTICATED_ANONYMOUSLY"})
	public String showForgotPasswordForm(Map model) {
		model.put("command", new ForgotPasswordCommand());
		return FORGOT_PASSWORD_REDIRECT;
	}

	@RequestMapping(value = "/reset_password/{id}", method = RequestMethod.GET)
	@Secured({"IS_AUTHENTICATED_ANONYMOUSLY"})
	public String showResetPasswordForm(@PathVariable("id") String id, Map model) {
		final ResetPasswordCommand command = new ResetPasswordCommand();
		command.setForgotPasswordId(id);
		model.put("command", command);

		try {
			final Account account = appService.getAccountByForgotPasswordId(id);
			model.put("account", account);
		} catch (EntityNotFoundException enfe) {
			LOG.error(ErrorInfoMSG.RESET_PASSWORD_LINK, enfe);
			model.put(ERROR_MSG_VAR, ErrorInfoMSG.RESET_PASSWORD_LINK);
			return "index";
		} catch (Exception e) {
			LOG.error(ErrorInfoMSG.UNKNOWN, e);
			model.put(ERROR_MSG_VAR, ErrorInfoMSG.UNKNOWN);
			return "index";
		}

		return RESET_PASSWORD_REDIRECT;
	}

	@RequestMapping(value = "reset_password/resetPasswordProcess", method = RequestMethod.POST)
	@Secured({"IS_AUTHENTICATED_ANONYMOUSLY"})
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
			LOG.error(ErrorInfoMSG.NOT_FOUND_ACCOUNT, enfe);
			model.addAttribute(ERROR_MSG_VAR, ErrorInfoMSG.NOT_FOUND_ACCOUNT);
			model.addAttribute("account", account);
			return RESET_PASSWORD_REDIRECT;
		} catch(Exception e) {
			LOG.error(ErrorInfoMSG.UNKNOWN, e);
			model.addAttribute("account", account);
			model.addAttribute(ERROR_MSG_VAR, ErrorInfoMSG.FORGOT_PASSWORD_NOT_SEND_REQUEST);
			return RESET_PASSWORD_REDIRECT;
		}

		model.addAttribute(SUCCESS_MSG_VAR, SuccessInfoMSG.RESET_PASSWORD);
		return "index";
	}

	@RequestMapping(value = "/forgotPasswordProcess", method = RequestMethod.POST)
	@Secured({"IS_AUTHENTICATED_ANONYMOUSLY"})
	public String forgotPasswordProcess(@ModelAttribute("command") @Valid ForgotPasswordCommand command, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return FORGOT_PASSWORD_REDIRECT;
		}
		try {
			appService.requestAdminForgotPassword(command.getUsername());
			model.addAttribute(SUCCESS_MSG_VAR, SuccessInfoMSG.FORGOT_PASSWORD);
		} catch(EntityNotFoundException enfe) {
			LOG.error(ErrorInfoMSG.NOT_FOUND_ACCOUNT, enfe);
			model.addAttribute(ERROR_MSG_VAR, ErrorInfoMSG.NOT_FOUND_ACCOUNT);
			return FORGOT_PASSWORD_REDIRECT;
		} catch(Exception e) {
			LOG.error(ErrorInfoMSG.UNKNOWN, e);
			model.addAttribute(ERROR_MSG_VAR, ErrorInfoMSG.FORGOT_PASSWORD_NOT_SEND_REQUEST);
			return FORGOT_PASSWORD_REDIRECT;
		}
		return "index";
	}


	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN_USER"})
	public String showChangePasswordForm(Map model) {
		model.put("command", new ChangePasswordCommand());
		return CHANGE_PASSWORD_REDIRECT;
	}
	@RequestMapping(value = "/changePasswordProcess", method = RequestMethod.POST)
	@Secured({"ROLE_ADMIN_USER"})
	public String changePasswordProcess(@ModelAttribute("command") @Valid ChangePasswordCommand command, BindingResult result, Model model) {
		final AuthUser authUser = SpringSecurityUtils.getCurrentUser();

		changePasswordValidator.validate(command, result);

		if(result.hasErrors()) {
			model.addAttribute("command", command);
			return CHANGE_PASSWORD_REDIRECT;
		}

		try {
			appService.changePassword(authUser.getId(), command.getOldPass(), command.getNewPass1());
			model.addAttribute(SUCCESS_MSG_VAR, SuccessInfoMSG.CHANGE_PASSWORD);
		} catch(WrongPasswordException wpe) {
			LOG.error(ErrorInfoMSG.WRONG_OLD_PASSWORD, wpe);
			model.addAttribute(ERROR_MSG_VAR, ErrorInfoMSG.WRONG_OLD_PASSWORD);
			model.addAttribute("command", new ChangePasswordCommand());
			return CHANGE_PASSWORD_REDIRECT;
		} catch(Exception e) {
			LOG.error(ErrorInfoMSG.UNKNOWN, e);
			model.addAttribute("command", new ChangePasswordCommand());
			model.addAttribute(ERROR_MSG_VAR, ErrorInfoMSG.CHANGE_PASSWORD);
			return CHANGE_PASSWORD_REDIRECT;
		}
		return "index";
	}
}
