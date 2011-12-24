/**
 * Copyright U-wiss
 */
package com.pingpong.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 24/12/2011
 */
@Entity
@Table(name = "user")
public class Player implements Serializable {
	private static final long serialVersionUID = -3951276983281739052L;

	@Id
	@SequenceGenerator(name = "seqGen", sequenceName = "player_seq")
	@GeneratedValue(generator = "seqGen")
	private Integer id;

	@Version
	private Timestamp version;
}
