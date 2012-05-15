/**
 * Without Copyright
 */
package com.pingpong.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import java.sql.Timestamp;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 12/04/2012
 */
@Entity
@Table(name = "forgot_password")
public class ForgotPassword extends AbstractVersionableEntity<String> {
	private static final long serialVersionUID = -1819487486996505877L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id", unique = true)
	private String id;
	@Version
	@Column(name = "version")
	private Timestamp version;
	@Column(nullable = false, name = "valid_till")
	@Type(type="com.pingpong.shared.hibernate.PersistentLocalDateTime")
	private LocalDateTime validTill;
	@ManyToOne(fetch = FetchType.LAZY)
	private Account account;

	@Override
	public String getId() {
		return id;
	}

	@Override
	public Timestamp getVersion() {
		return version;
	}

	public LocalDateTime getValidTill() {
		return validTill;
	}

	public void setValidTill(LocalDateTime validTill) {
		this.validTill = validTill;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setVersion(Timestamp version) {
		this.version = version;
	}
}
