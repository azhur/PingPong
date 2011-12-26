/**
 * Copyright U-wiss
 */
package com.pingpong.core.hibernate;

import com.pingpong.domain.Entity;
import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 25/12/2011
 */

public class HibernateManager extends HibernateTemplate {
	private static final Logger LOGGER = LoggerFactory.getLogger(HibernateManager.class);

	public Integer insertEntity(final Entity entity) throws HibernateException {
		Validate.notNull(entity);

		Serializable id;
		Session session = getSession();
		try {
			checkWriteOperationAllowed(session);

			id = session.save(entity);

			LOGGER.info("Inserted entity with id = '{}'", id);
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

			LOGGER.info("Got entity by id = '{}'", id);
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

			LOGGER.info("Update entity with id = '{}'", entity.getId());
		} finally {
			SessionFactoryUtils.releaseSession(session, getSessionFactory());
		}
	}

	public void deleteEntityById(Class<? extends Entity> entityType, Integer id) throws HibernateException {
		Validate.notNull(id);
		Validate.notNull(entityType);

		Session session = getSession();
		try {
			checkWriteOperationAllowed(session);

			session.delete(getEntityById(entityType, id));

			LOGGER.info("Deleted entity with id = '{}'", id);
		} finally {
			SessionFactoryUtils.releaseSession(session, getSessionFactory());
		}
	}

	public List<Entity> list(Class<? extends Entity> entityType) {
		Validate.notNull(entityType);
		List<Entity> entities = new ArrayList<Entity>();
		Session session = getSession();
		try {
			entities = session.createCriteria(entityType).list();

			LOGGER.info("List entities for type = '{}'", entityType.getName());
		} finally {
			SessionFactoryUtils.releaseSession(session, getSessionFactory());
		}

		return entities;
	}
}
