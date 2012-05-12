/**
 * Without Copyright
 */
package com.pingpong.shared.exception;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 12/04/2012
 */

public class WrongPasswordException extends RuntimeException {
	private static final long serialVersionUID = 4916601264274192659L;

	public WrongPasswordException(String message) {
		super(message);
	}

	public WrongPasswordException(String message, Throwable cause) {
		super(message, cause);
	}
}
