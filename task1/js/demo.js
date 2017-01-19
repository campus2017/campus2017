 var place = document.getElementById('place');
 var oUl=place.getElementsByTagName('ul')[0];
 var oLi=oUl.getElementsByTagName('li');
var now = 6;

 function change(op,classO,classU){
	for(var i=0;i<op.length;i++){
		op[i].index=i;
		if(op[i].className==classO || op[i].className == classU){
			now = i;
		} 
		op[i].onclick=function(){
			now = this.index;
			for(var i=0;i<op.length;i++)
				op[i].className="";
			if(this.index %2) {
				this.className=classU;
			}else {
				this.className=classO;
			}				 
		}
	}	
}

function tab(op,classO,classU){
	document.onkeydown=function(){
		if(window.event.keyCode == 9){
			event.preventDefault();
			for(var i=0;i<op.length;i++)
				op[i].className="";
			if(now == op.length - 1){
				now =0;
			}else{
				now =now+1;
			}
			
			if(now %2) {
				op[now].className=classU;
			}else {
				op[now].className=classO;
			}	
		}
	}
}
change(oLi,"on","ondown");
console.log(now);
/*判断鼠标是否在某一div中*/
place.onclick=function(event){
	var x = event.pageX;
	var y = event.pageY;
	var dx = place.offsetLeft;
	var dy = place.offsetTop;
	var divx = dx + place.offsetWidth;
	var divy = dy + place.offsetHeight;
	 
	if(x<divx && x>dx && y<divy && y>dy){
		tab(oLi,"on","ondown");
	}
}