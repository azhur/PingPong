/**
 * Copyright U-wiss
 */
package com.pingpong.domain;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 17/09/2012
 */
@Entity
@Table(name="photo_album")
public class PhotoAlbum extends AbstractEntity {
	private static final long serialVersionUID = 6534206445572665428L;

	private String name;
	private String description;
	@OneToOne(fetch = FetchType.LAZY)
	private Account creator;
	@Column(name="creation_date")
	@Type(type="com.pingpong.shared.hibernate.PersistentLocalDateTime")
	private LocalDateTime creationDate;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="last_updated_by_id")
	private Account lastUpdatedBy;
	@Column(name="last_updated_date")
	@Type(type="com.pingpong.shared.hibernate.PersistentLocalDateTime")
	private LocalDateTime lastUpdatedDate;

	@OneToOne(fetch = FetchType.EAGER)
	private Photo cover;
	@Transient
	private Integer creatorId;
	@Transient
	private Integer lastUpdatedById;

	@Formula("(select count(p.id) from photo p where p.photo_album_id = id)")
	private Integer photoCount;

	public Integer getLastUpdatedById() {
		return lastUpdatedById;
	}

	public void setLastUpdatedById(Integer lastUpdatedById) {
		this.lastUpdatedById = lastUpdatedById;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

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

	public Account getCreator() {
		return creator;
	}

	public void setCreator(Account creator) {
		this.creator = creator;
	}

	public Account getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(Account lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDateTime getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public Photo getCover() {
		return cover;
	}

	public void setCover(Photo cover) {
		this.cover = cover;
	}

	public Integer getPhotoCount() {
		return photoCount;
	}
}
