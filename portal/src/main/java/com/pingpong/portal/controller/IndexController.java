/**
 * Copyright U-wiss
 */
package com.pingpong.portal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 11/02/2012
 */
@Controller
@RequestMapping("/index")
public class IndexController extends AbstractBaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "index";
	}
}
