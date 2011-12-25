/**
 * Copyright U-wiss
 */
package com.pingpong.core.hibernate;

import com.pingpong.domain.Entity;
import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import java.io.Serializable;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 25/12/2011
 */

public class HibernateManager extends HibernateTemplate {

	public Integer insertEntity(final Entity entity) throws HibernateException {
		Validate.notNull(entity);

		Serializable id;
		Session session = getSession();
		try {
			checkWriteOperationAllowed(session);

			id = session.save(entity);
		} finally {
			SessionFactoryUtils.releaseSession(session, getSessionFactory());
		}
		return (Integer)id;
	}

	public Entity getEntityById(Class<? extends Entity> entityType, Integer id) throws HibernateException {
		Validate.notNull(entityType);
		Validate.notNull(id);

		Entity entity;
		Session session = getSession();
		try {
			entity = (Entity)session.get(entityType, id);
		} finally {
			SessionFactoryUtils.releaseSession(session, getSessionFactory());
		}

		return entity;
	}

	public void updateEntity(Entity entity) throws HibernateException {
		Validate.notNull(entity);
		Session session = getSession();
		try {
			checkWriteOperationAllowed(session);

			session.merge(entity);
		} finally {
			SessionFactoryUtils.releaseSession(session, getSessionFactory());
		}
	}

	public void deleteEntityById(Class<? extends Entity> entityType, Integer id) throws HibernateException {
		Validate.notNull(id);
		Session session = getSession();
		try {
			checkWriteOperationAllowed(session);

			session.delete(getEntityById(entityType, id));
		} finally {
			SessionFactoryUtils.releaseSession(session, getSessionFactory());
		}
	}
}
