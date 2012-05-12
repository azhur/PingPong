/**
 * Without Copyright
 */
package com.pingpong.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 11/02/2012
 */
@Controller
@RequestMapping("/index")
public class IndexController extends AbstractBaseController {
	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "index";
	}
}
