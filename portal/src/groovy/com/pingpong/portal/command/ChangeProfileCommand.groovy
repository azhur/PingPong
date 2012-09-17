package com.pingpong.portal.command

import grails.validation.Validateable
import com.pingpong.domain.enumeration.Gender
import org.joda.time.LocalDate

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 17/09/2012
 */
@Validateable
class ChangeProfileCommand {
	String email
	String name
	Gender gender
	LocalDate birth;

	static constraints = {
		name nullable: false, blank: false
		email blank: false, email: true
		gender nullable: false
		birth nullable: false, max: new LocalDate().minusYears(15), date:true, format:'dd/mm/yyyy'
	}
}
