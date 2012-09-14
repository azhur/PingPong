class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/index"(controller: "login", action: 'auth') {
			constraints {}
		}

		"/"(controller: "login", action: 'auth')
		"500"(view:'/error')
	}
}
