/**
 * Copyright U-wiss
 */
package com.pingpong.core.bo;

import com.pingpong.domain.ForgotPassword;
import net.sf.oval.constraint.NotNull;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 12/04/2012
 */

public interface ForgotPasswordBO extends BO<String, ForgotPassword> {
	@NotNull
	String createForAccount(@NotNull Integer accountId);

	/**
	 * cleanups old forgot password records
	 */
	void cleanup();
}
