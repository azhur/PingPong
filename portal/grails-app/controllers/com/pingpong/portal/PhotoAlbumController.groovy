package com.pingpong.portal

import com.pingpong.domain.PhotoAlbum
import com.pingpong.shared.hibernate.PatternSearchData
import grails.plugins.springsecurity.Secured
import org.apache.commons.lang.StringUtils
import org.hibernate.criterion.Order
import org.joda.time.LocalDateTime
import org.springframework.dao.DataIntegrityViolationException
import com.pingpong.shared.exception.UnknownEntityException

@Secured('isAuthenticated()')
class PhotoAlbumController {

	def appService
	def springSecurityService


	def list = {
		def max = Math.min(params.max ? params.int('max') : 3, 100)
		def offset = params.offset == null ? 0 : params.int('offset')
		def orderBy = StringUtils.isEmpty(params.sort) ? 'id' : params.sort
		def asc = params.order == 'asc'
		params.max = max

		def order = asc ? Order.asc(orderBy) : Order.desc(orderBy)

		def searchData = new PatternSearchData<PhotoAlbum>(new PhotoAlbum(), order, offset, max)

		def albums = appService.listPhotoAlbums(searchData)

		[photoAlbums: albums.items, total: albums.totalItemsCount, max: max]
	}

	def create() {
		[photoAlbum: new PhotoAlbum()]
	}

	def save() {
		def photoAlbum = new PhotoAlbum()
		bindData(photoAlbum, params)

		photoAlbum.creationDate = new LocalDateTime()
		photoAlbum.lastUpdatedDate = photoAlbum.creationDate

		photoAlbum.creatorId = (Integer)((AuthUser)springSecurityService.principal).id
		photoAlbum.lastUpdatedById = photoAlbum.creatorId

		try {
			def albumId = appService.insertPhotoAlbum(photoAlbum)
			flash.message = message(code: 'default.created.message', args: [message(code: 'photoAlbum.label'), albumId])
			redirect(action: "show", id: albumId)
		} catch(any) {
			log.error("Server error. Try later", any)
			flash.error = message(code: 'server.error')
			render(view: "create", model: [photoAlbum: photoAlbum])
		}

	}

	def show() {
		def photoAlbum = appService.getPhotoAlbumById(params.int('id'))

		if(!photoAlbum) {
			flash.error = message(code: 'default.not.found.message', args: [message(code: 'photoAlbum.label'), params.id])
			redirect action: list
			return
		}

		[photoAlbum: photoAlbum]
	}

	def edit() {
		def photoAlbum = appService.getPhotoAlbumById(params.int('id'))

		if(!photoAlbum) {
			flash.error = message(code: 'default.not.found.message', args: [message(code: 'photoAlbum.label'), params.id])
			redirect action: list
			return
		}

		[photoAlbum: photoAlbum]
	}

	def update = {
		final id = params.int('id')

		def photoAlbum = appService.getPhotoAlbumById(id)

		if(!photoAlbum) {
			flash.error = message(code: 'default.not.found.message', args: [message(code: 'photoAlbum.label'), params.id])
			redirect action: list
			return
		}

		if (params.version) {
			def version = params.version.toLong()
			if(photoAlbum.version > version) {
				flash.error = message(code: 'default.optimistic.locking.failure')
				redirect(action: "edit", id: id)
			}
		}

		bindData(photoAlbum, params)

		photoAlbum.lastUpdatedById = (Integer)((AuthUser)springSecurityService.principal).id
		photoAlbum.lastUpdatedDate = new LocalDateTime()

		try {
			appService.updatePhotoAlbum(photoAlbum)
			flash.message = message(code: 'default.updated.message', args: [message(code: 'photoAlbum.label'), id])
			redirect(action: "show", id: id)
		} catch(any) {
			log.error("Server error. Try later", any)
			flash.error = message(code: 'server.error')
			render view: 'edit', model: [photoAlbum: photoAlbum]
		}
	}

	def delete() {
		final id = params.int('id')
		try {
			appService.deletePhotoAlbum(id)
			flash.success = message(code: 'default.deleted.message', args: [message(code: 'photoAlbum.label'), params.id])
			redirect action: "list"
		} catch (DataIntegrityViolationException e) {
			flash.error = message(code: 'default.not.deleted.message', args: [message(code: 'photoAlbum.label'), params.id])
			redirect action: "show", id: params.id
		} catch (UnknownEntityException uee) {
			flash.error = message(code: 'default.not.found.message', args: [message(code: 'photoAlbum.label'), params.id])
			redirect action: list
		} catch(any) {
			log.error("Server error. Try later", any)
			flash.error = message(code: 'server.error')
			render view: 'show', id:id
		}
	}
}
