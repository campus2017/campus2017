window.onload=function(){
	//根据日期不同，变背景
	function changeDate(){
		var now = Date.parse(new Date());
		var date1 = Date.parse("July 15, 2017");
		var date2 = Date.parse("November 25, 2017");
		var date3 = Date.parse("December 25, 2017");
		var triangle = document.getElementsByClassName('triangle');
		var background = document.getElementsByClassName('timeaix-item');
		//初始化，背景全都是灰色，小三角形全都被隐藏起来
		for(var i=0; i<triangle.length; i++){
			background[i].style.backgroundPosition='0 100%';
			if(triangle[i].className.indexOf('hidden')==-1){
				triangle[i].className += ' hidden';
			};
		}
		//改变背景颜色与控制小三角形的隐藏
		function change(obj1,obj2){
			obj1.className=obj1.className.replace(" hidden",'');
			obj2.style.backgroundPosition='0 0';
		}
		if (now<=date1) {
			change(triangle[0],background[0]);
		}else if (now>date1 && now<=date2) {
			change(triangle[1],background[1]);
		}else if (now>=date2 && now<=date3) {
			change(triangle[2],background[2]);
		}
		
	}
	//除灰色按钮外，其它按钮划过时变颜色
	//应该可以在css里用hover做到的，但灰色按钮与其它按钮共用一个button样式，不知怎么把它排除在外。
	function changeBtn(){
		var btn = document.getElementsByClassName('button');
		for (var i = 0; i < btn.length; i++) {
			if(btn[i].className.indexOf('tab-btn')==-1){
				btn[i].onmouseover=function(){
					this.style.background="rgb(167,222,85)";
					this.style.cursor = "pointer";
				}
				btn[i].onmouseout=function(){
					this.style.background="rgb(120,175,36)";
				}
			}
		}
	}
	function getCity(){
		//根据用户IP地址自动匹配
		var city = returnCitySN["cname"];
		var arrCity = ['北京','天津','上海','重庆','西安','成都','广州','深圳','杭州','郑州','海口'];
		var location = document.getElementsByClassName('location');
		//褐色小方块
		//下面用了两个for循环，复杂度高呀
		var choose = document.getElementById('choose');
		for (let i = 0; i < arrCity.length; i++) {
			location[i].className = location[i].className.replace('change','');
			if(city.indexOf(arrCity[i])!=-1){
				location[i].className += ' change';
				choose.style.left = 87*i+10+'px';
			}
			location[i].onmouseover = function(){
				for (let i = 0; i < location.length; i++) {
					location[i].className = location[i].className.replace('change','');
				}
				this.className += ' change';
				this.style.cursor = 'pointer';
				choose.style.left = 87*i+10+'px';
			}
			
		}

	}
	function allClick(){
		var clickEle1 = document.getElementsByClassName('content-pic-head');
		var clickEle2 = document.getElementsByClassName('content-pic-bar-l');
		clic(clickEle1);
		clic(clickEle2);
		function clic(arr){
			for (var i = 0; i < arr.length; i++) {
				arr[i].onclick = function(){
					window.open("#");
				}
			}
		}
	}
	//插入微博话题
	function insertTopic(){
		var target = document.getElementById('content');
		
		target.onclick = function(){
			var con = "请在此输入自定义话题";
			target.value += '#'+con+'#';
			var len = target.value.length;
			if (target.createTextRange) {
				range.moveStart('character',1);
				range.moveEnd('character',len-1);
				range.select();
			}else{
				target.setSelectionRange(len-con.length-1,len-1);
				target.focus();
			}
		}
	}
	//回到顶部 
	function returnToTop(){
		var btn = document.getElementById('to-top');
		var timer = null;
		var bJs = false;
		window.onscroll = function(){
			if (!bJs) {
				clearInterval(timer);
			}
			bJs = false;
		}
		btn.onclick = function(){
			clearInterval(timer);
			timer = setInterval(function(){
				var scrollTop = document.body.scrollTop||document.documentElement.scrollTop;
				console.log(scrollTop);
				var iSpeed = Math.floor(-scrollTop/8);
				bJs = true;
				document.documentElement.scrollTop = scrollTop+iSpeed;
				document.body.scrollTop = scrollTop + iSpeed;
				if (scrollTop <= 0) {
					clearInterval(timer);
				};
			},30);
		}
	}
	changeDate();
	changeBtn();
	getCity();
	allClick();
	insertTopic();
	returnToTop();
}