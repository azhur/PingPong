/**
 * Copyright U-wiss
 */
package com.pingpong.domain;

import java.sql.Timestamp;

/**
 * @author Artur Zhurat
 * @version 3.0
 * @since 25/12/2011
 */

public interface Entity {
	Integer getId();
	Timestamp getVersion();
}
