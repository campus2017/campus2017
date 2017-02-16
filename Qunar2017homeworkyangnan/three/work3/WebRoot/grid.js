var allbutton=document.getElementById("all"),
    successbutton=document.getElementById("success"),
    failbutton=document.getElementById("fail");
//添加事件
function addHandler(ele,type,handler){
	if(ele.addEventListener){
		addHandler=function(ele,type,handler){
			ele.addEventListener(type,handler,false);
		};
	}else if(ele.attachEvent){
		addHandler=function(ele,type,handler){
			ele.attachEvent("on"+type,handler);
		};
	}else{
		addHandler=function(ele,type,handler){
			ele["on"+type]=handler;
		};
	}
	addHandler(ele,type,handler);
}
var obj={
		type: "get",
		url:"/work3/GridServlet",
		data:{status:"all"},
		dataType: "json",
        success: function (data) {
        	//alert(data);
        	if(data!="0"){
        		var datas=data.split("&");
            	var str="";
            	if(datas.length>0){
            		for(var i=0;i<datas.length;i++){
                		//alert(datas[i]);
                		str+= joinStr(JSON.parse(datas[i]));
                	}
            		//alert(str);
                	if(document.getElementById("tbody")){
                		var tbody  = document.getElementById("tbody");
                		tbody.innerHTML = str;
                        var table = document.getElementById("show");
                        table.append(tbody);
                	}else{
                		alert("不存在数据");
                	}
            	}
        	}else{
            		if(document.getElementById("tbody")){
            			var tbody  = document.getElementById("tbody");
                		tbody.innerHTML = "<tr>无记录</tr>";
                		table.appendChild(tbody);
                	}else{
                		
                	}

        	}
        	//alert("查询成功");
        },
        fail: function(data){
        	alert("查询失败");
        },
        error: function (data) {
        	alert("查询出错");
        }	
};
function formatParams(data) {
    var arr = [];
    for (var key in data) {
        arr.push(encodeURIComponent(key) + "=" + encodeURIComponent(data[key]));
    }
    return arr.join("&");
}
function joinStr(data){
    return "<tr>" +
            "<td>"+ data.time + "</td><td>" + data.name + "</td><td>" + data.num + "</td>" +
            "<td>" + data.ecoin + "</td><td>" + (data.status ? "兑换成功" : "兑换失败") + "</td><td>" + data.info + "</td>" +
            '<td><button class="btn btn-primary" data-id="' + data.id + '">兑换信息</button></td><td></td>' +
        '</tr>';
}
function ajax(status,method){
	obj.type=(method||"GET").toUpperCase();
	obj.dataType = obj.dataType || "json";
	obj.data.status=status||"all";
	var params=formatParams(obj.data);
	/*alert(params);*/
	if (window.XMLHttpRequest) {
        var xhr = new XMLHttpRequest();
    } else { 
        var xhr = new ActiveXObject('Microsoft.XMLHTTP');
    }
	if (obj.type == "GET") {
        xhr.open("GET", obj.url + "?" + params, true);
        xhr.send(null);
    } else if (obj.type == "POST") {
        xhr.open("POST", obj.url, true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.send(params);
    }
	xhr.onreadystatechange = function () {
        if (xhr.readyState == 4) {
            var status = xhr.status;
            if (status >= 200 && status < 300) {
                obj.success && obj.success(xhr.responseText, xhr.responseXML);
            } else {
                obj.fail && obj.fail(status);
            }
        }
    }
}

addHandler(allbutton,"click",function(){
	ajax("all","get");
});
addHandler(successbutton,"click",function(){
	ajax("success","get");
});
addHandler(failbutton,"click",function(){
	ajax("fail","get");
});