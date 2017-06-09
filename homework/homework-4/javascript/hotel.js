window.onload = function() {
	let rooms = Array.from(document.getElementsByClassName(`recomend-rooms-item`));
		rooms.forEach((item,index)=>{
			item.onclick = function(){
				this.classList.toggle(`show`)
			}
		},false)
}