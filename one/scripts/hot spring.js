(function init(){
	time_axis_change();
    change();
})();

/*绑定事件*/
function addEventHandler(ele, event, hanlder) {
    if (ele.addEventListener) {
        ele.addEventListener(event, hanlder, false);
    } else if (ele.attachEvent) {
        ele.attachEvent("on"+event, hanlder);
    } else  {
        ele["on" + event] = hanlder;
    }
}
function change(){
    var buttons=document.getElementsByTagName("button");
    var len=buttons.length;
    for(var i=0;i<len;i++){
        addEventHandler(buttons[i],"click",function(){
            //window.location.href="hot spring.html";
            window.open("hot spring.html");
        });
    }

}
function time_axis_change () {//时间轴变化
	var leftImg=document.getElementById("timeAxisLeftImg"),//获取左边的图片
	    centerImg=document.getElementById("timeAxisCenterImg"),//获取中间的图片
	    rightImg=document.getElementById("timeAxisRightImg"),//获取右边的图片
	    leftTriangle=document.getElementById("triangle-left"),//获取左边的三角形
	    centerTriangle=document.getElementById("triangle-center"),//获取中间的三角形
	    rightTriangle=document.getElementById("triangle-right"),//获取右边的三角形
        datenow=new Date;//获取现在的时间对象
    var year=datenow.getFullYear();//现在的年份
    var month=datenow.getMonth()+1;//现在的月份
    month=(month<10?"0"+month:month);
    var date=datenow.getDate();//现在几号
    date=(date<10?"0"+date:date);
    var datenowStr=year.toString()+month.toString()+date.toString();
    if(datenowStr<=20141110){
    	//alert("1");
    	leftImg.style.backgroundImage="url(images/time-axis-left-blue.png)";
    	centerImg.style.backgroundImage="url(images/time-axis-center-grey.png)";
    	rightImg.style.backgroundImage="url(images/time-axis-right-grey.png)";
    	leftTriangle.style.left="140px";
    	leftTriangle.style.top="30px";
    	rightTriangle.style.display="none";
    	centerTriangle.style.display="none";
    }else if(datenowStr>20141110&&datenowStr<=20141125){
    	//alert("2");
    	leftImg.style.backgroundImage="url(images/time-axis-left-blue.png)";
    	centerImg.style.backgroundImage="url(images/time-axis-center-green.png)";
    	rightImg.style.backgroundImage="url(images/time-axis-right-grey.png)";
    	centerTriangle.style.left="140px";
    	centerTriangle.style.top="30px";
    	rightTriangle.style.display="none";
    	leftTriangle.style.display="none";
    }else if(datenowStr>20141125&&datenowStr<=20141130){
    	//alert("3");
    	leftImg.style.backgroundImage="url(images/time-axis-left-blue.png)";
    	centerImg.style.backgroundImage="url(images/time-axis-center-green.png)";
    	rightImg.style.backgroundImage="url(images/time-axis-right-red.png)";
    	rightTriangle.style.left="160px";
    	rightTriangle.style.top="30px";
    	leftTriangle.style.display="none";
    	centerTriangle.style.display="none";
    }else{
    	//alert("4");
    	leftImg.style.backgroundImage="url(images/time-axis-left-blue.png)";
    	centerImg.style.backgroundImage="url(images/time-axis-center-green.png)";
    	rightImg.style.backgroundImage="url(images/time-axis-right-red.png)";
    	rightTriangle.style.left="160px";
    	rightTriangle.style.top="30px";
    	leftTriangle.style.display="none";
    	centerTriangle.style.display="none";
    }

}

