/**
 * Created by jackwang on 2016/11/18.
 */

ajax({
    url: 'data.json',
    success: function(data){
        //成功
        data = JSON.parse(data);
        var str = "";
        for(var i = 0, len = data.length; i < len; i++){
            str += joinStr(data[i]);
        }

        var temp  = document.createElement('tbody');
        temp.innerHTML = str;
        var children = Array.prototype.slice.call(temp.childNodes);
        var dataList = getIdEle('data_list');
        for(var j = 0, childLen = children.length; j < childLen; j ++){
            dataList.appendChild(children[j]);
        }
    },
    fail: function(errCode){
        //失败
        alert(errCode);
    }
});

/**
 * 异步ajax
 * @param obj {Object}
 *      url: 请求地址 必须传入
 *      type：请求方式，默认：GET
 *      data：json格式传递数据，默认为{}
 *      success: 成功回调函数
 *      fail：失败回调函数
 */
function ajax(obj){

    if(Object.prototype.toString.call(obj) != '[object Object]'){
        throw(new TypeError("不是对象类型"));
    }

    if(!obj.url){
        throw(new ReferenceError("url未定义"));
    }

    obj.data = obj.data || {};
    obj.type = (obj.type || "GET").toUpperCase();
    params = paramsFormat(obj.data);

    var rq = new XMLHttpRequest();
    rq.onreadystatechange = function(){
        if(rq.readyState == 4){
            if(rq.status >= 200 && rq.status < 300 || rq.status == 304){
                obj.success && obj.success(rq.responseText);
            }else{
                obj.fail && obj.fail(rq.status);
            }
        }
    };

    if(obj.type == "GET"){
        rq.open("GET", obj.url + "?" + params, true);
        rq.send(null);
    }else if(obj.type == "POST"){
        rq.open("POST", obj.url, true);
        rq.setRequestHeader("Content-type", "x-www-form-urlencoded");
        rq.send(params);
    }

    //格式化参数
    function paramsFormat(data){
        var paramsArr = [];
        for(var key in data){
            paramsArr.push(key + "=" + data[key]);
        }
        return paramsArr.join("&");
    }
}

//获取ID
function getIdEle(id){
    return document.getElementById(id);
}

//生成字符串
function joinStr(data){
    return '<tr>' +
            '<td>'+ data.time + '</td><td>' + data.name + '</td><td>' + data.num + '</td>' +
            '<td>' + data.eCoin + '</td><td>' + (data.status ? "兑换成功" : "兑换失败") + '</td><td>' + data.info + '</td>' +
            '<td><input class="exchange_btn" value="兑换信息" type="button" data-id="' + data.id + '"/></td><td></td>' +
        '</tr>';
}