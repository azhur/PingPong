/**
 * Without Copyright
 */
package com.pingpong.shared.hibernate;

import java.io.Serializable;
import java.util.List;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 14/05/2012
 */

public class ListResult<E extends Serializable> implements Serializable {
	private static final long serialVersionUID = 1867146344538900864L;

	private int totalItemsCount;
	private List<E> items;

	public ListResult(List<E> items, int totalItemsCount) {
		this.items = items;
		this.totalItemsCount = totalItemsCount;
	}

	public int getTotalItemsCount() {
		return totalItemsCount;
	}

	public List<E> getItems() {
		return items;
	}
}
