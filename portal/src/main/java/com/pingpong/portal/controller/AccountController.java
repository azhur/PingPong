/**
 * Without Copyright
 */
package com.pingpong.portal.controller;

import com.pingpong.domain.Account;
import com.pingpong.domain.Player;
import com.pingpong.domain.enumeration.Gender;
import com.pingpong.portal.ErrorInfoMSG;
import com.pingpong.portal.SuccessInfoMSG;
import com.pingpong.portal.command.ChangePasswordCommand;
import com.pingpong.portal.command.ChangeProfileCommand;
import com.pingpong.portal.command.ForgotPasswordCommand;
import com.pingpong.portal.command.ResetPasswordCommand;
import com.pingpong.portal.security.AuthUser;
import com.pingpong.portal.security.SpringSecurityUtils;
import com.pingpong.portal.validator.ChangePasswordValidator;
import com.pingpong.portal.validator.ResetPasswordValidator;
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
		return "account/forgotPassword";
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
		} catch(EntityNotFoundException enfe) {
			LOG.error(ErrorInfoMSG.RESET_PASSWORD_LINK, enfe);
			model.put(ERROR_MSG_VAR, ErrorInfoMSG.RESET_PASSWORD_LINK);
			return "index";
		} catch(Exception e) {
			LOG.error(ErrorInfoMSG.UNKNOWN, e);
			model.put(ERROR_MSG_VAR, ErrorInfoMSG.UNKNOWN);
			return "index";
		}

		return "account/resetPassword";
	}

	@RequestMapping(value = "reset_password/resetPasswordProcess", method = RequestMethod.POST)
	@Secured({"IS_AUTHENTICATED_ANONYMOUSLY"})
	public String resetPasswordProcess(@ModelAttribute("command") @Valid ResetPasswordCommand command, BindingResult result, Model model) {

		Account account = new Account();
		try {
			account = appService.getAccountByForgotPasswordId(command.getForgotPasswordId());

			resetPasswordValidator.validate(command, result);

			if(result.hasErrors()) {
				model.addAttribute("account", account);
				return "account/resetPassword";
			}

			appService.resetForgottenPassword(command.getForgotPasswordId(), command.getPass1());
		} catch(EntityNotFoundException enfe) {
			LOG.error(ErrorInfoMSG.NOT_FOUND_ACCOUNT, enfe);
			model.addAttribute(ERROR_MSG_VAR, ErrorInfoMSG.NOT_FOUND_ACCOUNT);
			model.addAttribute("account", account);
			return "account/resetPassword";
		} catch(Exception e) {
			LOG.error(ErrorInfoMSG.UNKNOWN, e);
			model.addAttribute("account", account);
			model.addAttribute(ERROR_MSG_VAR, ErrorInfoMSG.FORGOT_PASSWORD_NOT_SEND_REQUEST);
			return "account/resetPassword";
		}

		model.addAttribute(SUCCESS_MSG_VAR, SuccessInfoMSG.RESET_PASSWORD);
		return "index";
	}

	@RequestMapping(value = "/forgotPasswordProcess", method = RequestMethod.POST)
	@Secured({"IS_AUTHENTICATED_ANONYMOUSLY"})
	public String forgotPasswordProcess(@ModelAttribute("command") @Valid ForgotPasswordCommand command, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "account/forgotPassword";
		}
		try {
			appService.requestPlayerForgotPassword(command.getUsername());
			model.addAttribute(SUCCESS_MSG_VAR, SuccessInfoMSG.FORGOT_PASSWORD);
		} catch(EntityNotFoundException enfe) {
			LOG.error(ErrorInfoMSG.NOT_FOUND_ACCOUNT, enfe);
			model.addAttribute(ERROR_MSG_VAR, ErrorInfoMSG.NOT_FOUND_ACCOUNT);
			return "account/forgotPassword";
		} catch(Exception e) {
			LOG.error(ErrorInfoMSG.UNKNOWN, e);
			model.addAttribute(ERROR_MSG_VAR, ErrorInfoMSG.FORGOT_PASSWORD_NOT_SEND_REQUEST);
			return "account/forgotPassword";
		}
		return "index";
	}


	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	@Secured({"ROLE_PLAYER_USER"})
	public String showChangePasswordForm(Map model) {
		model.put("command", new ChangePasswordCommand());
		return "account/changePassword";
	}

	@RequestMapping(value = "/changePasswordProcess", method = RequestMethod.POST)
	@Secured({"ROLE_PLAYER_USER"})
	public String changePasswordProcess(@ModelAttribute("command") @Valid ChangePasswordCommand command, BindingResult result, Model model) {
		final AuthUser authUser = SpringSecurityUtils.getCurrentUser();

		changePasswordValidator.validate(command, result);

		if(result.hasErrors()) {
			model.addAttribute("command", command);
			return "account/changePassword";
		}

		try {
			appService.changePassword(authUser.getId(), command.getOldPass(), command.getNewPass1());
			model.addAttribute(SUCCESS_MSG_VAR, SuccessInfoMSG.CHANGE_PASSWORD);
		} catch(WrongPasswordException wpe) {
			LOG.error(ErrorInfoMSG.WRONG_OLD_PASSWORD, wpe);
			model.addAttribute(ERROR_MSG_VAR, ErrorInfoMSG.WRONG_OLD_PASSWORD);
			model.addAttribute("command", new ChangePasswordCommand());
			return "account/changePassword";
		} catch(Exception e) {
			LOG.error(ErrorInfoMSG.UNKNOWN, e);
			model.addAttribute("command", new ChangePasswordCommand());
			model.addAttribute(ERROR_MSG_VAR, ErrorInfoMSG.CHANGE_PASSWORD);
			return "account/changePassword";
		}
		return "index";
	}

	@RequestMapping(value = "/changeProfile", method = RequestMethod.GET)
	@Secured({"ROLE_PLAYER_USER"})
	public String showChangeProfileForm(Map model) {
		try {
			final Player player = appService.getPlayerAccountByEmail(SpringSecurityUtils.getCurrentUser().getUsername()).getPlayer();
			final ChangeProfileCommand command = new ChangeProfileCommand();
			command.setEmail(SpringSecurityUtils.getCurrentUser().getUsername());
			command.setName(player.getName());
			command.setGender(player.getGender());
			command.setBirth(player.getBirth());

			model.put("command", command);
		} catch(Exception e) {
			LOG.error(ErrorInfoMSG.SERVER_ERROR, e);
			model.put(ERROR_MSG_VAR, ErrorInfoMSG.SERVER_ERROR);
			return "index";
		}
		return "account/changeProfile";
	}

	@RequestMapping(value = "/changeProfileProcess", method = RequestMethod.POST)
	@Secured({"ROLE_PLAYER_USER"})
	public String changeProfileProcess(@ModelAttribute("command") @Valid ChangeProfileCommand command, BindingResult result, Model model) {
		if(result.hasErrors()) {
			command.setEmail(SpringSecurityUtils.getCurrentUser().getUsername());
			model.addAttribute("command", command);
			return "account/changeProfile";
		}

		try {
			final Player player = appService.getPlayerAccountByEmail(SpringSecurityUtils.getCurrentUser().getUsername()).getPlayer();

			player.setName(command.getName());
			player.setGender(command.getGender());
			player.setBirth(command.getBirth());

			appService.updatePlayer(player);
			model.addAttribute(SUCCESS_MSG_VAR, SuccessInfoMSG.CHANGE_PROFILE);
		} catch(Exception e) {
			LOG.error(ErrorInfoMSG.UNKNOWN, e);
			command.setEmail(SpringSecurityUtils.getCurrentUser().getUsername());
			model.addAttribute("command", command);
			model.addAttribute(ERROR_MSG_VAR, ErrorInfoMSG.CHANGE_PROFILE);
			return "account/changeProfile";
		}
		return "index";
	}

	@ModelAttribute("genderItems")
	public Gender[] populateGenderItems() {
		return Gender.values();
	}
}
