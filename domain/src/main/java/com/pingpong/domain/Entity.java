package com.pingpong.domain;

import java.io.Serializable;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 25/12/2011
 */

public interface Entity<ID extends Serializable> extends Serializable{
	ID getId();
}
