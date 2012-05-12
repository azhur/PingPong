/**
 * Without Copyright
 */
package com.pingpong.portal.taglib;

import com.pingpong.portal.security.SpringSecurityUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 11/04/2012
 */

public class PlayerNameTag extends SimpleTagSupport {
	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		final String name = SpringSecurityUtils.getCurrentUser().getName();
		out.print(name);
	}
}
