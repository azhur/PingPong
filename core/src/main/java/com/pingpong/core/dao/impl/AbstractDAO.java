/**
 * Copyright U-wiss
 */
package com.pingpong.core.dao.impl;

import com.pingpong.core.dao.DAO;
import com.pingpong.core.hibernate.HibernateManager;
import com.pingpong.domain.Entity;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 25/12/2011
 */
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
@Guarded
public abstract class AbstractDAO<E extends Entity> implements DAO<E> {
	private Class<E> clazz;
	@Autowired
	private HibernateManager manager;

	public AbstractDAO(Class<E> clazz) {
		this.clazz = clazz;
	}

	public Integer insert(@NotNull E entity) {
		return manager.insertEntity(entity);
	}

	protected Session getCurrentSession() {
		return manager.getSessionFactory().getCurrentSession();
	}

	@Override
	@SuppressWarnings("unchecked")
	public E getById(@NotNull Integer id) {
		return (E)manager.getEntityById(clazz, id);
	}
}
