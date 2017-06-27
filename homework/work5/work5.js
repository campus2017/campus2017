window.onload = function(){
	var dateLis = document.getElementsByClassName('date-li');
	var tables = document.getElementsByClassName('flight-table');
	var whiteSpace = document.getElementById('white-space');
//添加鼠标点击事件
	for (let i = 0; i < dateLis.length; i++) {
		
		dateLis[i].onclick = function(){
			for (var j = 0; j< tables.length; j++) {
				tables[j].style.display = "none";
			}
			tables[i].style.display = "table";
			whiteSpace.style.left = 105*i+50+"px";
			
		}
	}
}