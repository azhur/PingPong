/**
 * Copyright U-wiss
 */
package com.pingpong.core.bo;

import com.pingpong.domain.Entity;
import net.sf.oval.constraint.NotNull;

import java.io.Serializable;
import java.util.List;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 20/02/2012
 */

public interface BO<ID extends Serializable, E extends Entity<ID>> {
	@NotNull
	ID insert(@NotNull E entity);

	E getById(@NotNull ID id);

	void update(@NotNull E entity);

	@NotNull
	List<E> list();

	void deleteById(@NotNull ID id);
}
