/**
 * Copyright U-wiss
 */
package com.pingpong.core.dao;

import com.pingpong.domain.Entity;
import net.sf.oval.constraint.NotNull;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 25/12/2011
 */

public interface DAO<E extends Entity> {
	Integer insert(@NotNull E entity);
	E getById(@NotNull Integer id);
}
