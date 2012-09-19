/**
 * Copyright U-wiss
 */
package com.pingpong.shared;

import com.pingpong.domain.Photo;

import java.io.Serializable;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 18/09/2012
 */

public class PhotoImage implements Serializable {
	private static final long serialVersionUID = -7776139474380446724L;

	private byte[] image;
	private Photo photo;


	public PhotoImage(byte[] image, Photo photo) {
		this.image = image;
		this.photo = photo;
	}

	public byte[] getImage() {
		return image;
	}

	public Photo getPhoto() {
		return photo;
	}
}
