/**
 * Without Copyright
 */
package com.pingpong.admin.controller;

import com.pingpong.admin.ErrorInfoMSG;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 01/02/2012
 */
@Controller
public class AuthController extends AbstractBaseController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@Secured({"IS_AUTHENTICATED_ANONYMOUSLY"})
	public String showLoginForm() {
		return "auth/login";
	}

	@RequestMapping(value="/loginFailed", method = RequestMethod.GET)
	@Secured({"IS_AUTHENTICATED_ANONYMOUSLY"})
	public String loginError(ModelMap model) {
		model.addAttribute(ERROR_MSG_VAR, ErrorInfoMSG.LOGIN);
		return "auth/login";
	}
}
