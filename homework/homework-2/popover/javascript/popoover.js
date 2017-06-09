var getSingleton = (fn)=> {
	var res;
	return function() {
		return res || (res = fn.apply(this,arguments))
	}
}
var createModal = ()=>{
	var modal = document.createElement(`div`),
		pop = document.createElement(`div`),
		closeBtn = document.createElement(`div`);

	modal.className = `mask`
	pop.className = `os-choose-panle`
	closeBtn.className = `close-btn`
 	
 	modal.style.height = `${document.documentElement.scrollHeight}px`
    modal.style.width =  `${document.documentElement.scrollWidth}px`
    modal.style.display = 'none'
	
	modal.appendChild(pop)
	pop.appendChild(closeBtn)
	document.body.appendChild(modal)

	closeBtn.addEventListener(`click`,()=>{
		modal.style.display = 'none'
	},false)	
	return modal;
}

var createSingModal = getSingleton(createModal)

window.onload = ()=>{
	var main = document.getElementById(`main`)
	main.addEventListener(`click`,()=>{
		let modal = createSingModal();
		modal.style.display = `block`
	},false)
}