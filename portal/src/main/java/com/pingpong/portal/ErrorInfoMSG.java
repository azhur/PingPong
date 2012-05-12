/**
 * Without Copyright
 */
package com.pingpong.portal;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 10/05/2012
 */

public interface ErrorInfoMSG {
	String UNKNOWN = "Unknown error";
	String RESET_PASSWORD_LINK = "Reset link is not valid anymore";
	String NOT_FOUND_ACCOUNT = "Can't find such account";
	String FORGOT_PASSWORD_NOT_SEND_REQUEST = "Couldn't send request about forgot password, try again please";
	String WRONG_OLD_PASSWORD = "Wrong old password";
	String CHANGE_PASSWORD = "Couldn't change password, try again please";
	String CHANGE_PROFILE = "Couldn't change profile, try again please";
	String LOGIN = "Couldn't find player with specified data, try again please";
	String REGISTRATION = "Couldn't register player, try again please";
}
