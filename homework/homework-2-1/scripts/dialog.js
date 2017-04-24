window.onload = function(){
	var mask = document.getElementById("mask");
	var dialogContainer = document.getElementById("dialog-container");
	var equipmentType = document.getElementById("equipment-type");
	var cancel = document.getElementById("cancel-button");
	debugger
	equipmentType.onclick = function(ev){
		debugger;
	    var ev = ev || window.event;
	    var target = ev.target || ev.srcElement;
	    if(target.nodeName.toLowerCase() == "a"){
	    	mask.style.display = "block";
	    	dialogContainer.style.display = "block";
	    }
	}
	cancel.onclick = function(ev){
		mask.style.display = "none";
    	dialogContainer.style.display = "none";
	}
} 


