var init = (function() {
	var $topBtn = document.querySelector('#go-top');
	var bindEvent = function() {
		$topBtn.addEventListener('click', function(e) {
			document.documentElement.scrollTop = document.body.scrollTop = 0;
		});
	}
	return {
		init: bindEvent
	}
})();

init.init();