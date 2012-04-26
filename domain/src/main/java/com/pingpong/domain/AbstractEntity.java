package com.pingpong.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.sql.Timestamp;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 25/12/2011
 */
@MappedSuperclass
public abstract class AbstractEntity extends AbstractVersionableEntity<Integer> {
	private static final long serialVersionUID = 1836353019026551960L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Version
	@Column(name = "version")
	private Timestamp version;

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public Timestamp getVersion() {
		return version;
	}

	public void setVersion(Timestamp version) {
		this.version = version;
	}
}
