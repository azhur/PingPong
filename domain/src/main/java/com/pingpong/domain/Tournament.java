/**
 * Without Copyright
 */
package com.pingpong.domain;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 12/05/2012
 */
@Entity
@Table
public class Tournament extends AbstractEntity {
	private static final long serialVersionUID = -3347818477622198553L;

	public static enum Status {
		REGISTRATION,
		ACTIVE,
		BLOCKED,
		CLOSED
	}

	@Column
	private String name;
	@Type(type = "org.joda.time.contrib.hibernate.PersistentLocalDate")
	@Column(nullable = false, name = "begin_date")
	private LocalDate beginDate;
	@Type(type = "org.joda.time.contrib.hibernate.PersistentLocalDate")
	@Column(nullable = false, name = "end_date")
	private LocalDate endDate;
	@Enumerated(EnumType.STRING)
	private Status status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(LocalDate beginDate) {
		this.beginDate = beginDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
