package com.pingpong.portal.command

import grails.validation.Validateable

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 17/09/2012
 */
@Validateable
class ChangePasswordCommand {
	String oldPass
	String pass1
	String pass2

	static constraints = {
		oldPass nullable: false, blank: false
		pass1 nullable: false, blank: false, minSize: com.pingpong.shared.Constraints.MIN_PASSWORD_LENGTH
		pass2 nullable: false, blank: false, validator: { value, command ->
			if(command.pass1 != command.pass2) {
				return 'command.password2.error.mismatch'
			}
		}
	}
}
