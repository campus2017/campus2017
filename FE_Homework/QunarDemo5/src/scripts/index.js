'use strict';
initList();

function initList() {
	var outer = document.getElementById('subject');
	var list = outer.getElementsByTagName('li');

	for (var i = 0, len = list.length; i < len; i++) {
		bind(list[i], 'mouseover', mouseoverHandler);
	}
}

function bind(el, eventType, callback) {
	if (typeof el.addEventListener === 'function') {
		el.addEventListener(eventType, callback);
	} else if (typeof el.attachEvent === 'function') {
		el.attachEvent('on' + eventType, callback);
	}
}

function mouseoverHandler(e) {
	var target = e.target || e.srcElement;
	var outer = document.getElementById('subject');
	var list = outer.getElementsByTagName('li');

	for (var i = 0, len = list.length; i < len; i++) {
		list[i].className = '';
	}


	while (target.tagName.toLowerCase() != 'li' && target.tagName.toLowerCase() != 'body') {
		target = target.parentNode;
	}

	target.className = 'big';
}