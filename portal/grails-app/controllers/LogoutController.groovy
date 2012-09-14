

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import grails.converters.JSON

class LogoutController {

	def springSecurityService

	/**
	 * Index action. Redirects to the Spring security logout uri.
	 */
	def index = {
		// TODO put any pre-logout code here
		redirect uri: SpringSecurityUtils.securityConfig.logout.filterProcessesUrl // '/j_spring_security_logout'
	}

	def after = {
		if(springSecurityService.isAjax(request)) {
			def result = [success: true]

			render result as JSON
		} else {
			redirect uri: '/'
		}
	}
}
