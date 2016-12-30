/**
 * Created by jackwang on 2016/11/16.
 */
var slideDoor = document.getElementsByClassName('slide_door');
var slideDoorParent = document.getElementById('slide_door_effect');
//动画
var animation = {
    formula: {
        linear: function(t){
            return t;
        },
        easeOutQuick: function(t){
            return Math.pow(t, 2);
        },
        easeInQuick: function(t){
            return -Math.pow(t, 2) + 2 * t;
        },
        easeInOutQuick: function(t){
            if(t <= 0.5){
                return -2 * Math.pow(t, 2) + 2 * t;
            }
            else{
                return 0.5 + 2 * Math.pow(t - 0.5, 2);
            }
        }
    },
    run: function (ele, prop, end, fn, duration) {

        fn = fn || 'linear';
        duration = duration || 400;

        if(!this.formula[fn]){
            throw '动画缓动函数错误';
        }
        if(!window.getComputedStyle(ele, null)[prop]){
            throw '属性未定义';
        }

        var start = parseInt(window.getComputedStyle(ele, null)[prop]),
            formulaFn = this.formula[fn],
            t = 0,
            pos;

        var time = setInterval(function () {
            if (t <= duration){
                t += 15;
                pos = start + formulaFn(t / duration) * (end - start);
                ele.style.left = pos + 'px';
            } else {
                ele.style.left = end + 'px';
                clearInterval(time);
            }
        }, 15);
    }
};

function listen(ele, type, handler){
    if(document.addEventListener){
        ele.addEventListener(type, handler, false);
    }else if(document.attachEvent){
        ele.attachEvent('on' + type, handler);
    }else{
        ele['on' + type] = handler;
    }
}

(function(){
    for(var i = 0, len = slideDoor.length; i < len; i++){
        if(i == 0){
            slideDoor[i].style.left = (160 * i) + 'px';
        }else{
            slideDoor[i].style.left = (160 * i + 315) + 'px';
        }
    }
})();

listen(slideDoorParent, 'click', function(e) {
    var indexCur = parseInt(e.target.getAttribute('data-index')), i;
    for(i = 0; i <= indexCur; i++){
        animation.run(slideDoor[i], 'left', 160 * i, 'easeInQuick', 400);
    }
    for(i = indexCur + 1, len = slideDoor.length; i < len; i++){
        animation.run(slideDoor[i], 'left', 160 * i + 315, 'easeInQuick', 400);
    }
});