/**
 * Copyright U-wiss
 */
package com.pingpong.shared.hibernate;

import org.hibernate.criterion.Order;

import java.io.Serializable;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 14/05/2012
 */

public class SearchData implements Serializable {
	private static final long serialVersionUID = -5248230837491724481L;

	private final Integer offset;
	private final Integer limit;
	private final Order order;

	public SearchData(Order order, Integer offset, Integer limit) {
		if((offset != null && offset < 0) || (limit != null && limit < 0)) {
			throw new IllegalArgumentException();
		}

		this.offset = offset;
		this.limit = limit;
		this.order = order;
	}

	public SearchData(Order order) {
		this(order, null, null);
	}

	public Integer getOffset() {
		return offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public Order getOrder() {
		return order;
	}
}
