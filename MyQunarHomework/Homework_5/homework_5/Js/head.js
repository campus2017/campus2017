window.onload=function () {
    var head=document.getElementsByClassName('head')[0];
    var ul=head.childNodes[1];
    var li=ul.childNodes;
    var body=document.getElementsByClassName('body')[0];
    var body1=document.getElementsByClassName('body1')[0];
    var body2=document.getElementsByClassName('body2')[0];
    var body3=document.getElementsByClassName('body3')[0];
    var body4=document.getElementsByClassName('body4')[0];
    var body5=document.getElementsByClassName('body5')[0];
    var body6=document.getElementsByClassName('body6')[0];
    var body_arr=[body,body1,body2,body3,body4,body5,body6];
    for (var i=3;i<=15;i=i+2) {
        li[i].i=i;
        li[i].onclick=function(){
            delete_bottom(this,li,body_arr);

        }
    }

}
function delete_bottom(obj,arr,body_arr) {
    for (var i=0;i<body_arr.length;i++) {
        body_arr[i].style.display='none';
    }
    switch (obj.i) {
        case 9:body_arr[0].style.display='block';break;
        case 3:body_arr[1].style.display='block';break;
        case 5:body_arr[2].style.display='block';break;
        case 7:body_arr[3].style.display='block';break;
        case 11:body_arr[4].style.display='block';break;
        case 13:body_arr[5].style.display='block';break;
        case 15:body_arr[6].style.display='block';break;
        default:alert('ok');
    }
    for (var i=3;i<=15;i=i+2) {
      arr[i].style.borderBottom='2px solid #268fc5';
    }
    obj.style.borderBottom=0;

}
