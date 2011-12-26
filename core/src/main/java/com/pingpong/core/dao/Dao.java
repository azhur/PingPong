/**
 * Copyright U-wiss
 */
package com.pingpong.core.dao;

import com.pingpong.core.hibernate.HibernateManager;
import com.pingpong.domain.Entity;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;

import java.util.List;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 25/12/2011
 */

@Guarded
public interface Dao<E extends Entity> {
	Integer insert(@NotNull E entity);

	E getById(@NotNull Integer id);

	void update(@NotNull E entity);

	void deleteById(@NotNull Integer id);

	@NotNull
	List<E> list();

	@NotNull
	HibernateManager getManager();
}
