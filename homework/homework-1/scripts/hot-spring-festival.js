window.onload = function(){
	timeAxisFunc();
} 

/**
 * 时间轴显示效果判断
 */
function timeAxisFunc(){
	var timeAxisIndex = 0; 
	var myDate = new Date();
	var year = myDate.getFullYear();
	var month=myDate.getMonth()+1;
	var date=myDate.getDate();
	if(year < 2014 || year == 2014 && month < 11 || year == 2014 && month == 11 && date < 10){
		timeAxisIndex = 0;
	}else if(year == 2014 && month == 11 && date < 25 && date >= 10){
		timeAxisIndex = 1;
	}else if(year == 2014 && month == 11 && date < 30 && date >= 25){
		timeAxisIndex = 2;
	}else{
		timeAxisIndex = 3;
	}
	var timeAxisLeft = document.getElementById("time-axis-left");
	var timeAxisCenter = document.getElementById("time-axis-center");
    var timeAxisRight = document.getElementById("time-axis-right");
    var timeAxisLeftTriangle = document.getElementById("time-axis-left-triangle-icon");
    var timeAxisCenterTriangle = document.getElementById("time-axis-center-triangle-icon");
    var timeAxisRightTriangle = document.getElementById("time-axis-right-triangle-icon");
	if(timeAxisIndex == 3){
		timeAxisLeft.style.backgroundImage = "url(image/time-axis-left-blue.png)";
		timeAxisCenter.style.backgroundImage = "url(image/time-axis-center-green.png)";
		timeAxisRight.style.backgroundImage = "url(image/time-axis-right-red.png)";
		timeAxisRightTriangle.style.display = "inline";
	}else if(timeAxisIndex == 2){
		timeAxisLeft.style.backgroundImage = "url(image/time-axis-left-blue.png)";
		timeAxisCenter.style.backgroundImage = "url(image/time-axis-center-green.png)";
		timeAxisCenterTriangle.style.display = "inline";
	}else if(timeAxisIndex == 1){
		timeAxisLeft.style.backgroundImage = "url(image/time-axis-left-blue.png)";
		timeAxisLeftTriangle.style.display = "inline";
	}
}
