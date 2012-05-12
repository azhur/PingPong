/**
 * Without Copyright
 */
package com.pingpong.admin.validator;

import com.pingpong.admin.command.ChangePasswordCommand;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 18/02/2012
 */

@Component
public class ChangePasswordValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return ChangePasswordCommand.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		final ChangePasswordCommand command = (ChangePasswordCommand)target;

		if(errors.getFieldErrorCount("newPass1") == 0 && !(command.getNewPass1()).equals(command.getNewPass2())) {
			errors.rejectValue("newPass1", "password.matching.error");
		}
	}
}
