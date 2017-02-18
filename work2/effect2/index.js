/**
 * Created by Haruko on 2017/2/16.
 */
window.onload = function(){

    for(var i=0;i<doors.length;i++){
        (function (i) {
            doors[i].timer = null;
            eventUtil.addHandler(doors[i], 'mouseover', function(){
                slideAnimation(this, 160*i);
                for(var j=0;j<doors.length;j++){
                    if(j!=i){
                        slideAnimation(doors[j], (j<i ? 160*j : 160*j+315));
                    }
                }
            });
        })(i);
    }
};
var doors = document.getElementsByClassName('door');
var eventUtil = {
    addHandler:function (ele, type, handler) {
        if(ele.addEventListener){
            ele.addEventListener(type, handler, false);
        }else if(ele.attachEvent){
            ele.attachEvent('on'+type, handler);
        }else {
            ele['on'+type] = handler;
        }
    }
};

function slideAnimation(e, end) {
    clearInterval(e.timer);

    var vel = e.offsetLeft>end ? -40 : 40;
    e.timer = setInterval(function () {
        if(Math.abs(e.offsetLeft-end)<=Math.abs(vel)){
            e.style.left = end+'px';
            clearInterval(e.timer);
        }else {
            e.style.left = e.offsetLeft+vel+'px';
        }
    },30);
}