package com.pingpong.portal

import com.pingpong.domain.Photo
import com.pingpong.portal.exception.FileFormatException
import com.pingpong.shared.PhotoImage
import com.pingpong.shared.hibernate.PatternSearchData
import grails.plugins.springsecurity.Secured
import org.apache.commons.io.FilenameUtils
import org.joda.time.LocalDateTime
import org.springframework.web.multipart.commons.CommonsMultipartFile
import com.pingpong.shared.exception.UnknownEntityException

@Secured('isAuthenticated()')
class PhotoController {
	def appService
	def springSecurityService

	static final String[] contentTypes = ['image/jpeg', 'image/jpg', 'image/png']

	def list = {
		def photoAlbumId = params.int('photoAlbumId')

		def pattern = new Photo()
		pattern.photoAlbumId = photoAlbumId

		def photos = appService.listPhotos(new PatternSearchData<Photo>(pattern))

		def album = appService.getPhotoAlbumById(photoAlbumId)

		[photos: photos.items, photoAlbumName: album.name, photoAlbumId: photoAlbumId, coverId: album?.cover?.id]
	}

	def create() {
		def photo = new Photo()
		def photoAlbumId = params.int("photoAlbumId")

		def photoAlbum = appService.getPhotoAlbumById(photoAlbumId)

		photo.setPhotoAlbumId(photoAlbumId)

		[photo: photo, photoAlbumName: photoAlbum.name, photoAlbumId: photoAlbumId]
	}

	def save = {
		def photoAlbumId = params.int("photoAlbumId")

		try {
			def photo = new Photo()

			bindData(photo, params)

			photo.setPhotoAlbumId(photoAlbumId)
			photo.uploaderId = (Integer)((AuthUser)springSecurityService.principal).id

			photo.uploadingDate = LocalDateTime.now()

			def responseImage = (CommonsMultipartFile)params.image

			if(!contentTypes.contains(responseImage.getContentType())) {
				throw new FileFormatException()
			}

			final fileName = UUID.randomUUID().toString()
			final extension = FilenameUtils.getExtension(responseImage.originalFilename)

			photo.url = fileName + "." + extension ;
			photo.contentType = responseImage.contentType

			def photoId = appService.insertPhoto(new PhotoImage(responseImage.bytes, photo))

			flash.message = message(code: 'default.created.message', args: [message(code: 'photo.label'), photoId])
			redirect(action: "list", params: [photoAlbumId: photoAlbumId])

		} catch(FileFormatException e) {
			flash.error = message(code: 'photo.image.format.message')
			redirect action: "create", params: [photoAlbumId: photoAlbumId]
			return
		} catch(any) {
			flash.error = message(code: 'default.hibernate.error.message')
			redirect(action: 'create', params: [photoAlbumId: photoAlbumId])
			return
		}

	}

	def edit = {
		final id = params.int('id')

		def photo = appService.getPhotoById(id)

		if(!photo) {
			flash.error = message(code: 'default.not.found.message', args: [message(code: 'photo.label'), id])
			redirect controller: 'photoAlbum', action: 'list'
			return
		}


		[photo: photo]
	}

	def update = {
		final id = params.int('id')
		def photo = null

		try {
			photo = appService.getPhotoById(id)

			if(!photo) {
				flash.error = message(code: 'default.not.found.message', args: [message(code: 'photo.label'), id])
				redirect controller: 'photoAlbum', action: 'list'
				return
			}

			if(params.version) {
				def version = params.version.toLong()
				if(photo.version > version) {
					flash.error = message(code: 'default.optimistic.locking.failure')
					redirect(action: "edit", id: id)
					return
				}
			}

			bindData(photo, params)

			appService.updatePhoto(photo)

			flash.message = message(code: 'default.updated.message', args: [message(code: 'photo.label'), id])
			redirect(action: "list", params: [photoAlbumId: photo?.photoAlbum?.id])
		} catch(any) {
			flash.error = message(code: 'default.hibernate.error.message')
			render(view: 'edit', model: [photo: photo])
			return
		}
	}

	def delete = {
		def id = params.int("id")
		try {
			appService.deletePhoto(id)

			flash.success = message(code: 'default.deleted.message', args: [message(code: 'photo.label'), params.id])
			redirect(action: list, params: [photoAlbumId: params.photoAlbumId])
		} catch(UnknownEntityException uee) {
			flash.error = message(code: 'default.not.found.message', args: [message(code: 'photo.label'), id])
			redirect controller: 'photoAlbum', action: 'list'
			return
		} catch(any) {
			flash.error = message(code: 'default.not.deleted.message', args: [message(code: 'photo.label'), params.id])
			redirect action: 'list', params: [photoAlbumId: params.photoAlbumId]
			return
		}
	}

	def setCover(){
		def id = params.int("id")

		try{
			appService.setPhotoAsCover(id)
			redirect action: 'list', params: [photoAlbumId: params.photoAlbumId]
		} catch (any) {
			flash.error = message(code: 'server.error')
			redirect action: 'list', params: [photoAlbumId: params.photoAlbumId]
		}
	}

	def image() {
		def id = params.int("id")

		def photo = appService.getOrReturnDefaultPhoto(id)


		response.contentType = photo?.photo?.contentType
		response.contentLength = photo?.image?.length
		response.outputStream.write(photo?.image)
		response.outputStream.flush()
	}
}
