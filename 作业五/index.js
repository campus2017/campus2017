/**
 * Created by chao on 2017/7/3.
 */
/**
 * 顶部最大标签页切换
 * @type {NodeList}
 */
var lis = document.getElementById("tab").getElementsByTagName("li");
var contents = document.getElementById("content").children;
//当前的tab
var nowTab = 1;
var startTab = 1;
var endTab = 2;
//初始化左右按钮
lis[0].addEventListener("click", function () {
    if (nowTab <= 1) {
        //对整个日历进行更新
    } else {
        tabChange(lis[nowTab - 1], nowTab - 1);
    }
});
lis[lis.length - 2].addEventListener("click", function () {
    if (nowTab >= lis.length - 3) {
        //对整个日历更新
    } else {
        tabChange(lis[nowTab + 1], nowTab + 1);
    }
});
for (var i = startTab; i < lis.length - endTab; i++) {
    tabChangeInit(lis[i], i);
}

/**
 * 初始化每个li的点击事件
 * @param li
 * @param flag
 */
function tabChangeInit(li, flag) {
    li.addEventListener("click", function () {
        tabChange(this, flag);
    });
}

/**
 * 具体执行tab切换的部分
 * @param li
 * @param flag
 */
function tabChange(li, flag) {
    for (var i = startTab; i < lis.length - endTab; i++) {
        lis[i].className = "";
    }
    for (var i = 0; i < contents.length; i++) {
        contents[i].style.display = "none";
    }
    contents[flag - 1].style.display = "block";
    li.className = "on";
    nowTab = flag;
}
