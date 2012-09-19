/**
 * Copyright U-wiss
 */
package com.pingpong.core.bo.impl;

import com.pingpong.core.ImageResource;
import com.pingpong.core.bo.AccountBO;
import com.pingpong.core.bo.PhotoAlbumBO;
import com.pingpong.core.bo.PhotoBO;
import com.pingpong.core.dao.PhotoDAO;
import com.pingpong.core.hibernate.RestrictionsHelper;
import com.pingpong.domain.Account;
import com.pingpong.domain.Photo;
import com.pingpong.domain.PhotoAlbum;
import com.pingpong.domain.PlayerAccount;
import com.pingpong.shared.PhotoImage;
import com.pingpong.shared.hibernate.HibernateUtils;
import com.pingpong.shared.hibernate.ListResult;
import com.pingpong.shared.hibernate.PatternSearchData;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;
import org.apache.commons.io.FileUtils;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 17/09/2012
 */
@Guarded
public class PhotoBOImpl extends AbstractBO<Integer, Photo, PhotoDAO> implements PhotoBO {
	@Autowired
	private ImageResource imageResource;
	@Autowired
	private AccountBO accountBO;
	@Autowired
	private PhotoAlbumBO photoAlbumBO;

	@Override
	public PhotoImage getOrReturnDefaultPhoto(Integer id) {
		final Photo entity = getDao().getById(id);

		if(entity != null) {
			entity.setUploader(HibernateUtils.initializeAndUnproxy(entity.getUploader()));

			HibernateUtils.initializeAndUnproxy(((PlayerAccount)entity.getUploader()).getPlayer());
			return new PhotoImage(imageResource.getImage(entity), entity);
		} else {
			final Photo photo = new Photo();
			photo.setContentType("image/png");

			return new PhotoImage(imageResource.getDefaultImage(), photo);
		}
	}

	@Override
	public ListResult<Photo> list(@NotNull PatternSearchData<Photo> searchData) {
		final Photo pattern = searchData.getPattern();
		final Criteria criteria = createCriteria();

		RestrictionsHelper.eqOpt(criteria, "photoAlbum.id", pattern.getPhotoAlbumId());

		final ListResult<Photo> photoListResult = toList(criteria, searchData);

		for(Photo photo : photoListResult.getItems()) {
			photo.setUploader(HibernateUtils.initializeAndUnproxy(photo.getUploader()));

			HibernateUtils.initializeAndUnproxy(((PlayerAccount)photo.getUploader()).getPlayer());
		}

		return photoListResult;
	}

	@Override
	@Transactional(readOnly = false)
	public Integer insertPhoto(@NotNull PhotoImage image) {
		final Photo entity = image.getPhoto();

		final Account uploader = accountBO.getById(checkNotNull(entity.getUploaderId()));
		final PhotoAlbum photoAlbum = photoAlbumBO.getById(checkNotNull(entity.getPhotoAlbumId()));

		entity.setPhotoAlbum(photoAlbum);
		entity.setUploader(uploader);

		entity.setUrl(imageResource.getImageDir() + "/" + entity.getUrl());

		final Integer photoId = insert(entity);

		if(photoAlbum.getCover() == null) {
			photoAlbum.setCover(entity);
		}

		photoAlbum.setLastUpdatedDate(entity.getUploadingDate());

		if(!photoAlbum.getLastUpdatedBy().getId().equals(entity.getUploaderId())) {
			photoAlbum.setLastUpdatedBy(uploader);
		}

		imageResource.store(image);

		return photoId;
	}

	@Override
	public Photo getById(@NotNull Integer integer) {
		final Photo entity = super.getById(integer);

		if(entity != null) {
			HibernateUtils.initializeAndUnproxy(entity.getPhotoAlbum());
		}

		return entity;
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteById(@NotNull Integer integer) {
		final Photo photo = getDao().getById(integer, true);
		final String url = photo.getUrl();

		final PhotoAlbum photoAlbum = photo.getPhotoAlbum();

		if (photoAlbum.getCover().getId().equals(integer)) {
			photoAlbum.setCover(null);
		}

		super.deleteById(integer);

		FileUtils.deleteQuietly(new File(url));

		photoAlbum.setCover(getDao().getRandomFromAlbum(photoAlbum.getId()));
	}

	@Override
	@Transactional(readOnly = false)
	public void setAsCover(Integer id) {
		final Photo photo = getDao().getById(id);

		final PhotoAlbum photoAlbum = photo.getPhotoAlbum();

		final Photo cover = photoAlbum.getCover();

		if(!cover.getId().equals(id)) {
			photoAlbum.setCover(photo);
		}
	}
}
