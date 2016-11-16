/**
 * Created by jackwang on 2016/11/14.
 */
function getId(name){
    return document.getElementById(name);
}
function listen(ele, type, handler){
    if(document.addEventListener){
        ele.addEventListener(type, handler, false);
    }else if(document.attachEvent){
        ele.attachEvent('on' + type, handler);
    }else{
        ele['on' + type] = handler;
    }
}
listen(getId('return_top'), 'click', function(){
    var scrollTopStep, animation;
    if(document.body.scrollTop){
        scrollTopStep = document.body.scrollTop / 100;
        animation = setInterval(function(){
            if(document.body.scrollTop <= 0){
                clearInterval(animation);
            }
            document.body.scrollTop -= scrollTopStep;
        }, 0);
    }else{
        scrollTopStep = document.documentElement.scrollTop / 100;
        animation = setInterval(function(){
            if(document.documentElement.scrollTop <= 0){
                clearInterval(animation);
            }
            document.documentElement.scrollTop -= scrollTopStep;
        }, 0);
    }
});
