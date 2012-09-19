/**
 * Copyright U-wiss
 */
package com.pingpong.core.dao.impl;

import com.pingpong.core.dao.PhotoAlbumDAO;
import com.pingpong.domain.PhotoAlbum;
import net.sf.oval.guard.Guarded;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 17/09/2012
 */
@Guarded
public class PhotoAlbumDAOImpl extends AbstractDAO<Integer, PhotoAlbum> implements PhotoAlbumDAO {
	public PhotoAlbumDAOImpl() {
		super(PhotoAlbum.class);
	}
}
