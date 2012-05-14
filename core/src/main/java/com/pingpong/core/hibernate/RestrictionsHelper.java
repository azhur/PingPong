/**
 * Copyright U-wiss
 */
package com.pingpong.core.hibernate;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 14/05/2012
 */

public class RestrictionsHelper {
	private RestrictionsHelper() {
	}

	public static void eqOpt(final Criteria criteria, String propertyName, Object value) {
		checkNotNull(criteria);
		checkNotNull(propertyName);

		if (value != null) {
			criteria.add(Restrictions.eq(propertyName, value));
		}
	}
}
