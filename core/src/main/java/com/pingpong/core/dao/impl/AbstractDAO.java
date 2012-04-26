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

import java.io.Serializable;
import java.util.List;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 25/12/2011
 */
@Transactional(propagation = Propagation.MANDATORY)
@Guarded
public abstract class AbstractDAO<ID extends Serializable, E extends Entity<ID>> implements DAO<ID, E> {
	private Class<E> clazz;
	@Autowired
	private HibernateManager manager;

	public AbstractDAO(Class<E> clazz) {
		this.clazz = clazz;
	}

	@Override
	@NotNull
	public ID insert(@NotNull E entity) {
		return (ID)manager.insertEntity(entity);
	}

	protected Session getCurrentSession() {
		return manager.getSessionFactory().getCurrentSession();
	}

	@Override
	@SuppressWarnings("unchecked")
	public E getById(@NotNull ID id) {
		return (E)manager.getEntityById(clazz, id);
	}

	@Override
	public void update(@NotNull E entity) {
		manager.updateEntity(entity);
	}

	@Override
	@NotNull
	public List<E> list() {
		return (List<E>)manager.list(clazz);
	}

	@Override
	public void deleteById(@NotNull ID id) {
		manager.deleteEntityById(clazz, id);
	}

	@Override
	@NotNull
	public HibernateManager getManager() {
		return manager;
	}
}
