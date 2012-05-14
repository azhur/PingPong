/**
 * Without Copyright
 */
package com.pingpong.admin.controller;

import com.pingpong.admin.ErrorInfoMSG;
import com.pingpong.admin.SuccessInfoMSG;
import com.pingpong.admin.command.ChangePasswordCommand;
import com.pingpong.admin.command.ForgotPasswordCommand;
import com.pingpong.admin.command.NewAccountCommand;
import com.pingpong.admin.command.ResetPasswordCommand;
import com.pingpong.admin.security.AuthUser;
import com.pingpong.admin.security.SpringSecurityUtils;
import com.pingpong.admin.validator.ChangePasswordValidator;
import com.pingpong.admin.validator.NewAccountValidator;
import com.pingpong.admin.validator.ResetPasswordValidator;
import com.pingpong.domain.Account;
import com.pingpong.domain.AdminAccount;
import com.pingpong.shared.AppService;
import com.pingpong.shared.exception.UnknownEntityException;
import com.pingpong.shared.exception.WrongPasswordException;
import com.pingpong.shared.hibernate.PatternSearchData;
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
	@Autowired
	private NewAccountValidator newAccountValidator;

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
		final Account account = appService.getAccountByForgotPasswordId(command.getForgotPasswordId());

		resetPasswordValidator.validate(command, result);

		if(result.hasErrors()) {
			model.addAttribute("account", account);
			return "account/resetPassword";
		}

		try {
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
			appService.requestAdminForgotPassword(command.getUsername());
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
	@Secured({"ROLE_ADMIN_USER"})
	public String showChangePasswordForm(Map model) {
		model.put("command", new ChangePasswordCommand());
		return "account/changePassword";
	}

	@RequestMapping(value = "/changePasswordProcess", method = RequestMethod.POST)
	@Secured({"ROLE_ADMIN_USER"})
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

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN_USER"})
	public String showListForm(Map model) {
		model.put("admins", appService.listAdminAccounts(new PatternSearchData<AdminAccount>(new AdminAccount())).getItems());
		return "account/list";
	}

	@RequestMapping(value = "/{id}/block", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN_USER"})
	public String block(@PathVariable("id") String id, Map model) {
		try {
			final int accountId = Integer.parseInt(id);
			final AdminAccount admin = appService.getAdminAccountById(accountId);
			appService.blockAdminAccount(accountId);
			model.put(SUCCESS_MSG_VAR, String.format(SuccessInfoMSG.ADMIN_BLOCKING, admin.getEmail()));
		} catch(UnknownEntityException uee) {
			model.put(ERROR_MSG_VAR, uee.getMessage());
		} catch(Exception e) {
			LOG.error(ErrorInfoMSG.ADMIN_BLOCKING);
			model.put(ERROR_MSG_VAR, ErrorInfoMSG.ADMIN_BLOCKING);
		}

		model.put("admins", appService.listAdminAccounts(new PatternSearchData<AdminAccount>(new AdminAccount())).getItems());

		return "account/list";
	}

	@RequestMapping(value = "/{id}/unblock", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN_USER"})
	public String unblock(@PathVariable("id") String id, Map model) {
		try {
			final int accountId = Integer.parseInt(id);
			final AdminAccount admin = appService.getAdminAccountById(accountId);
			appService.unblockAdminAccount(accountId);
			model.put(SUCCESS_MSG_VAR, String.format(SuccessInfoMSG.ADMIN_UNBLOCKING, admin.getEmail()));
		} catch(UnknownEntityException uee) {
			model.put(ERROR_MSG_VAR, uee.getMessage());
		} catch(Exception e) {
			LOG.error(ErrorInfoMSG.ADMIN_UNBLOCKING);
			model.put(ERROR_MSG_VAR, ErrorInfoMSG.ADMIN_UNBLOCKING);
		}

		model.put("admins", appService.listAdminAccounts(new PatternSearchData<AdminAccount>(new AdminAccount())).getItems());

		return "account/list";
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN_USER"})
	public String delete(@PathVariable("id") String id, Map model) {
		try {
			final AuthUser authUser = SpringSecurityUtils.getCurrentUser();
			final int accountId = Integer.parseInt(id);

			if(accountId == authUser.getId()) {
				model.put(ERROR_MSG_VAR, SuccessInfoMSG.ADMIN_DELETING_YOURSELF);
			} else {
				final AdminAccount admin = appService.getAdminAccountById(accountId);
				appService.deleteAdminAccount(accountId);
				model.put(SUCCESS_MSG_VAR, String.format(SuccessInfoMSG.ADMIN_DELETING, admin.getEmail()));
			}
		} catch(UnknownEntityException uee) {
			model.put(ERROR_MSG_VAR, uee.getMessage());
		} catch(Exception e) {
			LOG.error(ErrorInfoMSG.ADMIN_DELETING);
			model.put(ERROR_MSG_VAR, ErrorInfoMSG.ADMIN_DELETING);
		}

		model.put("admins", appService.listAdminAccounts(new PatternSearchData<AdminAccount>(new AdminAccount())).getItems());

		return "account/list";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN_USER"})
	public String showCreateForm(Map model) {
		model.put("command", new NewAccountCommand());
		return "account/create";
	}

	@RequestMapping(value = "/createProcess", method = RequestMethod.POST)
	@Secured({"ROLE_ADMIN_USER"})
	public String createProcess(@ModelAttribute("command") @Valid NewAccountCommand command, BindingResult result, Model model) {
		newAccountValidator.validate(command, result);

		if(result.hasErrors()) {
			model.addAttribute("command", command);
			return "account/create";
		}

		try {
			final AdminAccount account = new AdminAccount();
			account.setEnabled(true);
			account.setEmail(command.getEmail());
			account.setPassword(command.getPass1());
			appService.createAdminAccount(account);
			model.addAttribute(SUCCESS_MSG_VAR, String.format(SuccessInfoMSG.CREATE_ACCOUNT, account.getEmail()));
		} catch(WrongPasswordException wpe) {
			LOG.error(ErrorInfoMSG.CREATE_ACCOUNT, wpe);
			model.addAttribute(ERROR_MSG_VAR, ErrorInfoMSG.CREATE_ACCOUNT);
			model.addAttribute("command", new NewAccountCommand());
			return "account/create";
		} catch(Exception e) {
			LOG.error(ErrorInfoMSG.UNKNOWN, e);
			model.addAttribute("command", new NewAccountCommand());
			model.addAttribute(ERROR_MSG_VAR, ErrorInfoMSG.CREATE_ACCOUNT);
			return "account/create";
		}
		model.addAttribute("admins", appService.listAdminAccounts(new PatternSearchData<AdminAccount>(new AdminAccount())).getItems());
		return "account/list";
	}
}
