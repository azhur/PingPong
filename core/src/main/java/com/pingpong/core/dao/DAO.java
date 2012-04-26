package com.pingpong.core.dao;

import com.pingpong.core.hibernate.HibernateManager;
import com.pingpong.domain.Entity;
import net.sf.oval.constraint.NotNull;

import java.io.Serializable;
import java.util.List;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 27/12/2011
 */

public interface DAO<ID extends Serializable, E extends Entity<ID>> {
	@NotNull
	ID insert(@NotNull E entity);

	E getById(@NotNull ID id);

	void update(@NotNull E entity);

	@NotNull
	List<E> list();

	void deleteById(@NotNull ID id);

	@NotNull
	HibernateManager getManager() ;
}
