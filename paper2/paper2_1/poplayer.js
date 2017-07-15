window.onload=function(){
	var oBtn=document.getElementById("m_taobao");
 	oBtn.onclick=function(){
  	openNew();
 	}
}

function openNew(){
	//获取页面body！内容！的高度和宽度
 	var sHeight=document.documentElement.scrollHeight;
 	var sWidth=document.documentElement.scrollWidth;
 	//获取可视区域高度，宽度与页面内容的宽度一样
 	var wHeight=document.documentElement.clientHeight;


 	//创建遮罩层div并插入body
 	var oMask=document.createElement("div");
 	oMask.id="mask";
 	oMask.style.height=sHeight+"px";
 	oMask.style.width=sWidth+"px";
 	document.body.appendChild(oMask);

 	//创建弹出层div并插入body
 	var oPop=document.createElement("div");
	oPop.id="pop";
 	var con=document.createElement("div");
 	con.id="popCon";
 	var clo=document.createElement("div");
 	clo.id="close";
 	con.appendChild(clo);
 	oPop.appendChild(con);
 	document.body.appendChild(oPop);

 	//获取oPop的宽度和高度并设置偏移值
 	var dHeight=oPop.offsetHeight;
 	var dWidth=oPop.offsetWidth;
 	oPop.style.left=(sWidth-dWidth)/2+"px";
 	oPop.style.top=(wHeight-dHeight)/2+"px";
 
	//获取关闭按钮
 	var oClose=document.getElementById("close");
 	oClose.onmouseout=function(){
 		oClose.style.backgroundImage="url(images/close.png)";
 	}
 	oClose.onmouseover=function(){
 		oClose.style.backgroundImage="url(images/close_hover.png)";
 	}
 	oClose.onclick=function(){
	  	document.body.removeChild(oMask);
	  	document.body.removeChild(oPop);
 	}
}