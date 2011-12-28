/**
 * Copyright U-wiss
 */
package com.pingpong.core.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 27/12/2011
 */

@Controller
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

	@RequestMapping("hello.jsp")
    public void set(){
		System.out.println("Returning hello view");
		LOGGER.info("Returning hello view");
	}
}
