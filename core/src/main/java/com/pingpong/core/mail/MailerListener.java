/**
 * Copyright U-wiss
 */
package com.pingpong.core.mail;

import com.pingpong.core.listener.AbstractMessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 28/03/2012
 */

public class MailerListener extends AbstractMessageListener<EmailMessage> {
	private static final Logger LOG = LoggerFactory.getLogger(MailerListener.class);

	@Autowired
	private JavaMailSender mailSender;

	public MailerListener() {
		super(EmailMessage.class);
	}

	@Override
	protected void onMessage(EmailMessage message) throws Exception {
		LOG.debug("Received JMS message with email (TO: {}, SUBJECT: {})", message.getToAddress(), message.getSubject());

		final MimeMessage mimeMessage = mailSender.createMimeMessage();

		final MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
		messageHelper.setFrom(message.getFromAddress(), message.getFromAddress());
		messageHelper.setTo(message.getToAddress().split(" "));
		messageHelper.setSubject(message.getSubject());
		messageHelper.setText(message.getBody(), true);
		//messageHelper.addInline("header", new ClassPathResource("/com/medibench/central/template/img/header.png"), "image/png");
		//messageHelper.addInline("footer", new ClassPathResource("/com/medibench/central/template/img/footer.png"), "image/png");

		if(message.getAttachments() != null) {
			for(int i = 0 ; i != message.getAttachments().length ; i++) {
				messageHelper.addAttachment(message.getAttachmentNames()[i], message.getAttachments()[i]);
			}
		}
		mailSender.send(mimeMessage);
	}
}
