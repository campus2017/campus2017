/**
 * Created by hust on 2016/11/27.
 */
var btn=document.querySelector("#btn");

// 使用单例模式，弹出层只会在需要的时候创建一次
var createPopLayer=(function(){
    var popLayer;
    return function(width,height){
        if (!popLayer) {
            var section=document.createElement('section');
            section.id="pop-up";
            if (width) {
                section.style.width=width+'px';
            }
            if (height) {
                section.style.height=height+'px';
            }
            section.innerHTML= '<div><img src="res/layer.png" alt=""></div><div class="close"></div>';
            document.body.appendChild(section);

            // 接下来创建一个遮罩
            var mask=document.createElement('section');
            mask.id="mask";
            document.body.appendChild(mask);

            popLayer={};
            popLayer.show=function(){

                section.style.visibility='visible';
                mask.style.visibility='visible';
            };
            popLayer.hide=function(){
                section.style.visibility='hidden';
                mask.style.visibility='hidden';
            }

            mask.onclick=popLayer.hide();

            // 这里进行了dom查询效率不是很好
            document.querySelectorAll(".close")[0].onclick=popLayer.hide;

        }
        return popLayer;
    }

})();



btn.onclick=function () {
    var popLayer=createPopLayer(800,546);
    popLayer.show();
}
