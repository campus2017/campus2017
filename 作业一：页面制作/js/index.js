

function createElem(tag,classname,val,hrefVal,targetVal) {
	var elem = document.createElement(tag);
	elem.className=classname;
	if (tag=='a'){
		elem.href=hrefVal;
		elem.target=targetVal;
	}
	if(classname=='price-line1'){
		elem.innerHTML = "<span class='font-12'>&yen;</span>"+val;
	}else if(classname=="price-line2"){
		elem.innerHTML = "原价&yen;"+val;
	}else{
		elem.innerHTML = val;
	}
	return elem;
}

function createEl(tag,classname){
	var elem = document.createElement(tag);
	elem.className=classname;
	return elem;
}

function appendElem(itemData,dataVal){
	var itemList = document.getElementById("item-list");
	for(var k=0;k<itemData.length;k++){
		var item = itemList.appendChild(createEl('div','item'));
		var itemHead = item.appendChild(createEl('div','item-head'));
		var itemContent = item.appendChild(createEl('div','item-content'));
		if(k!=3){
			itemHead.appendChild(createEl('img','title-img')).src=itemData[k].t_img;
			itemHead.appendChild(createElem('span','title-big border-r',itemData[k].t_big));
			itemHead.appendChild(createElem('span','title-small',itemData[k].t_small));
			itemHead.appendChild(createElem('a','title-right',itemData[k].t_right,'index.html','_blank'));
			var itemContent = item.appendChild(createEl('div','item-content'));
			var itemBig = itemContent.appendChild(createEl('div','img-big'));
			var descRight = itemBig.appendChild(createEl('div','desc-right'));
			descRight.appendChild(createElem('div','text-line1',dataVal[k].t_head));
			descRight.appendChild(createElem('div','text-line2',dataVal[k].t_sub));
		}else{
			itemHead.appendChild(createElem('span','title-big',itemData[k].t_big));
			itemHead.appendChild(createElem('a','title-right',itemData[k].t_right,'index.html','_blank'));
		}

		var price = descRight.appendChild(createEl('div','price'));
		var i = price.appendChild(createEl('i',''));
		i.appendChild(createElem('span','price-line1',dataVal[k].t_price));
		i.appendChild(createEl('br',''));
		i.appendChild(createElem('del','price-line2',dataVal[k].t_del));
		descRight.appendChild(createElem('a','book','立即预订','index.html','_blank'));
		var itemSmall = itemContent.appendChild(createEl('div','img-small'));
		for(var j=1;j<=(dataVal.length-1);j++){
			var itemLeft = itemSmall.appendChild(createEl('div','img-left'))
			var descBottom = itemLeft.appendChild(createEl('div','desc-bottom'));
			descBottom.appendChild(createElem('div','text-line1',dataVal[j].t_head));
			descBottom.appendChild(createElem('div','text-line2',dataVal[j].t_sub));
			var price = descBottom.appendChild(createEl('div','price'));
			var i = price.appendChild(createEl('i',''));
			i.appendChild(createElem('del','price-line2',dataVal[j].t_del));
			i.appendChild(createElem('span','price-line1',dataVal[j].t_price));
			descBottom.appendChild(createElem('a','book','立即预订','index.html','_blank'));
		}
	}
}

window.onload = function(){
	var itemData = [
		{
			't_img':'img/person1.png',
			't_big':'人生第一“泡”',
			't_small':'经济实惠，泡之初体验',
			't_right':'查看全部温泉酒店>>',
		},
		{
			't_img':'img/person2.png',
			't_big':'双享“泡”',
			't_small':'此水只应天上有，人间难得几回“泡”',
			't_right':'查看全部温泉酒店>>',
		},
		{
			't_img':'img/person3.png',
			't_big':'团圆“泡”',
			't_small':'感情是“泡”出来的',
			't_right':'查看全部温泉酒店>>',
		},
		{
			't_big':'更多超值酒店精选',
			't_right':'查看全部温泉酒店>>',
		}
	];

	var dataVal =[
		{
			't_head':'北京天上人间温泉酒店',
			't_sub':'北京天上人间温泉酒店北京天上人间温泉酒店北京天上人间温泉酒店',
			't_price':'1999',
			't_del':'2999',
		},
		{
			't_head':'北京天上人间温泉酒店',
			't_sub':'北京天上人间温泉酒店北京天上人间温泉酒店',
			't_price':'1999',
			't_del':'2999',
		},
		{
			't_head':'北京天上人间温泉酒店',
			't_sub':'北京天上人间温泉酒店北京天上人间温泉酒店',
			't_price':'1999',
			't_del':'2999',
		},
		{
			't_head':'北京天上人间温泉酒店',
			't_sub':'北京天上人间温泉酒店北京天上人间温泉酒店',
			't_price':'1999',
			't_del':'2999',
		},
		{
			't_head':'北京天上人间温泉酒店',
			't_sub':'北京天上人间温泉酒店北京天上人间温泉酒店',
			't_price':'1999',
			't_del':'2999',
		}
	];
	appendElem(itemData,dataVal);
	var info_left = document.getElementById("info-left");
	var info_right = document.getElementById("info-right");
	info_right.style.top = info_left.style.top = "500px";
	timer = 0;
	window.onscroll = function(){
		clearInterval(timer);
		scrolltop = getScrollTop();
		if(scrolltop>400){
			info_left.style.top = "100px";
			info_right.style.top = "100px";
		}else{
		clearInterval(timer);
		timer = setInterval(function(){
			var flag = true;
			start = scrolltop;
			end = 400;
			var step = (end -start)/10;
     		step = step > 0 ? Math.ceil(step) : Math.floor(step);
			start = start + step;
			info_left.style.top = 550-start+"px";
			info_right.style.top = 550-start+"px";
			if(start!=end){
				flag=false;
			}
		},15);
		}
	}
	function getScrollTop() {
		return (document.documentElement.scrollTop || 0) + (document.body.scrollTop || 0);
	}
}
