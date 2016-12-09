
start("door_main");
function start(id) {
    var door = document.getElementById(id);
    var isAni = false;//判断动画是否在进行中
    setStyle(door, {
        position: "relative",
        listStyle: "none",
        margin: "0 auto",
        padding: 0,
        overflow: "hidden",
        border: "solid #c1c1c1",
        borderWidth: "0 1px 1px 1px"
    });//设置door的初始样式

    var beforeSign = 0;//beforeSign代表当前打开的图片为第0个
    var photos = [];

    var doorWidth = parseInt(getComputedStyle(door).width);
    var doorli = door.querySelectorAll("li");
    door.style.height = getComputedStyle(doorli[0]).height;//通过doorli元素设置door的高度
    doorli[0].style.position = "absolute";//设置doorli0的位置脱离文档流，可以获取他的实际宽度
    var oneDoorWidth = parseInt(getComputedStyle(doorli[0]).width);
    var oneWidth = ((doorWidth - oneDoorWidth) / (doorli.length - 1));//被分割后的宽度
    var moveWidth = oneDoorWidth - oneWidth;
    for (var i = 0; i < doorli.length; i++) {
        setStyle(doorli[i], {
            position: "absolute",
            top: 0,
            border: "solid #c1c1c1",
        });
        if (i == 0) {
            doorli[i].style.left = 0;
            doorli[i].style.borderWidth = 0;
            photos.push({
                left: 0,
                element: doorli[i],
                interval: null
            });
        } else {
            photos.push({
                left: oneWidth * (i - 1) + oneDoorWidth,
                element: doorli[i],
                interval: null
            });
            doorli[i].style.left = oneWidth * (i - 1) + oneDoorWidth + 'px';
            doorli[i].style.borderWidth = "0 0 0 1px";
        }
        (function (which) {
            doorli[i].addEventListener("mouseover", function () {
                if (beforeSign > which) {
                    doAni(which + 1, beforeSign, doorli, true);
                    isAni = true;
                } else {
                    if (i == 0) { return; }
                    doAni(beforeSign + 1, which, doorli, false);
                    isAni = true;
                }
                beforeSign = which;
            });
        } (i));
    }
    function doAni(from, to, doorli, isToRight) {
        for (var i = from; i <= to; i++) {
            var onePhoto = photos[i];
            if (onePhoto.interval) {
                clearInterval(onePhoto.interval);
                onePhoto.interval = null;
            }
            doInterval(onePhoto);
            function doInterval(onePhoto) {
                onePhoto.interval = setInterval(function () {
                    if (isToRight) {
                        onePhoto.element.style.left = onePhoto.element.offsetLeft + 2 + 'px';
                        if (onePhoto.element.offsetLeft >= onePhoto.left) {
                            onePhoto.element.style.left = onePhoto.left + 'px';
                            clearInterval(onePhoto.interval);
                            onePhoto.interval = null;
                        }
                    } else {
                        onePhoto.element.style.left = onePhoto.element.offsetLeft - 2 + 'px';
                        if (onePhoto.element.offsetLeft <= onePhoto.left - moveWidth) {
                            onePhoto.element.style.left = onePhoto.left - moveWidth + 'px';
                            clearInterval(onePhoto.interval);
                            onePhoto.interval = null;
                        }
                    }
                }, 5);

            }
        }
    }
}