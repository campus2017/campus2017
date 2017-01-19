var cw=document.documentElement.clientWidth;
var ch=document.documentElement.clientHeight;
var sh=document.body.scrollHeight||document.documentElement.scrollHeight;
var con=document.getElementById('container');
var pop=document.getElementById('pop');
var oImg=pop.getElementsByTagName('img')[0];

con.onclick=function(){
	Mask();
}
oImg.onclick=function(){
	unMask();
}
oImg.onmouseover=function(){
	oImg.src="images/close_hover.png";
}
oImg.onmouseout=function(){
	oImg.src="images/close.png";	
}
//还需要固定位置 滚动条滚动时候pop固定不动 还有透明度改变
function Mask(){
	con.style.background="#000";
	con.style.opacity="0.30";
	con.style.filter="alpha(opacity=30)";
	pop.style.display="block";
	pop.style.zIndex="500";
	pop.style.top=parseInt(parseInt(ch)-546)/2+"px";
	pop.style.left=parseInt(parseInt(cw)-800)/2+"px";
}

function unMask(){
	con.style.background="#fff";
	con.style.opacity="1";
	con.style.filter="alpha(opacity=100)";
	pop.style.display="none";
}
 