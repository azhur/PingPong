/**
 * Copyright U-wiss
 */
package com.pingpong.domain;

import java.io.Serializable;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 28/12/2011
 */

public abstract class AbstractVersionableEntity<ID extends Serializable> implements Entity<ID>, Versionable{
	private static final long serialVersionUID = 4914583658860172106L;

}
