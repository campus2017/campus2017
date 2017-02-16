var total_travel_time=document.getElementById("total_travel_time"),
	lowest_quotation=document.getElementById("lowest_quotation"),
	tabContent=document.getElementById("tabContent"),
	data_num=data.length,
    lis=document.getElementById("tabNav").getElementsByTagName("li"),
    back=document.getElementById("back"),
    go=document.getElementById("go"),
    li_select=document.getElementById("tabNav").getElementsByClassName("select")[0];
/*绑定事件*/
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

/*初始化各个tab信息*/
function init(){
	for(var i=0;i<data_num;i++){
		var tabInfo=document.createElement("div");
		tabInfo.setAttribute("id",data[i].tabid);
		tabInfo.setAttribute("class","tabInfo");
		tabInfo=initTabCon(tabInfo,i);
		tabContent.appendChild(tabInfo);
		if(i>0){
			tabInfo.style.display="none";
		}
	}
	tabChange();
}

function initTabCon(tabInfo,i){
	var tabCon=data[i].tabcon;//第i个tab页的内容，是个数组
	var tabCon_num=tabCon.length;
	for(var i=0;i<tabCon_num;i++){
		if((i+1)%2==0){
			var lowestprice=document.createElement("div");
			lowestprice.setAttribute("class","lowestprice");
			lowestprice.innerHTML="<div class='ticketprice'>"+"<span>￥</span><span>2259</span>票价</div>"
			                       +"<div class='taxes'>￥2784税费</div>"+"<div class='orderbotton'></div>";
			tabInfo.appendChild(lowestprice);
		}
		var flight=document.createElement("div");
	    flight.setAttribute("class","flight");
	    /*公司图标*/
		var companylogo=document.createElement("div");
		companylogo.setAttribute("class","companylogo");
		/*公司信息*/
		var companyInfo=document.createElement("div");
		companyInfo.setAttribute("class","companyInfo");
		var companyname=document.createElement("div");
		companyname.setAttribute("class","companyname");
		companyname.innerHTML=tabCon[i].companyname;
		var flightversion1=document.createElement("div");
		flightversion1.setAttribute("class","flightversion1");
		flightversion1.innerHTML=tabCon[i].flightversion1+"<a href='#'>共享</a>";
		var flightversion2=document.createElement("flightversion2");
		flightversion2.setAttribute("class","flightversion2");
		flightversion2.innerHTML=tabCon[i].flightversion2;
		companyInfo.appendChild(companyname);
		companyInfo.appendChild(flightversion1);
		companyInfo.appendChild(flightversion2);
		flight.appendChild(companylogo);
		flight.appendChild(companyInfo);
		/*航班时间信息*/
		var time_airport_Info=document.createElement("time_airport_Info");
		time_airport_Info.setAttribute("class","time_airport_Info");
		var time_airport_leftInfo=document.createElement("div");
		time_airport_leftInfo.setAttribute("class","time_airport_leftInfo");
		time_airport_leftInfo.innerHTML="<div class='start_time'>"+tabCon[i].start_time+"</div>"
		                                +"<div class='start_airport'>"+tabCon[i].start_airport+"</div>";
		var axislogo=document.createElement("div");
		axislogo.setAttribute("class","axislogo");
		var time_airport_middleInfo=document.createElement("div");
		time_airport_middleInfo.setAttribute("class","time_airport_middleInfo");
		time_airport_middleInfo.innerHTML="<div class='duration'>约"+tabCon[i].duration+"小时</div>"
		                       +"<div class='transform_stop_airport'>"
		                           +"<div class='transform_airport'>"+tabCon[i].transform_airport+"</div>"
		                           +"<div class='stop_airport'>"+tabCon[i].stop_airport+"</div>"
		                       +"</div>";
		var time_airport_rightInfo=document.createElement("div");
		time_airport_rightInfo.setAttribute("class","time_airport_rightInfo");
		time_airport_rightInfo.innerHTML="<div class='end_time'>"+tabCon[i].end_time+"<span class='delayDay'>+"
		                                  +tabCon[i].delayDay+"天</span></div>"
		                                  +"<div class='end_airport'>"+tabCon[i].end_airport+"</div>";
		time_airport_Info.appendChild(time_airport_leftInfo);
		time_airport_Info.appendChild(axislogo);
		time_airport_Info.appendChild(time_airport_middleInfo);
		time_airport_Info.appendChild(time_airport_rightInfo);
		flight.appendChild(time_airport_Info);
		/*推荐网站信息*/
		var recommendWebsite=document.createElement("div");
		recommendWebsite.setAttribute("class","recommendWebsite");
		recommendWebsite.innerHTML="<div class='recommendname'>"+"<a href='#'>达美航空</a>"+"</div>"
		                            +"<div class='recommendprice'>￥4091</div>";
		flight.appendChild(recommendWebsite);
		tabInfo.appendChild(flight);
	}
	return tabInfo;
}
/*tab切换*/
function tabhighLightOrNot(){
	lis=document.getElementById("tabNav").getElementsByTagName("li");
	for(var i=1;i<=data_num;i++){
		lis[i].className="";
	}
}
function tabContentDisplayOrNot(divs){
	for(var i=0;i<data_num;i++){
		divs[i].style.display="none";
	}
}
function tabChange(){
	lis=document.getElementById("tabNav").getElementsByTagName("li");
	var divs=document.getElementsByClassName("tabInfo");
	for(var i=1;i<=data_num;i++){
		lis[i].id=i;
		lis[i].style.cursor="pointer";
		addHandler(lis[i],"click",function(){
			tabhighLightOrNot();
			this.className="select";
			tabContentDisplayOrNot(divs);
			divs[this.id-1].style.display="block";
		});
	}
	addHandler(back,"click",function(){
		lis=document.getElementById("tabNav").getElementsByTagName("li");
		li_select=document.getElementById("tabNav").getElementsByClassName("select")[0];
		if(li_select.id!=="1"){
			var num=parseInt(li_select.id)-1;
			lis[num].click();
		}else{
			return ;
		}
		/*for(var i=1;i<=data_num;i++){
			if(lis[i].className=="select"){
				if(lis[i].id!=="1"){
					lis[i-1].click();
				}else{
					return ;
				}
			}
		}*/
	});
	addHandler(go,"click",function(){
		lis=document.getElementById("tabNav").getElementsByTagName("li");
		li_select=document.getElementById("tabNav").getElementsByClassName("select")[0];
		if(li_select.id!=="7"){
			var num=parseInt(li_select.id)+1;
			lis[num].click();
		}else{
			return ;
		}
	});
}
/*添加三角的函数*/
function addArrow(div,flag){
	div.style.cursor="pointer";
	div.style.width="0px";
	div.style.height="0px";
	div.style.borderLeft="5px solid transparent";
	div.style.borderRight="5px solid transparent";
	div.style.position = "absolute";

	if(flag){
		addHandler(div,"click",function(){
			li_select=document.getElementById("tabNav").getElementsByClassName("select")[0];
			var data_temp=data[parseInt(li_select.id)-1];
			for(var i=0;i<data_temp.tabcon.length;i++){
				for(var j=i+1;j<data_temp.tabcon.length;j++){
					if(data_temp.tabcon[i].duration>data_temp.tabcon[j].duration){
						var temp=data_temp.tabcon[i];
						data_temp.tabcon[i]=data_temp.tabcon[j];
						data_temp.tabcon[j]=temp;
					}
				}
			}
			tabContent.innerHTML="";
			reset();
	    });
	}else{
		addHandler(div,"click",function(){
			li_select=document.getElementById("tabNav").getElementsByClassName("select")[0];
			var data_temp=data[parseInt(li_select.id)-1];
			for(var i=0;i<data_temp.tabcon.length;i++){
				for(var j=i+1;j<data_temp.tabcon.length;j++){
					if(data_temp.tabcon[i].duration<data_temp.tabcon[j].duration){
						var temp=data_temp.tabcon[i];
						data_temp.tabcon[i]=data_temp.tabcon[j];
						data_temp.tabcon[j]=temp;
					}
				}
			}
			tabContent.innerHTML="";
			reset();
	    });
	}
	return div;
}
function reset(){
	for(var i=0;i<data_num;i++){
		var tabInfo=document.createElement("div");
		tabInfo.setAttribute("id",data[i].tabid);
		tabInfo.setAttribute("class","tabInfo");
		tabInfo=initTabCon(tabInfo,i);
		tabContent.appendChild(tabInfo);
		if(i>0){
			tabInfo.style.display="none";
		}
	}
	var divs=document.getElementsByClassName("tabInfo");
		for(var i=0;i<divs.length;i++){
			if(divs[i].id==parseInt(li_select.id)){
				divs[i].style.display="block";
			}else{
				divs[i].style.display="none";
			}
	}
}
/*添加上三角*/
function addArrowUp(){
    var total_travel_time_updiv=document.createElement("div"),
	    lowest_quotation_updiv=document.createElement("div");
	total_travel_time_updiv=addArrow(total_travel_time_updiv,true);
	total_travel_time_updiv.style.borderTop="5px solid transparent";
	total_travel_time_updiv.style.borderBottom ="5px solid #fff";
	total_travel_time_updiv.style.left = "72px";
	total_travel_time_updiv.style.top = "5px";
	total_travel_time.appendChild(total_travel_time_updiv);
	lowest_quotation_updiv=addArrow(lowest_quotation_updiv,true);
	lowest_quotation_updiv.style.left = "60px";
	lowest_quotation_updiv.style.borderTop="5px solid transparent";
	lowest_quotation_updiv.style.borderBottom ="5px solid #fff";
	lowest_quotation_updiv.style.top = "5px";
	lowest_quotation.appendChild(lowest_quotation_updiv);
}
/*添加下三角*/
function addArrowDown(){
	var total_travel_time_downdiv=document.createElement("div"),
	    lowest_quotation_downdiv=document.createElement("div");
	total_travel_time_downdiv=addArrow(total_travel_time_downdiv,false);
	total_travel_time_downdiv.style.borderBottom="5px solid transparent";
	total_travel_time_downdiv.style.borderTop ="5px solid #fff";
	total_travel_time_downdiv.style.left = "72px";
	total_travel_time_downdiv.style.bottom = "6px";
	total_travel_time.appendChild(total_travel_time_downdiv);
	lowest_quotation_downdiv=addArrow(lowest_quotation_downdiv,false);
	lowest_quotation_downdiv.style.borderBottom="5px solid transparent";
	lowest_quotation_downdiv.style.borderTop ="5px solid #fff";
	lowest_quotation_downdiv.style.left = "60px";
	lowest_quotation_downdiv.style.bottom = "6px";
	lowest_quotation.appendChild(lowest_quotation_downdiv);
}
(function(){
	init();
	addArrowUp();
	addArrowDown();
})();