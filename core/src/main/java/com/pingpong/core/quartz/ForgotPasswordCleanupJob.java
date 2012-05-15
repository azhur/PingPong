/**
 * Without Copyright
 */
package com.pingpong.core.quartz;

import com.pingpong.core.bo.ForgotPasswordBO;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 26/04/2012
 */

public class ForgotPasswordCleanupJob extends QuartzJobBean {
	private static final Logger LOG = LoggerFactory.getLogger(ForgotPasswordCleanupJob.class);

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		LOG.info("Forgot password table cleaning started...");

		final ForgotPasswordBO forgotPasswordBO = (ForgotPasswordBO)context.getMergedJobDataMap().get("forgotPasswordBO");

		forgotPasswordBO.cleanup();

		LOG.info("Forgot password table cleaning started");
	}
}
