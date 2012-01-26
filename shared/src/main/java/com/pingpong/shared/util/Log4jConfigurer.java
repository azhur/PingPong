/**
 * Copyright U-wiss
 */
package com.pingpong.shared.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @see org.springframework.util.Log4jConfigurer
 * @since 1.0
 */
public class Log4jConfigurer implements InitializingBean {
	private String location;
	private long refreshInterval;

	@Override
	public void afterPropertiesSet() throws Exception {
		if(StringUtils.isEmpty(location)) {
			return;
		}

		if(refreshInterval == 0) {
			org.springframework.util.Log4jConfigurer.initLogging(location);
		} else {
			org.springframework.util.Log4jConfigurer.initLogging(location, refreshInterval);
		}
	}

	public void setLocation(final String location) {
		this.location = location;
	}

	public void setRefreshInterval(final long refreshInterval) {
		this.refreshInterval = refreshInterval;
	}
}