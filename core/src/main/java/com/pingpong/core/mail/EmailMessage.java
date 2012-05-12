/**
 * Without Copyright
 */
package com.pingpong.core.mail;

import java.io.File;
import java.io.Serializable;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 28/03/2012
 */

public class EmailMessage implements Serializable {
	private static final long serialVersionUID = -1700239678402244860L;

	private String fromAddress;
	private String fromName;
	private String toAddress;
	private String ccAddress;
	private String bccAddress;
	private String subject;
	private String body;
	private File[] attachments;
	private String[] attachmentNames;

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getCcAddress() {
		return ccAddress;
	}

	public void setCcAddress(String ccAddress) {
		this.ccAddress = ccAddress;
	}

	public String getBccAddress() {
		return bccAddress;
	}

	public void setBccAddress(String bccAddress) {
		this.bccAddress = bccAddress;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public File[] getAttachments() {
		return attachments;
	}

	public void setAttachments(File[] attachments) {
		this.attachments = attachments;
	}

	public String[] getAttachmentNames() {
		return attachmentNames;
	}

	public void setAttachmentNames(String[] attachmentNames) {
		this.attachmentNames = attachmentNames;
	}
}
