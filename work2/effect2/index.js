/**
 * Created by Haruko on 2017/2/16.
 */
window.onload = function(){

    for(var i=0;i<doors.length;i++){
        doors[i].timer = null;
        eventUtil.addHandler(doors[i], 'mouseover', function(){
            slideAnimation(this, 475);
            for(var j=0;j<doors.length;j++){
                if(doors[j]!=this){
                    slideAnimation(doors[j], 159);
                }
            }
        });
    }
};
var doors = document.getElementsByClassName('slide-door');
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

    var vel = e.offsetWidth-1>end ? -5 : 5;

    e.timer = setInterval(function () {
        if(Math.abs(e.offsetWidth-1-end)<=Math.abs(vel)){
            e.style.width = end+'px';
            clearInterval(e.timer);
        }else {
            e.style.width = e.offsetWidth-1+vel+'px';
        }
    },30);
}