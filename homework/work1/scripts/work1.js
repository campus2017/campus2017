//返回顶部
var btn = document.getElementById('btn');
btn.onclick = function(){
	document.body.scrollTop = document.documentElement.scrollTop = 0;
}
//时间识别
window.onload = function(){
	var time1 = document.getElementsByClassName('item time1')[0];
	var time2 = document.getElementsByClassName('item time2')[0];
	var time3 = document.getElementsByClassName('item time3')[0];
	var myDate = new Date();
	var firstDay = new Date(myDate.getFullYear(),0,1);
	var dateDiff = myDate - firstDay;
	var msPerDay = 1000 * 60 * 60 * 24;
	var diffDays = Math.ceil(dateDiff / msPerDay);
	if(diffDays < 1){
		//console.log("0");
	}
	else if(1 <= diffDays && diffDays < 32){
		//console.log("1");
		document.getElementById('act-time1').style.visibility = 'visible';
	}
	else if(32 <= diffDays && diffDays < 60){
		//console.log("2");
		time1.style.background = 'url(../image/time-line1-grey.png)';
		document.getElementById('act-time2').style.visibility = 'visible';
	}
	else if(diffDays >= 60){
		//console.log("3");
		time1.style.background = 'url(../image/time-line1-grey.png)';
		time2.style.background = 'url(../image/time-line2-grey.png)';
		document.getElementById('act-time3').style.visibility = 'visible';
	}
}

