/**
 * Without Copyright
 */
package com.pingpong.shared.exception;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 14/03/2012
 */

public class NotUniqueEmailException extends RuntimeException{
	private static final long serialVersionUID = 6271518872306281392L;


	public NotUniqueEmailException(String message) {
		super(message);
	}

	public NotUniqueEmailException(String message, Throwable cause) {
		super(message, cause);
	}
}
