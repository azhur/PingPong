package com.pingpong.core.hibernate;

import com.pingpong.domain.Entity;
import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 25/12/2011
 */

public class HibernateManager extends HibernateTemplate {
	private static final Logger LOGGER = LoggerFactory.getLogger(HibernateManager.class);

	public Serializable insertEntity(final Entity<? extends Serializable> entity) throws HibernateException {
		checkNotNull(entity);

		Serializable id;
		Session session = obtainSession();
		try {
			checkWriteOperationAllowed(session);

			id = session.save(entity);

			LOGGER.info("Inserted entity with id = '{}'", id);
		} finally {
			releaseSession(session);
		}
		return id;
	}

	public Entity<? extends Serializable> getEntityById(Class<? extends Entity<? extends Serializable>> entityType, Serializable id, boolean lock) throws HibernateException {
		checkNotNull(entityType);
		checkNotNull(id);

		Entity<? extends Serializable> entity;
		Session session = obtainSession();
		try {
			entity = (Entity<? extends Serializable>)session.get(entityType, id, lock ? LockOptions.UPGRADE : LockOptions.NONE);

			LOGGER.info("Got entity by id = '{}'", id);
		} finally {
			releaseSession(session);
		}

		return entity;
	}

	public Entity<? extends Serializable> getEntityById(Class<? extends Entity<? extends Serializable>> entityType, Serializable id) throws HibernateException {
		return getEntityById(entityType, id, false);
	}

	public void updateEntity(Entity<? extends Serializable> entity) throws HibernateException {
		checkNotNull(entity);
		Session session = obtainSession();
		try {
			checkWriteOperationAllowed(session);

			session.merge(entity);

			LOGGER.info("Update entity with id = '{}'", entity.getId());
		} finally {
			releaseSession(session);
		}
	}

	public void deleteEntityById(Class<? extends Entity<? extends Serializable>> entityType, Serializable id) throws HibernateException {
		checkNotNull(id);
		checkNotNull(entityType);

		Session session = obtainSession();
		try {
			checkWriteOperationAllowed(session);

			session.delete(getEntityById(entityType, id));

			LOGGER.info("Deleted entity with id = '{}'", id);
		} finally {
			releaseSession(session);
		}
	}

	public List<? extends Entity<? extends Serializable>> list(Class<? extends Entity<? extends Serializable>> entityType) {
		checkNotNull(entityType);
		List<? extends Entity<? extends Serializable>> entities = new ArrayList<Entity<? extends Serializable>>();
		Session session = obtainSession();
		try {
			entities = session.createCriteria(entityType).list();

			LOGGER.info("List entities for type = '{}'", entityType.getName());
		} finally {
			releaseSession(session);
		}

		return entities;
	}

	/**
	 * Obtain the {@link org.hibernate.LockMode#FORCE} lock level upon the given
	 * entity, implicitly checking whether the corresponding database entry
	 * still exists.
	 *
	 * @param entity entity which will be locked
	 * @throws org.springframework.dao.DataAccessException
	 *
	 * @see #lock(Object, org.hibernate.LockMode)
	 * @see #lock(String, Object, org.hibernate.LockMode)
	 * @see org.hibernate.LockMode#UPGRADE
	 */
	public void lockEntity(final Entity<? extends Serializable> entity) throws DataAccessException {
		checkNotNull(entity);

		getCurrentSession().buildLockRequest(LockOptions.UPGRADE).lock(entity);
	}

	/**
	 * Delegates flushing to <code>Hibernate</code> via
	 * {@link org.hibernate.Session#flush()}
	 */
	@Override
	public void flush() {
		LOGGER.debug("Flushing session");

		super.flush();
	}

	/**
	 * Obtains the current session. The definition of what exactly "current"
	 * means controlled by the
	 * {@link org.hibernate.context.CurrentSessionContext} impl configured for
	 * use.
	 * <p/>
	 * In contrast with {@link #obtainSession()} it's not needed to
	 * {@link #releaseSession(org.hibernate.Session)} it
	 *
	 * @return The current session if bound to context.
	 * @throws org.hibernate.HibernateException
	 *          Indicates an issue locating a suitable current session.
	 * @see org.hibernate.SessionFactory#getCurrentSession()
	 */
	public Session getCurrentSession() {
		LOGGER.debug("Getting current session");

		return getSessionFactory().getCurrentSession();
	}

	/**
	 * Return session. Which session will be returned depends on properties
	 * defined in {@link org.springframework.orm.hibernate3.HibernateTemplate}
	 * Take in mind, that for better resource consumptions it's obliged to
	 * invoke {@link #releaseSession(org.hibernate.Session)} after obtaining.
	 *
	 * @return the Session to use (never <code>null</code>)
	 * @see #releaseSession(org.hibernate.Session)
	 * @see #getCurrentSession
	 */
	public Session obtainSession() {
		LOGGER.debug("Obtaining session");

		return getSession();
	}

	/**
	 * Close the given Session, created via the given factory, if it is not
	 * managed externally (i.e. not bound to the thread).
	 *
	 * @param session session to close, which was opened with for instance with
	 *                {@link #obtainSession()}
	 * @see #obtainSession()
	 */
	public void releaseSession(final Session session) {
		Validate.notNull(session);

		LOGGER.debug("Releasing session");

		SessionFactoryUtils.releaseSession(session, getSessionFactory());
	}
}
