var slidedoors=document.getElementById("slide_doors"),
    door=document.getElementsByClassName("door"),
    len=door.length;
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

function move(ele, end, duration){
	var left=ele.style.left;
        left=left.substr(0,left.indexOf("px"));
    var start = parseInt(left);
    var t = 0,
        position;       
    var time = setInterval(function () {
        if (t <= duration){
            t += 20;
            //position = start + (-Math.pow(t/duration, 2) + 2 * t / duration) * (end - start);
            position = start + (t / duration) * (end - start);
            ele.style.left = position + "px";
        } else {
            ele.style.left = end + "px";
            clearInterval(time);
        }
    }, 20);

}

(function(){
    for(var i = 0; i < len; i++){
        if(i == 0){
            door[i].style.left = "0px";
        }else{
            door[i].style.left = 280+(180*i)+"px";
        }
    }
})();

addHandler(slidedoors, 'click', function(e) {
	var e=event||window.event,
	    target=e.target||e.srcElement;
	var doornumber=target.getAttribute("data-doornumber");
	    doornumber=parseInt(doornumber);
    for(var i=0;i<=doornumber;i++){
        (function(i){
	        move(door[i],180 * i, 500);
        })(i);
    }
    for(var i=doornumber+1;i<len;i++){
        //move(door[i],180*i+280,500);
        (function(i){
	        move(door[i],180*i+280,500);
        })(i);
    }
}); 