package com.pingpong.portal.command

import grails.validation.Validateable

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 17/09/2012
 */
@Validateable
class ForgotPasswordCommand {
	String email

	static constraints = {
		email blank: false, email: true
	}
}
