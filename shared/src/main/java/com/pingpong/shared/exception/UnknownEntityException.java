/**
 * Without Copyright
 */
package com.pingpong.shared.exception;

import com.pingpong.domain.Entity;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 11/05/2012
 */

public class UnknownEntityException extends EntityNotFoundException {
	private static final long serialVersionUID = 2393874730035586691L;

	public <ID extends Serializable> UnknownEntityException(final Class<? extends Entity<? extends Serializable>> entityType, ID id) {
		super(getMessage(entityType, id));
	}

	private static <ID extends Serializable> String getMessage(final Class<? extends Entity<? extends Serializable>> entityType, ID id) {
		checkNotNull(entityType);
		checkNotNull(id);

		return String.format("Entity of type {%s} with id = {%s} not found", entityType.getName(), id);
	}
}
