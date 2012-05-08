/**
 * Copyright U-wiss
 */
package com.pingpong.portal.controller;

import com.pingpong.domain.enumeration.Gender;
import com.pingpong.portal.command.PlayerRegistrationCommand;
import com.pingpong.portal.editor.LocalDatePropertyEditorSupport;
import com.pingpong.portal.validator.PlayerRegistrationValidator;
import com.pingpong.shared.AppService;
import com.pingpong.shared.exception.NotUniqueEmailException;
import com.pingpong.shared.registration.PlayerRegistrationData;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 31/01/2012
 */
@Controller
@RequestMapping("/registration")
public class PlayerRegistrationController extends AbstractBaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(PlayerRegistrationController.class);

	@Autowired
	private AppService appService;
	@Autowired
	private PlayerRegistrationValidator validator;


	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(LocalDate.class, new LocalDatePropertyEditorSupport());
	}

	@RequestMapping(method = RequestMethod.GET)
	@Secured({"IS_AUTHENTICATED_ANONYMOUSLY"})
	public String showRegistrationForm(Map model) {
		model.put("registration", new PlayerRegistrationCommand());
		return "registration/registration";
	}

	@RequestMapping(method = RequestMethod.POST)
	@Secured({"IS_AUTHENTICATED_ANONYMOUSLY"})
	public String processRegistration(@ModelAttribute("registration") @Valid PlayerRegistrationCommand command, BindingResult result, Model model) {
		validator.validate(command, result);

		if(result.hasErrors()) {
			return "registration/registration";
		}

		try {
			appService.register(populateData(command));
		} catch(NotUniqueEmailException emailException){
			LOGGER.error("Not unique", emailException);
			model.addAttribute(ERROR_MSG_VAR, emailException.getMessage());
			return "registration/registration";
		} catch(Exception e) {
			LOGGER.error("ERROR", e);
			model.addAttribute(ERROR_MSG_VAR, "Couldn't register player, try again please");
			return "registration/registration";
		}

		return "registration/success";
	}

	@ModelAttribute("genderItems")
	public Gender[] populateGenderItems() {
	    return Gender.values();
	}

	private PlayerRegistrationData populateData(PlayerRegistrationCommand command) {
		final PlayerRegistrationData data = new PlayerRegistrationData();
		data.setName(command.getName());
		data.setEmail(command.getEmail());
		data.setGender(command.getGender());
		data.setPassword(command.getPass1());
		data.setBirth(command.getBirth());
		return data;
	}
}
