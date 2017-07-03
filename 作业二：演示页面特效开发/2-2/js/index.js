var box = document.getElementById("box");
var ul = box.children[0];
var lis = ul.children;

for(var i=0;i<lis.length;i++){
	lis[i].style.backgroundImage = "url(./img/door"+(i+1)+".png)";
	if(i==0)
  		lis[i].style.width = "475px";
  	else 
  		lis[i].style.width="160px";
  	lis[i].onmouseover = function(){
  		for(var j=0;j<lis.length;j++){
  			animate(lis[j],"width",160);
  		}
  		animate(this,"width",475);
  	}
}

function animate(obj,attr,target){
	clearInterval(obj.timer);
	obj.timer = setInterval(function(){
		var start = parseInt(getStyle(obj,attr));
		var speed = (target-start)/10;
		speed = speed>0?Math.ceil(speed):Math.floor(speed);
		if(start ==target){
			clearInterval(obj.timer);
		}
		else{
			obj.style[attr] = start+speed +"px";
		}
	},15);
}

function getStyle(obj,attr){
	if(typeof window.getComputedStyle!="undefined")
		return window.getComputedStyle(obj,null)[attr];
	else 
		return obj.currentStyle[attr];
}
