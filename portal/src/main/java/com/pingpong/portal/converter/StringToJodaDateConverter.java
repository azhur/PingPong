/**
 * Copyright U-wiss
 */
package com.pingpong.portal.converter;


import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 12/02/2012
 */

public class StringToJodaDateConverter implements Converter<String, LocalDate> {
	private static final Logger LOGGER = LoggerFactory.getLogger(StringToJodaDateConverter.class);

	private static final DateTimeFormatter FMT = DateTimeFormat.forPattern("dd/mm/yy");

	@Override
	public LocalDate convert(String source) {
		try {
			return FMT.parseDateTime(source).toLocalDate();
		} catch(Exception e) {
			LOGGER.error("Invalid date", e);
			throw new IllegalArgumentException("Invalid date");
		}
	}
}
