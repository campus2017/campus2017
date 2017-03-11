window.onload=function () {
var Idiv=document.getElementsByClassName('layer');
    Idiv[0].style.display='none';
    var body=document.getElementsByClassName('body-bg');
    body[0].onmousedown=Pop;

}

function Pop() {
    var Idiv=document.getElementsByClassName('layer');
    Idiv[0].style.position='absolute';
   Idiv[0].style.left=(document.documentElement.clientWidth-parseInt(getStyle(Idiv[0],'width')))/2+'px';
   Idiv[0].style.top=(document.documentElement.clientHeight-parseInt(getStyle(Idiv[0],'height')))/2+"px";
    Idiv[0].style.display='block';
    var procbg = document.createElement("div");
    procbg.setAttribute("id","mybg");
    procbg.style.background = "#000000";
    procbg.style.width = "100%";
    procbg.style.height = "100%";
    procbg.style.position = "fixed";
    procbg.style.top = "0";
    procbg.style.left = "0";
    procbg.style.zIndex = "500";
    procbg.style.opacity = "0.6";
    procbg.style.filter = "Alpha(opacity=70)";
    document.body.appendChild(procbg);
     var timer;
    Refresh(Idiv[0]);
    var buttn_close=document.getElementsByClassName('second');
    buttn_close[0].onmousedown=function(){
        CloseDiv(Idiv[0],procbg);
    }

    function getStyle(obj,name){
        if (obj.currentStyle)
            return obj.currentStyle[name];
        else
            return getComputedStyle(obj,false)[name];
    }
    function CloseDiv(obj,remove) {
        obj.style.display='none';
        document.body.removeChild(remove);

    }


}
function Refresh() {
    var Idiv=document.getElementsByClassName('layer');
    Idiv[0].style.position='fixed';
    Idiv[0].style.left=(document.documentElement.clientWidth-parseInt(getStyle(Idiv[0],'width')))/2+'px';
    Idiv[0].style.top=(document.documentElement.clientHeight-parseInt(getStyle(Idiv[0],'height')))/2+"px";
    function getStyle(obj,name){
        if (obj.currentStyle)
            return obj.currentStyle[name];
        else
            return getComputedStyle(obj,false)[name];
    }
}
