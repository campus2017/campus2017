/**
 * Created by hust on 2017/5/1.
 */

window.addEventListener('load',function () {
    var listScroll=new IScroll('#container',{
        mouseWheel:true,
        scrollX:false,
        scrollY:true,
        click:true
    });


},false);


var reason_list=document.getElementsByClassName('reason-list')[0];
var button=document.getElementsByTagName('button')[0];
//用户选择单选按钮事件
reason_list.addEventListener('click',function (event) {
    var target=event.target;
    console.log(target.tagName);

    if(target.tagName==='LI'){
        return;
    }
    var item=target.parentNode;

    var selecteditem=reason_list.getElementsByClassName('selected')[0];



    if(selecteditem===item){//如果点击的项目本来就被选中就不进行任何操作
        return;
    }
    if (typeof selecteditem!= 'undefined'){
        selecteditem.classList.remove('selected');
        var selectedicon=selecteditem.getElementsByClassName('iconfont')[0];
        selectedicon.innerHTML='&#xe608;'
    }

    var icon=item.getElementsByClassName('iconfont')[0];

    item.classList.add('selected');
    icon.innerHTML='&#xe60a;';

    //使按钮可点击
    button.style.opacity=1.0;
    button.disabled=false;


},false);



