/**
 * Copyright U-wiss
 */
package com.pingpong.core.dao;

import com.pingpong.domain.Photo;
import net.sf.oval.constraint.NotNull;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 17/09/2012
 */

public interface PhotoDAO extends DAO<Integer, Photo> {
	Photo getRandomFromAlbum(@NotNull Integer albumId);
}
