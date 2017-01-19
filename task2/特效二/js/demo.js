var con=document.getElementById('content');
var oUl=con.getElementsByTagName('ul')[0];
var oLi=oUl.getElementsByTagName('li');
var timer=null;
var late=0;
var now=0; 

/*方案二*/
for(var i=0;i<oLi.length;i++){
	oLi[i].index=i;
	oLi[i].onclick=function(){
		 now =this.index;
		 //处理
		 if(late != now){
		 	open(oLi[late],20,155);
		 	open(oLi[now],20,457);
		 }
		 late=now;
	};
} 

function open(ev,speed,end){
	var begin=parseInt(ev.offsetWidth);
	if(begin > end){
		requestAnimationFrame(function(){
			ev.style.width = parseInt(ev.offsetWidth)-speed+"px";
			//speed=Math.ceil(speed * 0.7);
			if(parseInt(ev.offsetWidth)<=end){
				ev.style.width=end +"px";
			}else {
				requestAnimationFrame(arguments.callee);
			}
		});
	}else {
		requestAnimationFrame(function(){
			ev.style.width = parseInt(ev.offsetWidth)+speed+"px";
			//speed=Math.ceil(speed * 0.7);
			if(parseInt(ev.offsetWidth) >=end){
				ev.style.width=end +"px";
			}else {
				requestAnimationFrame(arguments.callee);
			}
		});
	}
}

