/**
 * Copyright U-wiss
 */
package com.pingpong.portal.validator;

import com.pingpong.portal.command.PlayerRegistrationCommand;
import com.pingpong.shared.AppService;
import org.hibernate.validator.constraints.impl.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 18/02/2012
 */

@Component
public class PlayerRegistrationValidator implements Validator {
	@Autowired
	private EmailValidator emailValidator;
	@Autowired
	private AppService appService;

	@Override
	public boolean supports(Class<?> clazz) {
		return PlayerRegistrationCommand.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		final PlayerRegistrationCommand command = (PlayerRegistrationCommand)target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "playerRegistrationCommand.blank.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "playerRegistrationCommand.blank.gender");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birth", "playerRegistrationCommand.blank.birth");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pass1", "playerRegistrationCommand.blank.password");

		if(!(command.getPass1()).equals(command.getPass2())) {
			errors.rejectValue("pass1", "playerRegistrationCommand.matchingPassword.password");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "playerRegistrationCommand.blank.email");

		if(!emailValidator.isValid(command.getEmail(), null)) {
			errors.rejectValue("email", "playerRegistrationCommand.blank.emailFormat");
		}

		if(!errors.hasErrors()) {
			if(!appService.isEmailUnique(command.getEmail())) {
				errors.rejectValue("email", "playerRegistrationCommand.unique.email");
			}
		}
	}
}
