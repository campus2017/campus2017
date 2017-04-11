(function(){
	'use strict'
	var UA = window.navigator.userAgent;
	var CLICK = 'click';
	if (/ipad|iphone|android/.test(UA)) {
		CLICK = 'tap';
	}
	var $submit = $('.submit');
	var $inputs = $('input[name=xwd]');
	var $dialog = $('.dialog');
	var $iconClose = $('.icon-close');
	var $scroller = $('#scroller');

	$inputs.forEach(function(value){
		var $this = $(value);
		$this.change(function(){
			$submit.removeClass('disabled');
		});
	});

	$submit[CLICK](function(){
		$scroller.addClass('gray');
		$dialog.addClass('slideInUp');
		$dialog.removeClass('slideOutDown');
	});

	$iconClose[CLICK](function(){
		$scroller.removeClass('gray');
		$dialog.removeClass('slideInUp');
		$dialog.addClass('slideOutDown');
	});
})();