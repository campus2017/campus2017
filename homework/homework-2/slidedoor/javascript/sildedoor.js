;(function(w,d,undefined){
	function Slidedoor(container,config) {
		this.container = container
		this.config = config
		this.children = Array.from(container.children)||[]
	}
	Slidedoor.prototype.init = function(){
		var self = this
		for (let i = self.children.length - 1; i >= 0; i--) {
			self.children[i].style.width = self.config.width+`px`
			self.children[i].addEventListener (`mouseover`,()=>{
				self.toggle(self.config.width,self.config.activeDoor)
				self.toggle(self.config.toggleWidth+self.config.width,i)
				self.config.activeDoor = i
			})
		}
	}
	Slidedoor.prototype.toggle = function(target,index){
		var self = this,
			el = self.children[index]
		if(self.activeDoor == index) {
			return  false
		}else{
			if(el.move){
				clearTimeout(el.move)
			}
			var speed = (target - el.offsetWidth)/10
			speed = speed>0? Math.ceil(speed) : Math.floor(speed)
			if(el.offsetWidth == target){
				clearTimeout(el.move)
			}
			else{
				el.style.width = parseInt(el.style.width)+ speed +`px`
			}
			el.move = setTimeout(function() {
				self.toggle(target,index)
			}, 1000/60);
		}
	}
	w[`Slidedoor`] = Slidedoor
})(window,document);
var config = {
	width: 260,
	toggleWidth: 205,
}
var slide = document.getElementById(`slidedoor`)
var slidedoor = new Slidedoor(slide,config);
slidedoor.init()