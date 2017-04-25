/**
 * Created by hust on 2016/11/27.
 */
var doors=Array.prototype.slice.call(document.getElementsByClassName("door"));
var num=doors.length;//统计一共有多少个门
var sliding=document.getElementById("sliding");
var open;//用来记录目前处于打开状态的门的索引，从0开始
(function  init() {
    doors[0].classList.add("open");//一开始默认第一扇门是打开的，使用classList暂时未考虑兼容性
    open=0;
    doors[0].style.left=0+"px";
    for(var i=1;i<num;i++){
        doors[i].style.left=(475+(i-1)*160)+"px";
    }
})();

sliding.onmouseover=function (e) {
    var target=e.target;//暂未考虑IE兼容性
    var index=doors.indexOf(target);//待打开的门的索引
    if ((index==open) || (index==-1)){
        return;
    }

    if (index>open){//如果需要打开的门在已经打开的门的右侧
        for(var i=open+1;i<=index;i++){
            doors[i].style.left=parseInt(doors[i].style.left)-315+"px";
        }
    }else {
        for(var i=index+1;i<=open;i++){//如果需要打开的门在已经打开的门左侧
            doors[i].style.left=parseInt(doors[i].style.left)+315+"px";
        }
    }
    open=index;
}



