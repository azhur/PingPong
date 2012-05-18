/**
 * Copyright U-wiss
 */
package com.pingpong.admin.command;

import com.pingpong.shared.Constraints;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 14/05/2012
 */

public class TournamentCommand implements Serializable {
	@NotBlank
	private String name;
	@NotNull
	@Min(value = Constraints.MIN_PARTICIPANT_COUNT)
	private Integer max;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}
}
