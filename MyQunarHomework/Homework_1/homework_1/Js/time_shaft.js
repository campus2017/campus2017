function time_shaft() {
    /* 清除之前的状态*/
    var left_1=document.getElementsByClassName('left_1')[0];
    left_1.style.display='none';
    var center_1=document.getElementsByClassName('center_1')[0];
    center_1.style.display='none';
    var right_1=document.getElementsByClassName('right_1')[0];
    right_1.style.display='none';
    var icon=document.getElementsByClassName('icon');
    icon[0].style.display='none';
    icon[1].style.display='none';
    icon[2].style.display='none';
    /*获取当前时间*/
    var date=new Date();/*date.getFullYear()获取年 date.getMonth()获取月0到11。date.getDate()获取日期*/
    var StrDate=date.getFullYear()+'.'+date.getMonth()+1+'.'+date.getDate();
var time=document.getElementsByClassName('time');
    for(var i=0;i<time.length;i++){
            /*处理当前状态*/
            var TargetDate=time[i].innerHTML;
            if(TargetDate==StrDate){
              var bg=time[i].parentNode.parentNode.childNodes[3];
             bg.style.display='block'
             var icon=time[i].parentNode.parentNode.childNodes[5];
           /* alert(icon.getAttribute('class'));*/
           icon.style.display='block';}

    }
}
