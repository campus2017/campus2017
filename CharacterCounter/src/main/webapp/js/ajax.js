/**
 * Created by Administrator on 2017/1/7.
 */
var ajax = {
    _validate:function(ajaxData){
        if(typeof ajaxData == 'undefined'
            || typeof  ajaxData['url'] == 'undefined'
            || typeof  ajaxData['datas'] == 'undefined'
            || typeof ajaxData['type'] == 'undefined')
            return false;
        ajaxData['ajaxSuccess']
            =typeof  ajaxData['ajaxSuccess'] == 'undefined'?
            function(){}:ajaxData['ajaxSuccess'];
        ajaxData['ajaxError']
            =typeof  ajaxData['ajaxError'] == 'undefined'?
            function(){}:ajaxData['ajaxError'];
        return true;
    },
    ajaxSend:function (ajaxData) {
        if(!ajax._validate(ajaxData)){
            throw Error("参数错误");
        }
        if(window.XMLHttpRequest){
            AjaxObj =  new XMLHttpRequest();
        }else{
            AjaxObj = new ActiveXObject("XMLHTTP");
        }
        AjaxObj.open(ajaxData['type'],ajaxData['url']+"?new="+new Date().getTime(),true);
        if(typeof  ajaxData['contentType'] != 'undefined') {
            AjaxObj.setRequestHeader("Content-type", ajaxData['contentType']);
        }
        AjaxObj.send(ajaxData['datas']);
        AjaxObj.onreadystatechange = function  () {
            if(AjaxObj.readyState == 4){
                if(AjaxObj.status == 200){
                    console.log('ok');
                    ajaxData['ajaxSuccess'](AjaxObj.responseText);
                }else{
                    ajaxData['ajaxError'](AjaxObj.readyState,AjaxObj.status,AjaxObj.responseText);
                }
            }else{
                ajaxData['ajaxError'](AjaxObj.readyState);
            }
        }
    }
}