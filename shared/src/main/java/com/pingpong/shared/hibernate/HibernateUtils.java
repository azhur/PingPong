/**
 * Without Copyright
 */
package com.pingpong.shared.hibernate;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 20/03/2012
 */

public class HibernateUtils {
	private HibernateUtils() {
	}

	public static <T> T initializeAndUnproxy(T entity) {
		checkNotNull(entity);

		Hibernate.initialize(entity);

		if(entity instanceof HibernateProxy) {
			entity = (T)((HibernateProxy)entity).getHibernateLazyInitializer().getImplementation();
		}

		return entity;
	}
}
