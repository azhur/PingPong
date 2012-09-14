package com.pingpong.portal

import com.pingpong.shared.exception.NotUniqueEmailException
import com.pingpong.shared.registration.PlayerRegistrationData
import grails.plugins.springsecurity.Secured
import org.joda.time.LocalDate

@Secured('isAuthenticated()')
class AccountController {
	def appService

	@Secured('isAnonymous()')
	def registration() {
		def command = new PlayerRegistrationCommand()
		command.birth = LocalDate.now()
		[command: command]
	}

	@Secured('isAnonymous()')
	def processRegistration = {PlayerRegistrationCommand command ->
		try {
			if(command.validate()) {
				def data = populateRegistrationData(command)
				appService.register(data)
				flash.success = message(code: 'player.registration.success')
				redirect(controller: 'home', action: 'index')
			} else {
				render(view: 'registration', model: [command: command])
			}
		} catch(NotUniqueEmailException nuee) {
			log.error("Not unique", nuee)
			flash.error = message(code: 'player.registration.email.notUnique')
			render view: 'registration', model: [command: command]
		} catch(any) {
			log.error("Unable to register", any)
			flash.error = message(code: 'player.registration.error')
			render view: 'registration', model: [command: command]
		}
	}

	def PlayerRegistrationData populateRegistrationData(PlayerRegistrationCommand command) {
		def data = new PlayerRegistrationData()

		data.name = command.name
		data.email = command.email
		data.gender = command.gender
		data.password = command.password1
		data.birth = command.birth

		data
	}
}