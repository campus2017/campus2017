 window.onload = function() {
        var container = document.getElementById("container");
        var imgs = container.getElementsByTagName("img");//获取图像
        var imgWidth = imgs[0].offsetWidth;//图片的宽度
        var exposeWidth = 275;//露出的宽度
        var containerWidth = imgWidth + exposeWidth * (imgs.length - 1); //外部盒子的总宽度
        container.style.width = containerWidth + "px";

        function Initial() {
            for(var i = 1; i < imgs.length; i++) {
                imgs[i].style.left = imgWidth + exposeWidth * (i - 1) + "px";
				//imgs[i].style.top = 0+ "px" ;
            }
        }
        Initial();        
        var translateWidth = imgWidth - exposeWidth;//移动的距离
        for(var i = 0; i < imgs.length; i++) {
            (function(i) {
                imgs[i].onmouseover = function() {
                    Initial(); //鼠标经过图片时，首先设置到初始状态
                    for (var j=1;j<=i;j++) {   //之前的图片都移动相同的距离
                        imgs[j].style.left=parseInt(imgs[j].style.left)-translateWidth+"px";
                    }
                }
            })(i);
        }

    }