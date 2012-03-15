/**
 * Copyright U-wiss
 */
package com.pingpong.portal.editor;

import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

import java.beans.PropertyEditorSupport;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 06/03/2012
 */

public class LocalDatePropertyEditorSupport extends PropertyEditorSupport {
	private static final String DATE_FORMAT_PATTERN = "dd/MM/yyyy";

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(StringUtils.isBlank(text)) {
			setValue(null);
		} else {
			setValue(new LocalDate(DateTimeFormat.forPattern(DATE_FORMAT_PATTERN).parseDateTime(text)));
		}
	}

	@Override
	public String getAsText() {
		final LocalDate value = (LocalDate)getValue();
		return value != null ? value.toString(DATE_FORMAT_PATTERN) : "";
	}
}
