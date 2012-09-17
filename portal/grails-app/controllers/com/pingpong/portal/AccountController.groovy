package com.pingpong.portal

import com.pingpong.portal.command.ForgotPasswordCommand
import com.pingpong.portal.command.PlayerRegistrationCommand
import com.pingpong.portal.command.ResetPasswordCommand
import com.pingpong.shared.exception.NotUniqueEmailException
import com.pingpong.shared.registration.PlayerRegistrationData
import grails.plugins.springsecurity.Secured
import org.joda.time.LocalDate

import javax.persistence.EntityNotFoundException
import com.pingpong.portal.command.ChangePasswordCommand
import com.pingpong.shared.exception.WrongPasswordException
import com.pingpong.portal.command.ChangeProfileCommand

@Secured('isAuthenticated()')
class AccountController {
	def appService
	def springSecurityService

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

	@Secured('isAnonymous()')
	def forgotPassword() {
		[command: new ForgotPasswordCommand()]
	}

	@Secured('isAnonymous()')
	def forgotPasswordProcess(ForgotPasswordCommand command) {
		try {
			if(command.validate()) {
				appService.requestPlayerForgotPassword(command.email)
				flash.success = message(code: 'forgotPassword.success.email.info')
				redirect(controller: 'home', action: 'index')
			} else {
				render(view: 'forgotPassword', model: [command: command])
			}
		} catch(EntityNotFoundException enfe) {
			log.error('Account not found', enfe)
			flash.message = message(code: 'account.email.notFound', args: [command.email])
			render(view: 'forgotPassword', model: [command: command])
		} catch(any) {
			log.error("Unable to reset password", any)
			flash.error = message(code: 'server.error')
			render view: 'forgotPassword', model: [command: command]
		}
	}

	@Secured('isAnonymous()')
	def reset_password(String id) {
		def command = new ResetPasswordCommand()
		command.forgotPasswordId = id
		try {
			def account = appService.getAccountByForgotPasswordId(id)
			command.email = account.email
			render(view: 'resetPassword', model: [command: command])
		} catch(EntityNotFoundException enfe) {
			log.error('Reset link is not valid anymore', enfe)
			flash.error = message(code: 'resetPassword.link.invalid')
			redirect(controller: 'home', action: 'index')
		} catch(any) {
			log.error("Unable to reset password", any)
			flash.error = message(code: 'server.error')
			redirect(controller: 'home', action: 'index')
		}
	}

	@Secured('isAnonymous()')
	def resetPasswordProcess(ResetPasswordCommand command) {
		try {
			if(command.validate()) {
				appService.resetForgottenPassword(command.forgotPasswordId, command.pass1)
				flash.success = message(code: 'resetPassword.success.info')
				redirect(controller: 'home', action: 'index')
			} else {
				render(view: 'resetPassword', model: [command: command])
			}
		} catch(EntityNotFoundException enfe) {
			log.error('Account not found', enfe)
			flash.message = message(code: 'account.email.notFound', args: [command.email])
			render(view: 'resetPassword', model: [command: command])
		} catch(any) {
			log.error("Unable to reset password", any)
			flash.error = message(code: 'server.error')
			render view: 'resetPassword', model: [command: command]
		}
	}

	def changePassword(){
	  [command: new ChangePasswordCommand()]
	}

	def changePasswordProcess(ChangePasswordCommand command) {
		try {
			if (command.validate()) {
				def user = (AuthUser)springSecurityService.principal
				appService.changePassword((int)user.id, command.oldPass, command.pass1)
				flash.success = message(code: 'account.changePassword.success')
				redirect(controller: 'home', action: 'index')
			} else {
				render view: 'changePassword', model: [command: new ChangePasswordCommand()]
			}
		} catch (WrongPasswordException wpe) {
			log.error('Wrong old password', wpe)
			flash.error=message(code: 'account.changePassword.wrongOldPassword')
			render view: 'changePassword', model: [command: new ChangePasswordCommand()]
		} catch (any) {
			log.error("Unable to change password", any)
			flash.error = message(code: 'server.error')
			render view: 'changePassword', model: [command: new ChangePasswordCommand()]
		}
	}

	def changeProfile() {
		try {
			def user = (AuthUser)springSecurityService.principal
			def player = appService.getPlayerAccountByEmail(user.username).player
			def command = new ChangeProfileCommand()
			command.email = user.username
			command.name = player.name
			command.gender = player.gender
			command.birth = player.birth

			[command: command]
		} catch (any) {
			log.error("Unable to show change profile form", any)
			flash.error = message(code: 'server.error')
			redirect(controller: 'home', action: 'index')
		}
	}

	def changeProfileProcess(ChangeProfileCommand command) {
		try{
		 if (command.validate()) {
			 def user = (AuthUser)springSecurityService.principal
			 def player = appService.getPlayerAccountByEmail(user.username).player

			 player.name = command.name
			 player.gender = command.gender
			 player.birth = command.birth

			 appService.updatePlayer(player)
			 flash.success = message(code: 'account.changeProfile.success')
			 redirect(controller: 'home', action: 'index')
		 } else {
			 render view: 'changePassword', model: [command: command]
		 }
		} catch(any) {
			log.error("Unable to change profile", any)
			flash.error = message(code: 'server.error')
			render view: 'changeProfile', model: [command: command]
		}
	}

	private PlayerRegistrationData populateRegistrationData(PlayerRegistrationCommand command) {
		def data = new PlayerRegistrationData()

		data.name = command.name
		data.email = command.email
		data.gender = command.gender
		data.password = command.password1
		data.birth = command.birth

		data
	}
}