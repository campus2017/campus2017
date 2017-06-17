	var exchangeRecord=document.getElementById('exchange-record');
	var search=document.getElementById('search');
	exchangeRecord.onclick=function(){
		var xhr;
		if (window.XMLHttpRequest) {
            xhr=new XMLHttpRequest();
	    }else{
	        xhr=new ActiveXObject("Microsoft.XMLHTTP");
	    }
		xhr.onreadystatechange=function(){
			if (xhr.readyState==4) {
				if((xhr.status>=200&&xhr.status<300)||xhr.status==304){
					var data=JSON.parse(xhr.responseText)
					showExchangeRecord(data);
				}else{
					alert("Request was unsuccessful: "+xhr.status);
				}
			}
		}
		xhr.open("get","../data/data.json",true);
		xhr.send();
	}
	function showExchangeRecord(exampleData){
		for(var i=0;i<exampleData.length;i++){
			var oneRecord=exampleData[i];
			var newTr=document.createElement('tr');
			newTr.innerHTML='<td>'+ oneRecord.time + '</td><td>' + oneRecord.name + '</td><td>' + oneRecord.num + '</td>' +
            '<td>' + oneRecord.eCoin + '</td><td>' + (oneRecord.status ? "兑换成功" : "兑换失败") + '</td><td>' + oneRecord.info + '</td>' +
            '<td><div class="exchange-info">兑换信息</div></td><td></td>';
            var tabConetntTable=document.getElementById('tab-content-table');
		    tabConetntTable.appendChild(newTr);
		}
		
	}
	// search.onclick=function(){
	// 	var xhr;
	// 	if (window.XMLHttpRequest) {
 //            xhr=new XMLHttpRequest();
	//     }else{
	//         xhr=new ActiveXObject("Microsoft.XMLHTTP");
	//     }
	// 	xhr.onreadystatechange=function(){
	// 		if (xhr.readyState==4) {
	// 			if((xhr.status>=200&&xhr.status<300)||xhr.status==304){
	// 				var data=JSON.parse(xhr.responseText)
	// 				searchExchangeRecord(data);
	// 			}else{
	// 				alert("Request was unsuccessful: "+xhr.status);
	// 			}
	// 		}
	// 	}
	// 	xhr.open("post","../data/data.json",true);
	// 	xhr.send();
	// };
	// function searchExchangeRecord(startTime,endTime,exampleData){
	// 	for(var i=0;i<exampleData.length;i++){
	// 		var oneRecord=exampleData[i];
	// 		var newTr=document.createElement('tr');
	// 		newTr.innerHTML='<td>'+ oneRecord.time + '</td><td>' + oneRecord.name + '</td><td>' + oneRecord.num + '</td>' +
 //            '<td>' + oneRecord.eCoin + '</td><td>' + (oneRecord.status ? "兑换成功" : "兑换失败") + '</td><td>' + oneRecord.info + '</td>' +
 //            '<td><div class="exchange-info">兑换信息</div></td><td></td>';
 //            var tabConetntTable=document.getElementById('tab-content-table');
	// 	    tabConetntTable.appendChild(newTr);
	// 	}
	// }