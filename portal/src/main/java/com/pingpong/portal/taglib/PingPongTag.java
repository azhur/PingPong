/**
 * Copyright U-wiss
 */
package com.pingpong.portal.taglib;

import com.pingpong.portal.security.AuthUser;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 11/04/2012
 */

public class PingPongTag extends SimpleTagSupport {
	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		final String name = ((AuthUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getName();
		out.print(name);
	}
}
