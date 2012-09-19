/**
 * Copyright U-wiss
 */
package com.pingpong.core.bo;

import com.pingpong.domain.Photo;
import com.pingpong.shared.PhotoImage;
import net.sf.oval.constraint.NotNull;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 17/09/2012
 */

public interface PhotoBO extends BO<Integer, Photo> {
	@NotNull
	PhotoImage getOrReturnDefaultPhoto(Integer id);

	@NotNull
	Integer insertPhoto(@NotNull PhotoImage image);

	void setAsCover(@NotNull Integer id);
}
