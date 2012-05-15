/**
 * Without Copyright
 */
package com.pingpong.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 12/05/2012
 */
@Entity
@Table
public class Tournament extends AbstractEntity {
	private static final long serialVersionUID = -3347818477622198553L;

	public enum Status {
		NEW,
		REGISTRATION,
		ACTIVE,
		FINISHED
	}

	@Column
	private String name;
	@Enumerated(EnumType.STRING)
	private Status status;
	@Column(name = "max_participants_count")
	private Integer maxParticipantsCount;
	@ManyToMany
	@JoinTable(name = "player_tournament",
			joinColumns = {
					@JoinColumn(name = "tournament_id")
			},
			inverseJoinColumns = {
					@JoinColumn(name = "player_id")
			})
	private Set<Player> participants;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Integer getMaxParticipantsCount() {
		return maxParticipantsCount;
	}

	public void setMaxParticipantsCount(Integer maxParticipantsCount) {
		this.maxParticipantsCount = maxParticipantsCount;
	}

	public Set<Player> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<Player> participants) {
		this.participants = participants;
	}
}
