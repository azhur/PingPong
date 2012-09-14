package com.pingpong.portal

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class HomeController {

    def index() {
		def config = SpringSecurityUtils.securityConfig

		String postUrl = "${request.contextPath}${config.apf.filterProcessesUrl}"

		render(view: 'index', model: [postUrl: postUrl,
				rememberMeParameter: config.rememberMe.parameter])
	}
}
