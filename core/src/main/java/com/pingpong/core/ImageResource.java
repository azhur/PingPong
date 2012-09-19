/**
 * Copyright U-wiss
 */
package com.pingpong.core;

import com.pingpong.domain.Photo;
import com.pingpong.shared.PhotoImage;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 18/09/2012
 */

public class ImageResource {
	private static final String DEFAULT_IMAGE_PATH = "com/pingpong/core/img/no_cover.png";

	private String imageDir;

	public byte[] getDefaultImage(){
		try {
			final ClassPathResource resource = new ClassPathResource(DEFAULT_IMAGE_PATH);
			return FileUtils.readFileToByteArray(resource.getFile());
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
	}

	public byte[] getImage(Photo photo){
		try {
			return FileUtils.readFileToByteArray(new File(photo.getUrl()));
		} catch(IOException e) {
			return getDefaultImage();
		}
	}

	public void store(PhotoImage image) {
		try {
			File photoDir = new File(imageDir);
			if(!photoDir.exists()) {
				photoDir.mkdir();
			}

			IOUtils.write(image.getImage(), new FileOutputStream(image.getPhoto().getUrl()));
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void setImageDir(String imageDir) {
		this.imageDir = imageDir;
	}

	public String getImageDir() {
		return imageDir;
	}
}
