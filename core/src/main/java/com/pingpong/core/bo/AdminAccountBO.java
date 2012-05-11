/**
 * Copyright U-wiss
 */
package com.pingpong.core.bo;

import com.pingpong.domain.AdminAccount;
import net.sf.oval.constraint.NotNull;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 20/03/2012
 */

public interface AdminAccountBO extends BO<Integer, AdminAccount>{
	void unblock(@NotNull Integer id);

	void block(@NotNull Integer id);
}
