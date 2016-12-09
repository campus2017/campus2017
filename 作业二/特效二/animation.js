/**
 * Created by chao on 2016/10/17.
 * 动画
 */
define(['tools/setStyle', 'tools/getLocation'], function (setStyle, getLocation) {
    return function (config,element, propertyObj, time, callBack, callBefore) {
        if (typeof  propertyObj != 'object') {
            return;
        }
        //看jquery
        var beforeValueObj = {};
        var insertObj = {};
        beforeValueObj = getLocation(element,config, false);
        for (var i in propertyObj) {
            var cha = propertyObj[i] - beforeValueObj[i];
            if (cha == 0) {
                delete  beforeValueObj[i];
                delete  beforeValueObj[i];
                continue;
            }
            insertObj[i] = (cha) * 5 / time;
        }
        if (beforeValueObj.width) {
            element.style.width = beforeValueObj.width + 'px';
        }
        if (beforeValueObj.height) {
            element.style.height = beforeValueObj.height + 'px';
        }

        //每10毫秒执行一次
        //动画开始前回调
        if (typeof callBefore == "function") {
            callBefore();
        }
        var timeInterval = setInterval(function () {
            var needClear = false;

            for (var i in insertObj) {
                try {
                    beforeValueObj[i] += insertObj[i];
                    element.style[i] = beforeValueObj[i] + 'px';
                    if (insertObj[i] < 0) {
                        if (beforeValueObj[i] <= propertyObj[i]) {
                            needClear = true;
                            break;
                        }
                    } else {
                        if (beforeValueObj[i] >= propertyObj[i]) {
                            needClear = true;
                            break;
                        }
                    }
                }catch (e){
                    console.log(e.message);
                }
            }
            if(needClear){
                clearInterval(timeInterval);
                for(var i in insertObj){//把计算过程中多加的或者不到目标的，由于小数原因，全部赋值到应有数值
                    element.style[i] = propertyObj[i] + 'px';
                }
                if (typeof callBack == "function") {
                    callBack();
                }
            }
        }, 5);
    }
});
