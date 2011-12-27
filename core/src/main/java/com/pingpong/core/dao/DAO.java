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
 * @since 27/12/2011
 */
@Guarded
public interface DAO<E extends Entity> {
	@NotNull
	Integer insert(@NotNull E entity);

	E getById(@NotNull Integer id);

	void update(@NotNull E entity);

	@NotNull
	List<E> list();

	void deleteById(@NotNull Integer id);

	@NotNull
	HibernateManager getManager() ;
}
