/**
 * Copyright U-wiss
 */
package com.pingpong.shared.hibernate;

import com.pingpong.domain.Entity;
import org.hibernate.criterion.Order;

import java.io.Serializable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 14/05/2012
 */

public class PatternSearchData<T extends Entity<? extends Serializable>> extends SearchData {
	private static final long serialVersionUID = 3860794300264642581L;
	private T pattern;

	public PatternSearchData(T pattern, Order order, Integer offset, Integer limit) {
		super(order, offset, limit);
		this.pattern = checkNotNull(pattern);
	}

	public PatternSearchData(T pattern, Order order) {
		this(pattern, order, null, null);
	}

	public PatternSearchData(T pattern) {
		this(pattern, null, null, null);
	}

	public T getPattern() {
		return pattern;
	}
}
