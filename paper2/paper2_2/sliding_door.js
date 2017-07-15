window.onload = function() {
  //获得容器对象
  var box = document.getElementById("sliding_box");
  //获得图片NodeList对象集合
  var imgs = box.getElementsByTagName("img");
  //单张图片的宽度
  var imgWidth = imgs[0].offsetWidth;
  //设置掩藏门体露出得宽度
  var exposeWidth = 180;
  //设置容器总宽度
  var boxWidth = imgWidth + exposeWidth * (imgs.length - 1);
  box.style.width = boxWidth + "px";
  //设置每道门的初始位置
  function setImgsPos() {
    for(var i = 1; i < imgs.length; i++) {
      imgs[i].style.left = imgWidth + exposeWidth * (i - 1) + "px";
    }
  }
  setImgsPos();
  //计算每道门打开时应移动的距离
  var translate = imgWidth - exposeWidth;
  //为每道门绑定事件
  for(var i = 0; i < imgs.length; i++) {
  //使用立即调用的函数表达式，为了获得不同的i值
    (function(i) {
      imgs[i].onmouseover = function() {
        //设置每道门复位
        setImgsPos();
        //打开门
        for (var j=1;j<=i;j++) {
        imgs[j].style.left=parseInt(imgs[j].style.left)-translate+"px";
        }
      }
    })(i);
  }
}
