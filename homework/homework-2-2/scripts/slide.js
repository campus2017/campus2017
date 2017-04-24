window.onload = function() {
	var door1 = document.getElementById('door1');
	var door2 = document.getElementById('door2');
	var door3 = document.getElementById('door3');
	var door4 = document.getElementById('door4');
	
	door1.onmouseover = function() {
		move(door2, "475");
		move(door3, "635");
		move(door4, "795");
	}
	door2.onmouseover = function() {
		move(door2, "160");
		move(door3, "635");
		move(door4, "795");
	}
	door3.onmouseover = function() {
		move(door2, "160");
		move(door3, "320");
		move(door4, "795");
	}
	door4.onmouseover = function() {
		move(door2, "160");
		move(door3, "320");
		move(door4, "480");
	}
}
function move(ele, targetLeft) {
	var timer = null;
	timer = setInterval(function() {
		var speed = (targetLeft - ele.offsetLeft) / 5;
		if(speed !=0 ){
			speed = speed > 0 ? Math.ceil(speed) : Math.floor(speed); 
			if (Math.abs(targetLeft - ele.offsetLeft) <= speed) { 
				clearInterval(timer); 
				ele.style.left = targetLeft + 'px'; 
			} else {
				ele.style.left = ele.offsetLeft + speed + 'px'; 
			}
		}else{
			clearInterval(timer);
		}
		
	}, 8)
}
