/**
 * Copyright U-wiss
 */
package com.pingpong.portal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 11/02/2012
 */
@Controller
public class IndexController {
	private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

	@RequestMapping("/index")
	public ModelAndView index() {
		return new ModelAndView("index");
	}
}
