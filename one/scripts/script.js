window.onload=function(){
	var start=document.getElementById('start-date'),
	    middle=document.getElementById('middle-date'),
	    end=document.getElementById('end-date'),
	    timeLeft=document.getElementById('time-left'),
	    timeCenter=document.getElementById('time-center'),
	    timeRight=document.getElementById('time-right');
	var nowDate=new Date();
	var startDate=new Date(start.innerHTML.toString().replace(/\./g, "-")),
	    middleDate=new Date(middle.innerHTML.toString().replace(/\./g, "-")),
	    endDate=new Date(end.innerHTML.toString().replace(/\./g, "-"));
	if(nowDate<startDate){
		timeLeft.style.backgroundImage="url(images/time-left-gray.png)";
		timeCenter.style.backgroundImage="url(images/time-center-gray.png)";
		timeRight.style.backgroundImage="url(images/time-right-gray.png)";
	}else if(nowDate>=startDate&&nowDate<middleDate){
		timeLeft.style.backgroundImage="url(images/time-left-selected.png)";
		timeCenter.style.backgroundImage="url(images/time-center-gray.png)";
		timeRight.style.backgroundImage="url(images/time-right-gray.png)";
	}else if(nowDate>=middleDate&&nowDate<endDate){
		timeLeft.style.backgroundImage="url(images/time-left-blue.png)";
		timeCenter.style.backgroundImage="url(images/time-center-selected.png)";
		timeRight.style.backgroundImage="url(images/time-right-gray.png)";
	}else if(nowDate>=endDate){
		timeLeft.style.backgroundImage="url(images/time-left-blue.png)";
		timeCenter.style.backgroundImage="url(images/time-center-green.png)";
		timeRight.style.backgroundImage="url(images/time-right-selected.png)";
	}		
};