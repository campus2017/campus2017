var sel=document.getElementById('sel');
var select=sel.getElementsByTagName('a');//全部 成功 失败
var header=document.getElementById('header');
var oLi=header.getElementsByTagName('ul')[0].getElementsByTagName('li');
var c=0;//0全部 1成功 2失败
var tbody=document.getElementById('tb');

function change(op,classN){
	for(var i=0;i<op.length;i++){
		op[i].index=i;
		op[i].onclick=function(){
			for(var i=0;i<op.length;i++)
				op[i].className="";		
			this.className=classN;
			if(op == select){
				c=this.index;
			}	 
		}
	}	
}
change(oLi,"on");
change(select,"selected");


function createXHR() {
	if(typeof XMLHttpRequest != 'undefined'){
		return new XMLHttpRequest();
	}else if(typeof ActiveXObject !='undefined'){
		if(typeof arguments.callee.activeXString != 'string'){
			var versions=["MSXML2.XMLHttp.6.0",
			"MSXML2.XMLHttp.3.0","MSXML2.XMLHttp"],
				i,len;
			for(i=0,len=versions.length;i < len;i++){
				try{
					new ActiveXObject(versions[i]);
					arguments.callee.activeXString=
					versions[i];
					break;
				}catch(ex){}
			}

		}
		return new ActiveXObject(arguments.callee.activeXString);
	}else{
		throw new Error("No XHR object available");
	}
}

var xhr = createXHR();
xhr.onreadystatechange = function(){
	if(xhr.readyState == 4){
		if ((xhr.status >= 200 && xhr.status <300) || xhr.status ==
			304) {
			var content=xhr.responseText;
			var msg=eval("("+content+")");
			var inner="";
			for(var i=0;i<msg.length;i++){
				inner+='<tr><td><p>'+
				msg[i].date+'</p><p>'+
				msg[i].time+'</p><td>'+
				msg[i].name+'</td><td>'+
				msg[i].num+'</td><td>'+
				msg[i].E+'</td><td>'+
				msg[i].state+'</td><td>'+
				msg[i].info+'</td><td><a>'+
				'兑换信息</a></td><td></td></tr>';
				}
			tbody.innerHTML=inner;
		}else {
			alert("错误状态为："+xhr.status);
		}
	}
}
xhr.open("get","data.json",true);
xhr.send(null);