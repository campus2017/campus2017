window.onload=function(){
	var dateList=document.getElementsByClassName("date");
	console.log(dateList[3].classList);

	var dateClick='date-click';
	for(var i=0;i<dateList.length;i++){
		dateList[i].onclick=function(){
			for(var j=0;j<dateList.length;j++){
				dateList[j].classList.remove(dateClick);
			   }
			  this.classList.add('date-click');
		   }   
		}
	}
