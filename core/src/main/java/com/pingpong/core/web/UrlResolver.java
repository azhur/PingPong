/**
 * Copyright U-wiss
 */
package com.pingpong.core.web;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 12/04/2012
 */

public class UrlResolver {
	private String adminUrl;
	private String portalUrl;

	public String getAdminUrl() {
		return adminUrl;
	}

	public String getPortalUrl() {
		return portalUrl;
	}


	public String getPortalResetPasswordUrl(String uid) {
		return String.format("%s/account/reset_password/%s", portalUrl, uid);
	}


	public void setAdminUrl(String adminUrl) {
		this.adminUrl = adminUrl;
	}

	public void setPortalUrl(String portalUrl) {
		this.portalUrl = portalUrl;
	}
}
