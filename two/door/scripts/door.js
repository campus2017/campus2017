window.onload = function(){
    var imgs = document.getElementById("door").getElementsByTagName("img");
    var imgsState = [0,0,0,1];  //用一个数组记录每张图片的展开与否
    var isMoving = false;        //用一个标志位表示是否正在滑动
    for (var i = imgs.length - 1; i >= 0; i--) {
        (function(i){
            imgs[i].addEventListener("mouseenter",function(){
                var index = i;
                var dest = 0;
                var speed = 1;
                var newLeft = 0;
                var middle = 0;
                if(imgsState[i] == 0 && isMoving == false){
                    //搜索当前展开的图片
                    for(var j = 0; j < imgsState.length; j++){
                        if(imgsState[j] == 1){
                            break;
                        }
                    }
                    if(j > i){
                        dest = j;
                        index = i + 1;
                        newLeft = imgs[index].offsetLeft + 475 - 160;
                    }else{
                        dest = j;
                        newLeft = imgs[index].offsetLeft - (475 - 160);
                    } 
                    middle = (newLeft + imgs[index].offsetLeft)/2;
                    var moveImg = setInterval(function(){
                        isMoving = true;
                        move(imgs, index, dest, speed);
                        if(dest >= index){
                            if(imgs[index].offsetLeft < middle){
                                speed++;
                            }else{
                                speed--;
                                speed >= 1 ? speed : 1;
                            }
                            if(imgs[index].offsetLeft >= newLeft){
                                clearInterval(moveImg);
                                isMoving = false;
                                imgs[index].offsetLeft = newLeft;
                            }
                        }else{
                            if(imgs[index].offsetLeft > middle){
                                speed++;
                            }else{
                                speed--;
                                speed >= 1 ? speed : 1;
                            }
                            if(imgs[index].offsetLeft <= newLeft){
                                clearInterval(moveImg);
                                isMoving = false;
                                imgs[index].offsetLeft = newLeft;
                            }
                        }   
                    }, 18);  
                    imgsState[i] = 1;
                    imgsState[j] = 0;
                    console.log(imgsState+" index:"+index+" dest:"+dest+" newLeft:"+newLeft);
                }
            });
        })(i);
    }
}
/*移动函数
 *imgs:图片节点序列
 *index:起始待移动图片
 *dest:终止移动图片
 *speed:移动速度
 */
function move(imgs, index, dest, speed){
    if(dest >= index){
        for(; index <= dest; index++){
            imgs[index].style.left = imgs[index].offsetLeft + speed + "px";
        }
    }else{
        speed *= -1;
        for(; index > dest; index--){
            imgs[index].style.left = imgs[index].offsetLeft + speed + "px";
        }
    }
}