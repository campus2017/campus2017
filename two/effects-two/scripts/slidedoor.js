window.onload=function(){
	var container = document.getElementById('container');
	var imgs = container.getElementsByTagName('img');
	var imgswidth = imgs[0].offsetWidth;
	var exposewidth = 160;
	for(var i=0;i<imgs.length;i++)
    {
     (function(i)
      {
         imgs[i].onmouseover=function()
       {
         for(var j=0;j<=i;j++){
                move(imgs[j],exposewidth*j);
            }
         for(var n=i+1;n<imgs.length;n++){
            move(imgs[n],imgswidth+exposewidth*(n-1));
         }
       }
     })(i)    
    }
};
    function move(ele, targetLeft) {
	var timer = null;
	timer = setInterval(function() {
		var speed = (targetLeft - ele.offsetLeft) / 5;
		if(speed !=0 ){
			speed = speed > 0 ? Math.ceil(speed) : Math.floor(speed); 
			if (Math.abs(targetLeft - ele.offsetLeft) <= speed) { 
				clearInterval(timer); 
				ele.style.left = targetLeft + 'px'; 
			} else {
				ele.style.left = ele.offsetLeft + speed + 'px'; 
			}
		}else{
			clearInterval(timer);
		}
		
	}, 16)
   }