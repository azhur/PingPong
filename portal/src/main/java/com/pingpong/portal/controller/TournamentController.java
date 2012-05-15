/**
 * Without Copyright
 */
package com.pingpong.portal.controller;

import com.pingpong.domain.Tournament;
import com.pingpong.portal.ErrorInfoMSG;
import com.pingpong.shared.AppService;
import com.pingpong.shared.hibernate.PatternSearchData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * @author Artur Zhurat
 * @version 1.0
 * @since 15/05/2012
 */
@Controller
public class TournamentController extends AbstractBaseController {
	private static final Logger LOG = LoggerFactory.getLogger(TournamentController.class);

	@Autowired
	private AppService appService;

	@RequestMapping(value = "/tournaments/registration", method = RequestMethod.GET)
	@Secured({"ROLE_PLAYER_USER"})
	public String showRegistrationTournaments(Map model) {
		try {
			model.put("tournaments", findTournaments(Tournament.Status.REGISTRATION));
			return "tournament/list";
		} catch(Exception e) {
			LOG.error(ErrorInfoMSG.UNKNOWN, e);
			model.put(ERROR_MSG_VAR, ErrorInfoMSG.UNKNOWN);
		}

		return "index";
	}

	private List<Tournament> findTournaments(Tournament.Status status) {
		final Tournament pattern = new Tournament();
		pattern.setStatus(status);

		return appService.listTournaments(new PatternSearchData<Tournament>(pattern)).getItems();
	}
}
