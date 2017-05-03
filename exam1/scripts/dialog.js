/**
 * Created by hust on 2017/5/3.
 */

var submit=document.getElementsByClassName('submit')[0];
var dialog=document.getElementsByClassName('dialog')[0];
var dialog_close=document.getElementsByClassName('dialog-close')[0];
var mask=null;

submit.addEventListener('click',function () {
    mask=document.createElement('div');
    mask.id='mask';
    document.body.appendChild(mask);
    dialog.style.display="block";
    dialog.style.top="50%";

},false);

dialog_close.addEventListener('click',function () {
    dialog.style.top="200%";
    document.body.removeChild(mask);
},false);




