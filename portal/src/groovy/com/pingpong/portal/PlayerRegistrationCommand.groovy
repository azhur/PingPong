package com.pingpong.portal

import com.pingpong.shared.registration.PlayerRegistrationData
import grails.validation.Validateable
import org.joda.time.LocalDate

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 13/09/2012
 */
@Validateable
class PlayerRegistrationCommand extends PlayerRegistrationData {
	String password1
	String password2

	static constraints = {
		name nullable: false, blank: false
		email blank: false, email: true
		gender nullable: false

		password1 nullable: false, blank: false, minSize: com.pingpong.shared.Constraints.MIN_PASSWORD_LENGTH
		password2 nullable: false, blank: false, validator: { value, command ->
			if(command.password1 != command.password2) {
				return 'command.password2.error.mismatch'
			}
		}

		birth nullable: false, max: new LocalDate().minusYears(15), date:true, format:'dd/mm/yyyy'
	}
}
