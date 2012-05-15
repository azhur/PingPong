/**
 * Without Copyright
 */
package com.pingpong.admin;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 10/05/2012
 */

public interface ErrorInfoMSG {
	String UNKNOWN = "Unknown error";
	String RESET_PASSWORD_LINK = "Reset link is not valid anymore";
	String NOT_FOUND_ACCOUNT = "Can't find such account";
	String FORGOT_PASSWORD_NOT_SEND_REQUEST = "Couldn't send request about forgot password, try again please";
	String WRONG_OLD_PASSWORD = "Wrong old password";
	String CHANGE_PASSWORD = "Couldn't change password, try again please";
	String LOGIN = "Couldn't find player with specified data, try again please";
	String PLAYER_ACTIVATION = "Can't activate player.";
	String PLAYER_BLOCKING = "Can't block player.";
	String PLAYER_UNBLOCKING = "Can't unblock player.";
	String PLAYER_DELETING = "Can't delete player.";
	String ADMIN_BLOCKING = "Can't block admin.";
	String ADMIN_UNBLOCKING = "Can't unblock admin.";
	String ADMIN_DELETING = "Can't delete admin.";
	String CREATE_ACCOUNT = "Can't create account.";
	String CREATE_TOURNAMENT = "Can't create tournament.";
	String DELETE_TOURNAMENT = "Can't delete tournament.";
	String REGISTER_TOURNAMENT = "Can't register tournament.";
	String ACTIVATE_TOURNAMENT = "Can't activate tournament.";
	String CANCEL_TOURNAMENT = "Can't cancel tournament.";
	String FINISH_TOURNAMENT = "Can't finish tournament.";
}
