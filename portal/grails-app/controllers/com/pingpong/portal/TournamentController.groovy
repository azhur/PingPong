package com.pingpong.portal

import com.pingpong.domain.Tournament
import com.pingpong.portal.extension.TournamentEx
import com.pingpong.shared.exception.RepeatActionException
import com.pingpong.shared.exception.UnknownEntityException
import com.pingpong.shared.hibernate.PatternSearchData
import grails.plugins.springsecurity.Secured

@Secured("ROLE_PLAYER_USER")
class TournamentController {
	def appService
	def springSecurityService

	def participate = {
		try {
			def user = (AuthUser)springSecurityService.principal
			def id = params.id as Integer
			appService.registerInTournament(user.playerId, id)
			flash.success = message(code: 'tournament.participate.success')
			redirect(controller: 'tournament', action: 'view', id: id)
		} catch(RepeatActionException e) {
			log.error(e.message, e)
			flash.error = message(code: 'tournament.participate.repeat')
			redirect(controller: 'tournament', action: 'view', id: id)
		} catch(UnknownEntityException uee) {
			log.error(uee.message, uee)
			flash.error = uee.message
			redirect(controller: 'home', action: 'index')
		} catch(any) {
			log.error("Server error. Try later", any)
			flash.error = message(code: 'server.error')
			redirect(controller: 'tournament', action: 'view', id: id)
		}
	}

	def giveUp = {
		try {
			def user = (AuthUser)springSecurityService.principal
			def id = params.id as Integer
			appService.giveUp(user.playerId, id)
			flash.success = message(code: 'tournament.giveUp.success')
			redirect(controller: 'tournament', action: 'view', id: id)
		} catch(RepeatActionException e) {
			log.error(e.message, e)
			flash.error = message(code: 'tournament.giveUp.repeat')
			redirect(controller: 'tournament', action: 'view', id: id)
		} catch(UnknownEntityException uee) {
			log.error(uee.message, uee)
			flash.error = uee.message
			redirect(controller: 'home', action: 'index')
		} catch(any) {
			log.error("Server error. Try later", any)
			flash.error = message(code: 'server.error')
			redirect(controller: 'tournament', action: 'view', id: id)
		}
	}

	def view = {
	  try {
		  def id = params.id as Integer

		  final Tournament item = loadTournamentById(id)

		  final TournamentEx tournament = convert(item)

		  [tournament: tournament]
	  } catch(UnknownEntityException uee) {
		  log.error(uee.message, uee)
		  flash.error = uee.message
		  redirect(controller: 'home', action: 'index')
	  } catch(any){
		  log.error("Server error. Try later", any)
		  flash.error = message(code: 'server.error')
		  redirect(controller: 'home', action: 'index')
	  }
	}

	def list() {
		try {

			def statusStr = params.status

			def status = Tournament.Status.valueOf(statusStr)

			def title='';

			switch(status) {
				case Tournament.Status.REGISTRATION:
					title = message(code: 'tournament.register.list')
					break
				case Tournament.Status.ACTIVE:
					title = message(code: 'tournament.active.list')
					break
				case Tournament.Status.FINISHED:
					title = message(code: 'tournament.finished.list')
					break
				default:
					throw new RuntimeException('Incorrect status ' + status)
			}

			def tournaments = findTournaments(status)

			[tournaments: tournaments, title: title]
		} catch(any) {
			log.error("Server error. Try later", any)
			flash.error = message(code: 'server.error')
			redirect(controller: 'home', action: 'index')
		}
	}

	private List<TournamentEx> findTournaments(Tournament.Status status) {
		final Tournament pattern = new Tournament()
		pattern.status = status

		final List<Tournament> items = appService.listTournaments(new PatternSearchData<Tournament>(pattern)).getItems()

		final List<TournamentEx> tournaments = new LinkedList<TournamentEx>()

		for(Tournament item : items) {
			tournaments.add(convert(item))
		}

		tournaments
	}

	private TournamentEx convert(Tournament item) {
		final TournamentEx tournamentEx = new TournamentEx()
		tournamentEx.id = item.id
		tournamentEx.participants = item.participants
		def user = (AuthUser)springSecurityService.principal
		tournamentEx.currentPlayerRegistered = appService.isParticipant((int)user.id, item.id)
		tournamentEx.maxParticipantsCount = item.maxParticipantsCount
		tournamentEx.name = item.name
		tournamentEx.status = item.status
		tournamentEx.version = item.version

		tournamentEx
	}

	private Tournament loadTournamentById(Integer id) {
		final Tournament tournament = appService.getTournamentById(id)

		if (tournament == null) {
			throw new UnknownEntityException(Tournament.class, id)
		}

		tournament
	}
}
