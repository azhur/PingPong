/**
 * Copyright U-wiss
 */
package com.pingpong.domain;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 17/09/2012
 */
@Entity
public class Photo extends AbstractEntity {
	private static final long serialVersionUID = 8390196509962302539L;

	private String name;
	private String description;
	private String url;
	@Column(name = "content_type")
	private String contentType;
	@Column(name = "uploading_date")
	@Type(type="com.pingpong.shared.hibernate.PersistentLocalDateTime")
	private LocalDateTime uploadingDate;
	@OneToOne(fetch = FetchType.LAZY)
	private Account uploader;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="photo_album_id")
	private PhotoAlbum photoAlbum;

	@Transient
	private Integer photoAlbumId;
	@Transient
	private Integer uploaderId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public LocalDateTime getUploadingDate() {
		return uploadingDate;
	}

	public void setUploadingDate(LocalDateTime uploadingDate) {
		this.uploadingDate = uploadingDate;
	}

	public Account getUploader() {
		return uploader;
	}

	public void setUploader(Account uploader) {
		this.uploader = uploader;
	}

	public PhotoAlbum getPhotoAlbum() {
		return photoAlbum;
	}

	public void setPhotoAlbum(PhotoAlbum photoAlbum) {
		this.photoAlbum = photoAlbum;
	}

	public Integer getPhotoAlbumId() {
		return photoAlbumId;
	}

	public void setPhotoAlbumId(Integer photoAlbumId) {
		this.photoAlbumId = photoAlbumId;
	}

	public Integer getUploaderId() {
		return uploaderId;
	}

	public void setUploaderId(Integer uploaderId) {
		this.uploaderId = uploaderId;
	}
}
