/**
 * Created by hust on 2016/11/24.
 */

//时间轴设置
var timeline=document.getElementById("timeline");
var triangle=timeline.getElementsByClassName("triangle")[0];
var timing=timeline.getElementsByClassName("timing");
var now=+new Date();
var startTime=+new Date("11/10/2016");
var middleTime=+new Date("11/25/2016");
var endTime=+new Date("11/30/2016");


if (startTime<=now && now<middleTime) {
    triangle.style.left = 135 + "px";
    timing[0].style.backgroundImage = "url(res/blue01.png)";
}else if(middleTime<=now && now<endTime){
    triangle.style.left = 467 + "px";
    timing[0].style.backgroundImage = "url(res/blue01.png)";
    timing[1].style.backgroundImage = "url(res/green02.png)";
}else if(now>=endTime){
    triangle.style.left = 799 + "px";
    timing[0].style.backgroundImage = "url(res/blue01.png)";
    timing[1].style.backgroundImage = "url(res/green02.png)";
    timing[2].style.backgroundImage = "url(res/red03.png)";
}

/*tab滑动条设置*/

var tab=document.getElementsByClassName("tab")[0];
var slider=tab.getElementsByClassName("slider")[0];

tab.onmouseover=function (e) {//使用事件代理
    throttle(relocateSlider,e);//因为鼠标over事件会一直触发，使用函数节流
}
function relocateSlider(e) {
    var dot=67;
    var x=e.pageX-480;
    while(Math.abs(x-dot)>44){
        dot+=87;
    }
    slider.style.left=dot+2+"px";
}
function throttle(method,e) {
    clearTimeout(method.tId);

    method.tId=setTimeout(function () {
        method.call(null,e);
    },150);
}







