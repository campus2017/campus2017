Zepto(function($){
	
	//判断提交按钮是不是该激活
	var button=$("#tijiao_button");
	button.attr("disabled","true");
	/*弹出的遮罩层*/
	var mask=$("#mask");
	var close=$("#dialogclose");
	var flag=0;
	//绑定事件，按钮变可激活
	$('li').each(function(index){
		var circle=$(this).children("span:first-child");
		//alert(index+circle.attr("class"));
		$(this).on("tap",function(e){
		    //提交按钮的改变																																																																																																																																	
			button.css("background-color","#1ba9ba");
		    button.attr("disabled","false");
		    circle.css("color","#4CB8B8");
		    circle.css("font-weight","bold");    		    
	    });
	});

    //给提交按钮绑定事件,防止页面弹出之后
	button.on("tap",function(e){
	    mask.css("display","block");
	    document.body.style.height="100%";
	    document.body.style.overFlow="hidden";
		document.documentElement.style.overflow="hidden";
		$("#dialog").animate({
	        top:30%
	        }, 500,
	        'ease-out');
		flag=1;	
	});
	document.addEventListener("touchmove",function(){
		if(flag==1){
			event.preventDefault();
		}
	});
	close.on("tap",function(){
		mask.css("display","none");
		flag=0;
	});

});
