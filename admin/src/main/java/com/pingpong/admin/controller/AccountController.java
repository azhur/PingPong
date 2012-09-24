/**
 * Without Copyright
 */
package com.pingpong.admin.controller;

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
import org.springframework.context.MessageSource;
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
import java.util.Locale;
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
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/forgot_password", method = RequestMethod.GET)
	@Secured({"IS_AUTHENTICATED_ANONYMOUSLY"})
	public String showForgotPasswordForm(Map model) {
		model.put("command", new ForgotPasswordCommand());
		return "account/forgotPassword";
	}

	@RequestMapping(value = "/reset_password/{id}", method = RequestMethod.GET)
	@Secured({"IS_AUTHENTICATED_ANONYMOUSLY"})
	public String showResetPasswordForm(@PathVariable("id") String id, Map model, Locale locale) {
		final ResetPasswordCommand command = new ResetPasswordCommand();
		command.setForgotPasswordId(id);
		model.put("command", command);

		try {
			final Account account = appService.getAccountByForgotPasswordId(id);
			model.put("account", account);
		} catch(EntityNotFoundException enfe) {
			LOG.error(messageSource.getMessage("account.password.reset.link.invalid", null, locale), enfe);
			model.put(ERROR_MSG_VAR, messageSource.getMessage("account.password.reset.link.invalid", null, locale));
			return "index";
		} catch(Exception e) {
			final String msg = messageSource.getMessage("error.unknown", null, locale);
			LOG.error(msg, e);
			model.put(ERROR_MSG_VAR, msg);
			return "index";
		}

		return "account/resetPassword";
	}

	@RequestMapping(value = "reset_password/resetPasswordProcess", method = RequestMethod.POST)
	@Secured({"IS_AUTHENTICATED_ANONYMOUSLY"})
	public String resetPasswordProcess(@ModelAttribute("command") @Valid ResetPasswordCommand command, BindingResult result, Model model, Locale locale) {
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
			LOG.error(messageSource.getMessage("account.notFound", null, locale), enfe);
			model.addAttribute(ERROR_MSG_VAR, messageSource.getMessage("account.notFound", null, locale));
			model.addAttribute("account", account);
			return "account/resetPassword";
		} catch(Exception e) {

			LOG.error("error", e);
			model.addAttribute("account", account);
			model.addAttribute(ERROR_MSG_VAR, messageSource.getMessage("account.password.forgot.error", null, locale));
			return "account/resetPassword";
		}

		model.addAttribute(SUCCESS_MSG_VAR, messageSource.getMessage("account.password.reset.success.message", null, locale));
		return "index";
	}

	@RequestMapping(value = "/forgotPasswordProcess", method = RequestMethod.POST)
	@Secured({"IS_AUTHENTICATED_ANONYMOUSLY"})
	public String forgotPasswordProcess(@ModelAttribute("command") @Valid ForgotPasswordCommand command, BindingResult result, Model model, Locale locale) {
		if(result.hasErrors()) {
			return "account/forgotPassword";
		}
		try {
			appService.requestAdminForgotPassword(command.getUsername());
			model.addAttribute(SUCCESS_MSG_VAR, messageSource.getMessage("forgotPassword.success.email.info", null, locale));
		} catch(EntityNotFoundException enfe) {
			LOG.error("error", enfe);
			model.addAttribute(ERROR_MSG_VAR, messageSource.getMessage("account.notFound", null, locale));
			return "account/forgotPassword";
		} catch(Exception e) {
			LOG.error("Error", e);
			model.addAttribute(ERROR_MSG_VAR, messageSource.getMessage("account.password.change.error", null, locale));
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
	public String changePasswordProcess(@ModelAttribute("command") @Valid ChangePasswordCommand command, BindingResult result, Model model, Locale locale) {
		final AuthUser authUser = SpringSecurityUtils.getCurrentUser();

		changePasswordValidator.validate(command, result);

		if(result.hasErrors()) {
			model.addAttribute("command", command);
			return "account/changePassword";
		}

		try {
			appService.changePassword(authUser.getId(), command.getOldPass(), command.getNewPass1());
			model.addAttribute(SUCCESS_MSG_VAR, messageSource.getMessage("account.changePassword.success", null, locale));
		} catch(WrongPasswordException wpe) {
			LOG.error("Error", wpe);
			model.addAttribute(ERROR_MSG_VAR, messageSource.getMessage("account.changePassword.wrongOldPassword", null, locale));
			model.addAttribute("command", new ChangePasswordCommand());
			return "account/changePassword";
		} catch(Exception e) {
			LOG.error("Error", e);
			model.addAttribute("command", new ChangePasswordCommand());
			model.addAttribute(ERROR_MSG_VAR, messageSource.getMessage("", null, locale));
			return "account/changePassword";
		}
		return "index";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN_USER"})
	public String showListForm(Map model, Locale locale) {
		try {
			model.put("admins", appService.listAdminAccounts(new PatternSearchData<AdminAccount>(new AdminAccount())).getItems());
			return "account/list";
		} catch(Exception e) {
			LOG.error("Server error", e);
			model.put(ERROR_MSG_VAR, messageSource.getMessage("server.error", null, locale));
			return "index";
		}
	}

	@RequestMapping(value = "/{id}/block", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN_USER"})
	public String block(@PathVariable("id") Integer id, Map model, Locale locale) {
		try {
			final AdminAccount admin = appService.getAdminAccountById(id);
			appService.blockAdminAccount(id);
			model.put(SUCCESS_MSG_VAR, messageSource.getMessage("account.block.success", new String[] {admin.getEmail()}, locale));
		} catch(UnknownEntityException uee) {
			model.put(ERROR_MSG_VAR, uee.getMessage());
		} catch(Exception e) {
			LOG.error("error", e);
			model.put(ERROR_MSG_VAR, messageSource.getMessage("account.block.error", null, locale));
		}

		try {
			model.put("admins", appService.listAdminAccounts(new PatternSearchData<AdminAccount>(new AdminAccount())).getItems());
		} catch(Exception e) {
			LOG.error("error", e);
			model.put(ERROR_MSG_VAR, messageSource.getMessage("server.error", null, locale));
			return "index";
		}

		return "account/list";
	}

	@RequestMapping(value = "/{id}/unblock", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN_USER"})
	public String unblock(@PathVariable("id") Integer id, Map model, Locale locale) {
		try {
			final AdminAccount admin = appService.getAdminAccountById(id);
			appService.unblockAdminAccount(id);
			model.put(SUCCESS_MSG_VAR, messageSource.getMessage("account.unblock.success", new String[] {admin.getEmail()}, locale));
		} catch(UnknownEntityException uee) {
			model.put(ERROR_MSG_VAR, uee.getMessage());
		} catch(Exception e) {
			LOG.error("Error", e);
			model.put(ERROR_MSG_VAR, messageSource.getMessage("account.unblock.error", null, locale));
		}

		try {
			model.put("admins", appService.listAdminAccounts(new PatternSearchData<AdminAccount>(new AdminAccount())).getItems());
		} catch(Exception e) {
			LOG.error("Server error", e);
			model.put(ERROR_MSG_VAR, messageSource.getMessage("server.error", null, locale));
			return "index";
		}

		return "account/list";
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN_USER"})
	public String delete(@PathVariable("id") Integer id, Map model, Locale locale) {
		try {
			final AuthUser authUser = SpringSecurityUtils.getCurrentUser();

			if(id == authUser.getId()) {
				model.put(ERROR_MSG_VAR, messageSource.getMessage("account.deleting.yourself.error", null, locale));
			} else {
				final AdminAccount admin = appService.getAdminAccountById(id);
				appService.deleteAdminAccount(id);
				model.put(SUCCESS_MSG_VAR, messageSource.getMessage("account.delete.success", new String[] {admin.getEmail()}, locale));
			}
		} catch(UnknownEntityException uee) {
			model.put(ERROR_MSG_VAR, uee.getMessage());
		} catch(Exception e) {
			LOG.error("error", e);
			model.put(ERROR_MSG_VAR, messageSource.getMessage("account.delete.error", null, locale));
		}

		try {
			model.put("admins", appService.listAdminAccounts(new PatternSearchData<AdminAccount>(new AdminAccount())).getItems());
		} catch(Exception e) {
			LOG.error("Server error", e);
			model.put(ERROR_MSG_VAR, messageSource.getMessage("server.error", null, locale));
			return "index";
		}

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
	public String createProcess(@ModelAttribute("command") @Valid NewAccountCommand command, BindingResult result, Model model, Locale locale) {
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
			model.addAttribute(SUCCESS_MSG_VAR, messageSource.getMessage("account.create.success", new String[] {account.getEmail()}, locale));
		} catch(WrongPasswordException wpe) {
			LOG.error("error", wpe);
			model.addAttribute(ERROR_MSG_VAR, messageSource.getMessage("account.create.error", null, locale));
			model.addAttribute("command", new NewAccountCommand());
			return "account/create";
		} catch(Exception e) {
			LOG.error("Error", e);
			model.addAttribute("command", new NewAccountCommand());
			model.addAttribute(ERROR_MSG_VAR, messageSource.getMessage("account.create.error", null, locale));
			return "account/create";
		}
		try {
			model.addAttribute("admins", appService.listAdminAccounts(new PatternSearchData<AdminAccount>(new AdminAccount())).getItems());
		} catch(Exception e) {
			LOG.error("Server error", e);
			model.addAttribute(ERROR_MSG_VAR, messageSource.getMessage("server.error", null, locale));
			return "index";
		}

		return "account/list";
	}
}
