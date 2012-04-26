/**
 * Copyright U-wiss
 */
package com.pingpong.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 12/04/2012
 */
@Controller
@RequestMapping("/error")
public class ErrorController extends AbstractBaseController {
	@RequestMapping(value = "/deny", method = RequestMethod.GET)
	public String deny() {
		return "error/deny";
	}
}
