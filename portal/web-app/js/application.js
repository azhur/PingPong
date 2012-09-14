if (typeof jQuery !== 'undefined') {
	(function($) {
		$('#spinner').ajaxStart(function() {
			$(this).fadeIn();
		}).ajaxStop(function() {
			$(this).fadeOut();
		});
	})(jQuery);


    function resolveLocalePath(locale) {
        var url = window.location.href;

        var indexOf = url.indexOf('lang=');

        if(indexOf != -1) {
            url = url.substring(0, indexOf - 1)
        }

        return (url + (url.indexOf("?") == -1 ? "?lang=" : "&lang=") + locale);
    }
}
