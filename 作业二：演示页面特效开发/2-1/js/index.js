var mask = document.getElementById("mask");
var layerDiv = document.getElementById("layerDiv");

function showDiv(){
	mask.style.display = "block";
	layerDiv.style.display = "block";
	layerDiv.style.left=(document.documentElement.clientWidth-layerDiv.clientWidth)/2+document.documentElement.scrollLeft+"px";
	layerDiv.style.top =(document.documentElement.clientHeight-layerDiv.clientHeight)/2+document.documentElement.scrollTop+"px";
	window.onresize = function(){
		layerDiv.style.left=(document.documentElement.clientWidth-layerDiv.clientWidth)/2+document.documentElement.scrollLeft+"px";
		layerDiv.style.top =(document.documentElement.clientHeight-layerDiv.clientHeight)/2+document.documentElement.scrollTop+"px";
	}
}

function hideDiv(){
	mask.style.display = "none";
	layerDiv.style.display = "none";
}