/**
 * Without Copyright
 */
package com.pingpong.admin.validator;

import com.pingpong.admin.command.ResetPasswordCommand;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 18/02/2012
 */

@Component
public class ResetPasswordValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return ResetPasswordCommand.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		final ResetPasswordCommand command = (ResetPasswordCommand)target;

		if(errors.getFieldErrorCount("pass1") == 0 && !(command.getPass1()).equals(command.getPass2())) {
			errors.rejectValue("pass1", "password.matching.error");
		}
	}
}
