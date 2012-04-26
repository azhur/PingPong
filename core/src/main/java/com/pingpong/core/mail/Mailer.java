/**
 * Copyright U-wiss
 */
package com.pingpong.core.mail;

import com.google.common.base.Strings;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.io.File;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 28/03/2012
 */

public class Mailer {
	private static final Logger LOG = LoggerFactory.getLogger(Mailer.class);

	private String subjectPrefix;
	private String defaultFromName;
	private String defaultFromAddress;
	/**
	 * Controls behavior -- will mail be sent or just logging will be performed
	 */
	private boolean enabled;

	@Autowired
	@Qualifier("mailerJmsTemplate")
	private JmsTemplate jmsTemplate;

	@Autowired
	@Qualifier("mailerDestination")
	private Destination destination;

	@Autowired
	private Configuration configuration;

	public static enum EmailTemplate {
		PLAYER_REGISTRATION("player-registration.ftl"),
		FORGOT_PASSWORD("forgot-password.ftl");

		private String value;

		private EmailTemplate(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}


	/**
	 * Sending email to specified address
	 *
	 * @param template		email template which has to be used
	 * @param model		   a data which have to be injected into email template
	 * @param fromAddress	 email address which is used as sender
	 * @param fromName		sender's name
	 * @param toAddress	   email addresses which are TO recipients
	 * @param subject		 subject of the email
	 * @param attachments	 files which have to be attached
	 * @param attachmentNames names for attachments
	 * @param errorsSilent	defines if any exceptions has to be thrown to caller of the method. If true then exception is thrown to caller, false otherise
	 */
	public void sendEmail(EmailTemplate template, Map<String, Object> model, String fromAddress, String fromName,String toAddress, String subject, File[] attachments, String[] attachmentNames, boolean errorsSilent) {
		checkArgument(!Strings.isNullOrEmpty(fromAddress));
		checkArgument(!Strings.isNullOrEmpty(toAddress));
		checkArgument(!Strings.isNullOrEmpty(subject));
		checkState((attachments == null && attachmentNames == null) || (attachments != null && attachmentNames != null && attachments.length == attachmentNames.length));

		if(enabled) {
			try {
				final EmailMessage emailMessage = new EmailMessage();
				emailMessage.setFromAddress(fromAddress);
				emailMessage.setFromName(fromName);
				emailMessage.setToAddress(toAddress);
				emailMessage.setSubject(subject);
				emailMessage.setBody(mergeWithFreemarkerTemplate(template, model));
				emailMessage.setAttachments(attachments);
				emailMessage.setAttachmentNames(attachmentNames);

				jmsTemplate.send(destination, new MessageCreator() {
					@Override
					public Message createMessage(Session session) throws JMSException {
						LOG.info("Sending email message (TO: {}, SUBJECT: {}) to JMS queue", emailMessage.getToAddress(), emailMessage.getSubject());

						return session.createObjectMessage(emailMessage);
					}
				});
			} catch(Exception e) {
				LOG.error(String.format("Error when sending email to '%s'", toAddress), e);

				if(!errorsSilent) {
					throw new RuntimeException(e);
				}
			}
		} else {
			LOG.info("Emulation of email send:\n FROM: {} \n TO: {}\n SUBJECT: {} \n CONTENT: {}", new Object[] {fromAddress, toAddress, subject, template});
		}
	}

	/**
	 * Sending email to specified address
	 *
	 * @param template		email template which has to be used
	 * @param model		   a data which have to be injected into email template
	 * @param toAddress	   email address which is used as sender
	 * @param subject		 subject of the email
	 * @param attachments	 files which have to be attached
	 * @param attachmentNames names for attachments
	 * @param errorsSilent	defines if any exceptions has to be thrown to caller of the method. If true then exception is thrown to caller, false otherise
	 */
	public void sendEmail(EmailTemplate template,Map<String, Object> model, String toAddress, String subject, File[] attachments, String[] attachmentNames, boolean errorsSilent) {
		final String subj = subjectPrefix == null ? subject : subjectPrefix + subject;
		sendEmail(template, model, defaultFromAddress, defaultFromName, toAddress, subj, attachments, attachmentNames, errorsSilent);
	}

	/**
	 * Merges a FreeMarker template with given model
	 *
	 * @param template email template which has to be used
	 * @param model	a data which have to be injected into email template
	 * @return merged text
	 */
	private String mergeWithFreemarkerTemplate(EmailTemplate template, Map<String, Object> model) {
		try {
			final Template freemarkerTemplate = configuration.getTemplate(template.getValue());

			return FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerTemplate, model);
		} catch(Exception e) {
			throw new RuntimeException("Can't apply a Freemarker template", e);
		}
	}

	public void setSubjectPrefix(String subjectPrefix) {
		this.subjectPrefix = subjectPrefix;
	}

	public void setDefaultFromName(String defaultFromName) {
		this.defaultFromName = defaultFromName;
	}

	public void setDefaultFromAddress(String defaultFromAddress) {
		this.defaultFromAddress = defaultFromAddress;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
