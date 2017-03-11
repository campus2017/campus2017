window.onload=function () {
    var html=document.getElementsByTagName('html')[0];
    html.style.fontSize=document.documentElement.clientWidth/20+'px';//18.75
    window.addEventListener('resize', function() {
        html.style.fontSize=document.documentElement.clientWidth/20+'px';//18.75
    }, false);
    add_event(document.getElementsByClassName('look-comment')[0]);
    add_event(document.getElementsByClassName('look-map')[0]);
    add_event(document.getElementsByClassName('hotel-facility')[0]);
    var List_4_sec=document.getElementsByClassName('List-4')[0].childNodes[3];
    List_4_sec.addEventListener("touchstart",function(){
        alert('List_4_sec');
    },false);
    var List_5_right5=document.getElementsByClassName('List-5')[0].childNodes[3];
    add_event(List_5_right5.childNodes[3]);
    var List_12_right12=document.getElementsByClassName('List-12')[0].childNodes[3];
    add_event(List_12_right12.childNodes[3]);
    var List_13_right13=document.getElementsByClassName('List-13')[0].childNodes[3];
    add_event(List_13_right13.childNodes[3]);
    var List_14_right14=document.getElementsByClassName('List-14')[0].childNodes[3];
    add_event(List_14_right14.childNodes[3]);

    var List_6_rightcontent=document.getElementsByClassName('List-6')[0].childNodes[3];
    add_event(List_6_rightcontent.childNodes[3]);
    var List_7_rightcontent=document.getElementsByClassName('List-7')[0].childNodes[3];
    add_event(List_7_rightcontent.childNodes[3]);
    var List_8_rightcontent=document.getElementsByClassName('List-8')[0].childNodes[3];
    add_event(List_8_rightcontent.childNodes[3]);
    var List_9_rightcontent=document.getElementsByClassName('List-9')[0].childNodes[3];
    add_event(List_9_rightcontent.childNodes[3]);
    var List_10_rightcontent=document.getElementsByClassName('List-10')[0].childNodes[3];
    add_event(List_10_rightcontent.childNodes[3]);
    var List_11_p=document.getElementsByClassName('List-11')[0].childNodes[1];
    add_event(List_11_p);
    var List_15_right15=document.getElementsByClassName('List-15')[0].childNodes[3];
    add_event(List_15_right15.childNodes[1]);
    var List_16_right16=document.getElementsByClassName('List-16')[0].childNodes[3];
    add_event(List_16_right16.childNodes[1]);
}
function getStyle(obj,name){
    if (obj.currentStyle)
        return obj.currentStyle[name];
    else
        return getComputedStyle(obj,false)[name];
}
function add_event(obj) {
    obj.addEventListener("touchstart",function(){
        alert(obj.getAttribute('class'));
    },false);

}