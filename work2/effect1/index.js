/**
 * Created by Haruko on 2017/2/16.
 */
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

var showBtn = document.getElementById('show-btn');
var modal = document.getElementById('detail-modal');
var closeBtn = document.getElementById('close-btn');

eventUtil.addHandler(showBtn, 'click', function () {
    modal.style.display = 'block';
});
eventUtil.addHandler(closeBtn, 'click', function () {
    modal.style.display = 'none';
});

