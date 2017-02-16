/*绑定事件*/
function addHandler(ele,type,handler){
	if(ele.addEventListener){
		addHandler=function(ele,type,handler){
			ele.addEventListener(type,handler,false);
		};
	}else if(ele.attachEvent){
		addHandler=function(ele,type,handler){
			ele.attachEvent("on"+type,handler);
		};
	}else{
		addHandler=function(ele,type,handler){
			ele["on"+type]=handler;
		};
	}
	addHandler(ele,type,handler);
}

(function init () {
	if(!document.getElementById) return false;
	if(!document.getElementsByTagName) return false;
	var closebutton=document.getElementById("closeButton"),//获取关闭按钮
	    mask=document.getElementById("mask"),
	    as=document.getElementsByTagName("a"),
	    as_len=as.length;
	for(var i=0;i<as_len;i++){
		(function(i){
			addHandler(as[i],"click",function(){
		       mask.style.display="block";
	        });
		})(i);
	}
	addHandler(closebutton,"click",function(){
		mask.style.display="none";
    });


})();