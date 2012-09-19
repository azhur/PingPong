/**
 * Copyright U-wiss
 */
package com.pingpong.core.dao.impl;

import com.pingpong.core.dao.PhotoDAO;
import com.pingpong.domain.Photo;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 17/09/2012
 */
@Guarded
public class PhotoDAOImpl extends AbstractDAO<Integer, Photo> implements PhotoDAO {
	public PhotoDAOImpl() {
		super(Photo.class);
	}

	@Override
	public Photo getRandomFromAlbum(@NotNull Integer albumId) {
		return (Photo)getCurrentSession().createQuery("from Photo p where p.photoAlbum.id = :albumId order by id desc")
				.setParameter("albumId", albumId)
				.setMaxResults(1)
				.uniqueResult();
	}
}
