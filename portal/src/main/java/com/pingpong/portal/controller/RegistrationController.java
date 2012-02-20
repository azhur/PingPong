/**
 * Copyright U-wiss
 */
package com.pingpong.portal.controller;

import com.pingpong.domain.enumeration.Gender;
import com.pingpong.portal.command.PlayerRegistrationCommand;
import com.pingpong.portal.validator.PlayerRegistrationValidator;
import com.pingpong.shared.AppService;
import com.pingpong.shared.registration.PlayerRegistrationData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 31/01/2012
 */
@Controller
@RequestMapping("/registration")
public class RegistrationController {
	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

	@Autowired
	private AppService appService;
	@Autowired
	private PlayerRegistrationValidator validator;


	@RequestMapping(method = RequestMethod.GET)
	public String showRegistrationForm(Map model) {
		model.put("registration", new PlayerRegistrationCommand());
		model.put("genders", Gender.values());

		return "registration/registration";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processRegistration(@ModelAttribute("registration") @Valid PlayerRegistrationCommand command, BindingResult result, Map model) {
		validator.validate(command, result);

		if(result.hasErrors()) {
			model.put("genders", Gender.values());
			return "registration/registration";
		}

		try {
			appService.register(populateData(command));
		} catch(Exception e) {
			LOGGER.error("ERROR", e);
			throw new RuntimeException(e);
		}

		return "registration/success";
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
