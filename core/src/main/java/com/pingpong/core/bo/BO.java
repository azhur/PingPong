/**
 * Without Copyright
 */
package com.pingpong.core.bo;

import com.pingpong.domain.Entity;
import com.pingpong.shared.hibernate.ListResult;
import com.pingpong.shared.hibernate.PatternSearchData;
import net.sf.oval.constraint.NotNull;

import java.io.Serializable;
import java.util.List;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 20/02/2012
 */

public interface BO<ID extends Serializable, E extends Entity<ID>> {
	@NotNull
	ID insert(@NotNull E entity);

	E getById(@NotNull ID id);

	void update(@NotNull E entity);

	@NotNull
	List<E> findAll();

	void deleteById(@NotNull ID id);

	@NotNull
	ListResult<E> list(@NotNull PatternSearchData<E> searchData);
}
