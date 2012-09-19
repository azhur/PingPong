/**
 * Copyright U-wiss
 */
package com.pingpong.core.bo.impl;

import com.pingpong.core.bo.AccountBO;
import com.pingpong.core.bo.PhotoAlbumBO;
import com.pingpong.core.bo.PhotoBO;
import com.pingpong.core.dao.PhotoAlbumDAO;
import com.pingpong.domain.Account;
import com.pingpong.domain.Photo;
import com.pingpong.domain.PhotoAlbum;
import com.pingpong.domain.PlayerAccount;
import com.pingpong.shared.hibernate.HibernateUtils;
import com.pingpong.shared.hibernate.ListResult;
import com.pingpong.shared.hibernate.PatternSearchData;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 17/09/2012
 */
@Guarded
public class PhotoAlbumBOImpl extends AbstractBO<Integer, PhotoAlbum, PhotoAlbumDAO> implements PhotoAlbumBO {
	@Autowired
	private AccountBO accountBO;
	@Autowired
	private PhotoBO photoBO;

	@Override
	@Transactional(readOnly = false)
	public Integer insert(@NotNull PhotoAlbum entity) {
		final Account creator = accountBO.getById(entity.getCreatorId());

		entity.setCreator(creator);
		entity.setLastUpdatedBy(creator);

		return super.insert(entity);
	}

	@Override
	@Transactional(readOnly = false)
	public void update(@NotNull PhotoAlbum entity) {

		if(!entity.getLastUpdatedBy().getId().equals(entity.getLastUpdatedById())) {
			final Account updateUsr = accountBO.getById(entity.getLastUpdatedById());
			entity.setLastUpdatedBy(updateUsr);
		}

		super.update(entity);
	}

	@Override
	public PhotoAlbum getById(@NotNull Integer integer) {
		final PhotoAlbum entity = super.getById(integer);

		if(entity != null) {
			entity.setLastUpdatedBy(HibernateUtils.initializeAndUnproxy(entity.getLastUpdatedBy()));
			entity.setCreator(HibernateUtils.initializeAndUnproxy(entity.getCreator()));

			HibernateUtils.initializeAndUnproxy(((PlayerAccount)entity.getCreator()).getPlayer());
			HibernateUtils.initializeAndUnproxy(((PlayerAccount)entity.getLastUpdatedBy()).getPlayer());

			final Photo cover = entity.getCover();

			if(cover != null) {
				HibernateUtils.initializeAndUnproxy(cover);
			}
		}

		return entity;
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteById(@NotNull Integer integer) {
		getDao().loadById(integer, true);

		final Photo pattern = new Photo();
		pattern.setPhotoAlbumId(integer);

		final ListResult<Photo> photos = photoBO.list(new PatternSearchData<Photo>(pattern));

		for(Photo photo : photos.getItems()) {
			photoBO.deleteById(photo.getId());
		}

		super.deleteById(integer);
	}
}
