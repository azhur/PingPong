/**
 * Copyright U-wiss
 */
package com.pingpong.core.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 27/12/2011
 */

public class HelloController implements Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

		LOGGER.info("Returning hello view");

        return new ModelAndView("hello.jsp");
    }

}
