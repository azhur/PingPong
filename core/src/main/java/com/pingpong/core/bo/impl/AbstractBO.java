/**
 * Without Copyright
 */
package com.pingpong.core.bo.impl;

import com.pingpong.core.bo.BO;
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
 * @since 20/02/2012
 */
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
@Guarded
public abstract class AbstractBO<ID extends Serializable, E extends Entity<ID>, Dao extends DAO<ID, E>> implements BO<ID, E> {

	private Dao dao;

	@Autowired
	public void setDao(@NotNull final Dao dao) {
		this.dao = dao;
	}

	@Override
	@Transactional(readOnly = false)
	public ID insert(@NotNull E entity) {
		return dao.insert(entity);
	}

	@Override
	public E getById(@NotNull ID id) {
		return dao.getById(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void update(@NotNull E entity) {
		dao.update(entity);
	}

	@Override
	public List<E> list() {
		return dao.list();
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteById(@NotNull ID id) {
		dao.deleteById(id);
	}

	/**
	 * Wrapper for
	 * {@link com.pingpong.core.dao.DAO#getManager()}
	 */
	protected HibernateManager getManager() {
		return dao.getManager();
	}

	/**
	 * Wrapper for
	 * {@link com.pingpong.core.hibernate.HibernateManager#getCurrentSession()}
	 */
	protected Session getCurrentSession() {
		return getManager().getCurrentSession();
	}

	/**
	 * Wrapper for
	 * {@link com.pingpong.core.hibernate.HibernateManager#obtainSession()}
	 */
	protected Session obtainSession() {
		return getManager().obtainSession();
	}

	/**
	 * Wrapper for
	 * {@link com.pingpong.core.hibernate.HibernateManager#releaseSession(org.hibernate.Session)}
	 */
	protected void releaseSession(@NotNull final Session session) {
		getManager().releaseSession(session);
	}
	
	protected void lockEntity(E entity) {
		getManager().lockEntity(entity);
	}

	@NotNull
	protected Dao getDao() {
		return dao;
	}
}
