window.onload=function(){
	var list=document.getElementById('list');
	var mask=document.getElementById('mask');
	var dialog=document.getElementById('dialog');
	var close=document.getElementById('dialog-btn');
	list.onclick=function(){
		mask.style.display='block';
		dialog.style.display='block';
	};
	close.onclick=function(){
		mask.style.display='none';
		dialog.style.display='none';
	};
};