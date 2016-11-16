/**
 * Created by jackwang on 2016/11/14.
 */
function listen(ele, type, handler){
    if(document.addEventListener){
        ele.addEventListener(type, handler, false);
    }else if(document.attachEvent){
        ele.attachEvent('on' + type, handler);
    }else{
        ele['on' + type] = handler;
    }
}
var returnButton = document.getElementById('return_top');
listen(returnButton, 'click', function(){
    document.documentElement.scrollTop = document.body.scrollTop = 0;
});
