var createModal = function(){
	var modal = document.createElement(`div`),
		pop = document.createElement(`div`),
		closeBtn = document.createElement(`div`);

	modal.className = `mask`
	pop.className = `panle`
	closeBtn.className = `close-btn`
 	
 	modal.style.height = `${document.documentElement.scrollHeight}px`
    modal.style.width =  `${document.documentElement.scrollWidth}px`
    modal.style.display = 'none'
	
	modal.appendChild(pop)
	pop.appendChild(closeBtn)
	document.body.appendChild(modal)

	closeBtn.addEventListener(`click`,function(){
		modal.style.display = 'none'
	},false)	
	return modal;
}


window.onload = function(){
	var main = document.getElementById(`main`)
	main.addEventListener(`click`,function(){
		var modal = createModal();
		modal.style.display = `block`
	},false)
}