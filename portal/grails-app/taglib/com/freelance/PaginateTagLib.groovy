package com.freelance

import org.springframework.web.servlet.support.RequestContextUtils

class PaginateTagLib {

	Closure paginateBootstrap = { attrs ->
		def writer = out
		if (attrs.total == null) {
			throwTagError("Tag [paginate] is missing required attribute [total]")
		}

		def messageSource = grailsAttributes.messageSource
		def locale = RequestContextUtils.getLocale(request)

		def total = attrs.int('total') ?: 0
		def action = (attrs.action ?: (params.action ?: ""))
		def offset = params.int('offset') ?: 0
		def max = params.int('max')
		def maxsteps = (attrs.int('maxsteps') ?: 10)

		if (!offset) offset = (attrs.int('offset') ?: 0)
		if (!max) max = (attrs.int('max') ?: 10)

		def linkParams = [:]
		if (attrs.params) linkParams.putAll(attrs.params)
		linkParams.offset = offset - max
		linkParams.max = max
		if (params.sort) linkParams.sort = params.sort
		if (params.order) linkParams.order = params.order

		def linkTagAttrs = [:]
		if(attrs.containsKey('mapping')) {
			linkTagAttrs.mapping = attrs.mapping
		} else {
			linkTagAttrs.action = action
			if (attrs.controller) {
				linkTagAttrs.controller = attrs.controller
			}
		}
		if (attrs.id != null) {
			linkTagAttrs.id = attrs.id
		}
		if (attrs.fragment != null) {
			linkTagAttrs.fragment = attrs.fragment
		}
		linkTagAttrs.params = linkParams

		// determine paging variables
		def steps = maxsteps > 0
		int currentstep = (offset / max) + 1
		int firststep = 1
		int laststep = Math.round(Math.ceil(total / max))

		writer << "<ul>"

		// display previous link when not on firststep unless omitPrev is true
		if (currentstep > firststep && !attrs.boolean('omitPrev')) {
			linkParams.offset = offset - max
			writer << "<li>" + link(linkTagAttrs.clone()) {
				(attrs.prev ?: messageSource.getMessage('paginate.prev', null, messageSource.getMessage('default.paginate.prev', null, 'Previous', locale), locale))
			} + "</li>"
		}

		// display steps when steps are enabled and laststep is not firststep
		if (steps && laststep > firststep) {

			// determine begin and endstep paging variables
			int beginstep = currentstep - Math.round(maxsteps / 2) + (maxsteps % 2)
			int endstep = currentstep + Math.round(maxsteps / 2) - 1

			if (beginstep < firststep) {
				beginstep = firststep
				endstep = maxsteps
			}
			if (endstep > laststep) {
				beginstep = laststep - maxsteps + 1
				if (beginstep < firststep) {
					beginstep = firststep
				}
				endstep = laststep
			}

			// display firststep link when beginstep is not firststep
			if (beginstep > firststep && !attrs.boolean('omitFirst')) {
				linkParams.offset = 0
				writer << "<li>" + link(linkTagAttrs.clone()) {firststep.toString()} + "</li>"
				writer << "<li><a>...</a></li>"
			}

			// display paginate steps
			(beginstep..endstep).each { i ->
				linkParams.offset = (i - 1) * max
				if (currentstep == i) {
					writer << "<li class=\"active\"><a>${i}</a></li>";
				}
				else {
					writer << "<li>" + link(linkTagAttrs.clone()) {i.toString()} + "</li>"
				}
			}

			// display laststep link when endstep is not laststep
			if (endstep < laststep && !attrs.boolean('omitLast')) {
				linkParams.offset = (laststep - 1) * max
				writer << "<li><a>...</a></li>"
				writer <<"<li>" + link(linkTagAttrs.clone()) { laststep.toString() } + "</li>"
			}
		}

		// display next link when not on laststep unless omitNext is true
		if (currentstep < laststep && !attrs.boolean('omitNext')) {
			linkParams.offset = offset + max
			writer << "<li>" + link(linkTagAttrs.clone()) {
				(attrs.next ? attrs.next : messageSource.getMessage('paginate.next', null, messageSource.getMessage('default.paginate.next', null, 'Next', locale), locale))
			} + "</li>"
		}

		writer << "</ul>"
	}
}
