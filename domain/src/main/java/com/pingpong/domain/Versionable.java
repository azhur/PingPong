/**
 * Copyright U-wiss
 */
package com.pingpong.domain;

import java.sql.Timestamp;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 28/12/2011
 */

public interface Versionable {
	Timestamp getVersion();
}