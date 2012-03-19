/**
 * Copyright U-wiss
 */
package com.pingpong.portal.validator;

import com.pingpong.portal.command.PlayerRegistrationCommand;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 18/02/2012
 */

@Component
public class PlayerRegistrationValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return PlayerRegistrationCommand.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		final PlayerRegistrationCommand command = (PlayerRegistrationCommand)target;

		if(errors.getFieldErrorCount("pass1") == 0 && !(command.getPass1()).equals(command.getPass2())) {
			errors.rejectValue("pass1", "playerRegistrationCommand.matchingPassword.password");
		}
	}
}