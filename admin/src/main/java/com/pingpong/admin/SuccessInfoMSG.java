/**
 * Copyright U-wiss
 */
package com.pingpong.admin;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 10/05/2012
 */

public interface SuccessInfoMSG {
	String RESET_PASSWORD = "Your password was reset successfully.";
	String CHANGE_PASSWORD = "Password was changed successfully.";
	String CHANGE_PROFILE = "Profile was changed successfully.";
	String REGISTRATION = "Thank you for registration... We will contact with you soon!";
	String FORGOT_PASSWORD = "On your email was sent email with link for password reset. If it does not arrive, contact your administrator.";
	String PLAYER_ACTIVATION = "Player '%s' was successfully activated.";
	String PLAYER_BLOCKING = "Player '%s' was successfully blocked.";
	String PLAYER_UNBLOCKING = "Player '%s' was successfully unblocked.";
	String PLAYER_DELETING = "Player '%s' was successfully deleted.";
	String ADMIN_BLOCKING = "Admin '%s' was successfully blocked.";
	String ADMIN_UNBLOCKING = "Admin '%s' was successfully unblocked.";
	String ADMIN_DELETING = "Admin '%s' was successfully deleted.";
}
