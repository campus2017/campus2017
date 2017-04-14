$(function() {
	'use strict'

	function start(){
		dateHandle();
		cityHandle();
		backTop();
	}

	function dateHandle(){
		var myDate = new Date();
		var todayDate = myDate.toLocaleDateString();
		var $tabPos = $('.tab-pos').children();
		$tabPos.each(function(index,item){
			var $item = $(item);
			var date = $item.find('.date').html();
			if(new Date(date).getTime() < new Date(todayDate).getTime()){
				$item[0].style.backgroundImage='url(images/gray'+ (index+1)  +'.png)' ;
			}
		});
	}

	function cityHandle(){
		var $citys = $('.city li'); 
		var $lastItem;
		$citys.each(function(index,item){
			var $item = $(item);
			var cityName = $item.find('input');
	    	var localCity = remote_ip_info["city"] ;

	    	cityName.on('focus',function(){
				this.style.fontWeight = 'bolder'; 
				$item.append('<div class="white-point"></div>');
			});
			cityName.on('blur',function(){
				$item.find('.white-point').remove();
				this.style.fontWeight = 'normal'; 
			});

			$item.hover(function(){
				if($lastItem){
					$lastItem.removeClass('hover');
					$lastItem.find('input').trigger('blur');
				}
				cityName.trigger('focus');
				$item.addClass('hover');
				$lastItem = $item;
			},function(){
				
			});
			if(cityName.val() == localCity){
				$item.trigger('mouseenter');
			}
		});
	}
	
	function backTop(){
		$('.back-top').on('click',function(){
			var speed=200;//滑动的速度
	        $('body,html').animate({ scrollTop: 0 }, speed);
	        return false;
		});
	}
	

	start();
});