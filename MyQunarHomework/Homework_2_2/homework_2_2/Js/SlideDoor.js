window.onload=function () {
    var door1=document.getElementsByClassName('door1');
   var door2=document.getElementsByClassName('door2');
    var door3=document.getElementsByClassName('door3');
    var door4=document.getElementsByClassName('door4');
    var timer;
    door1[0].flag=1;
   door2[0].flag=0;
    door3[0].flag=0;
    door4[0].flag=0;

    door1[0].onmouseover=function(){
        slideDoor(this,0,timer, door1[0].flag,door3[0],door4[0],door2[0]);
    }
    door2[0].onmouseover=function(){
       slideDoor(this,-320,timer, door2[0].flag,door3[0],door4[0]);
                  }
    door3[0].onmouseover=function(){
       slideDoor(this,318,timer, door3[0].flag,door2[0],door4[0]);
    }
    door4[0].onmouseover=function(){
      slideDoor(this,488,timer, door4[0].flag,door2[0],door3[0]);
    }
}
function slideDoor(obj,target,timer,flag,door2,door3,door5) {
    var number = obj.className.replace(/[^0-9]/ig,"");
if(flag==0){
    if (timer) {
        clearInterval(timer);
    }
    timer=setInterval(function(){
        if(number==2) {
            var curr = parseInt(getStyle(obj, 'left'));
            var speed = (target - curr) / 8;
            speed = speed > 0 ? Math.ceil(speed) : Math.floor(speed);
            obj.style.left = curr + speed + 'px';
            if( parseInt(obj.style.left)==target) {
                clearInterval(timer);
                obj.flag=1;
            }
        }//if
        if(number==3) {
            sub_flag=0;
            var curr = parseInt(getStyle(obj, 'left'));
            var speed = (target - curr) / 8;
            speed = speed > 0 ? Math.ceil(speed) : Math.floor(speed);
            obj.style.left = curr + speed + 'px';
            if(  parseInt(obj.style.left)==target) {
                obj.flag=1;
                sub_flag= sub_flag+1;
            }

            var curr2 = parseInt(getStyle(door2, 'left'));
            var speed2 = (-320 - curr2) / 8;
            speed2 = speed2 > 0 ? Math.ceil(speed2) : Math.floor(speed2);
            door2.style.left = curr2 + speed2 + 'px'
            if( parseInt(door2.style.left)==-320) {
                door2.flag=1;
                sub_flag= sub_flag+1;
            }
            if (sub_flag==2) {
                clearInterval(timer);
            }
        }
        if(number==4) {
            sub_flag=0;
            var curr = parseInt(getStyle(obj, 'left'));
            var speed = (target - curr) / 8;
            speed = speed > 0 ? Math.ceil(speed) : Math.floor(speed);
            obj.style.left = curr + speed + 'px';
            if( parseInt(obj.style.left)==target) {
               obj.flag=1;
                sub_flag=sub_flag+1;
            }

            var curr2 = parseInt(getStyle(door2, 'left'));
            var speed2 = (-320 - curr2) / 8;
            speed2 = speed2 > 0 ? Math.ceil(speed2) : Math.floor(speed2);
            door2.style.left = curr2 + speed2 + 'px'
            if( parseInt(door2.style.left)==-320) {
                door2.flag=1;
                sub_flag=sub_flag+1;
            }

            var curr3 = parseInt(getStyle(door3, 'left'));
            var speed3 = (318 - curr3) / 8;
            speed3 = speed3 > 0 ? Math.ceil(speed3) : Math.floor(speed3);
            door3.style.left = curr3 + speed3+ 'px'
            if( parseInt(door3.style.left)==318) {
                door3.flag=1;
                sub_flag=sub_flag+1;
            }
            if (sub_flag==3) {
                clearInterval(timer);
            }

        }

    },10);


    function getStyle(obj,name){
        if (obj.currentStyle)
            return obj.currentStyle[name];
        else
            return getComputedStyle(obj,false)[name];

    }
}
else {
    var number = obj.className.replace(/[^0-9]/ig,"");
    if (timer) {
        clearInterval(timer);
    }
    timer=setInterval(function(){
        if(number==2) {
            sub_flag=0;
            target3=635;
            target4=795;
            var curr3 = parseInt(getStyle(door2, 'left'));
            var speed3 = (target3- curr3) / 8;
            speed3 = speed3> 0 ? Math.ceil(speed3) : Math.floor(speed3);
            door2.style.left = curr3 + speed3+ 'px';
            if(parseInt(door2.style.left)==target3) {
                sub_flag=sub_flag+1;
                door2.flag=0;
            }
            var curr4 = parseInt(getStyle(door3, 'left'));
            var speed4 = (target4- curr4) / 8;
            speed4 = speed4> 0 ? Math.ceil(speed4) : Math.floor(speed4);
            door3.style.left = curr4 + speed4+ 'px';
            if(parseInt(door3.style.left)==target4) {
                door3.flag=0;
                sub_flag=sub_flag+1;

            }
               if(sub_flag==2) {
                   clearInterval(timer);
               }
        }//if
        if (number==3) {
            target4=795;
            var curr4 = parseInt(getStyle(door3, 'left'));
            var speed4 = (target4- curr4) / 8;
            speed4 = speed4> 0 ? Math.ceil(speed4) : Math.floor(speed4);
            door3.style.left = curr4 + speed4+ 'px';
            if(parseInt(door3.style.left)==target4) {
                clearInterval(timer);
                door3.flag=0;
            }

        }
        if (number==1) {
            sub_flag=0;
            target2=-5;
            target3=635;
            target4=795;
            var curr3 = parseInt(getStyle(door2, 'left'));
            var speed3 = (target3- curr3) / 8;
            speed3 = speed3> 0 ? Math.ceil(speed3) : Math.floor(speed3);
            door2.style.left = curr3 + speed3+ 'px';
            if(parseInt(door2.style.left)==target3) {
                sub_flag=sub_flag+1;
                door2.flag=0;
            }
            var curr4 = parseInt(getStyle(door3, 'left'));
            var speed4 = (target4- curr4) / 8;
            speed4 = speed4> 0 ? Math.ceil(speed4) : Math.floor(speed4);
            door3.style.left = curr4 + speed4+ 'px';
            if(parseInt(door3.style.left)==target4) {
                door3.flag=0;
                sub_flag=sub_flag+1;

            }
            var curr5 = parseInt(getStyle(door5, 'left'));
            var speed5 = (target2- curr5) / 8;
            speed5 = speed5> 0 ? Math.ceil(speed5) : Math.floor(speed5);
            door5.style.left = curr5 + speed5+ 'px';
            if(parseInt(door5.style.left)==target2) {
                door5.flag=0;
                sub_flag=sub_flag+1;

            }
            if(sub_flag==3) {
                clearInterval(timer);
            }

        }

    },10);
    function getStyle(obj,name){
        if (obj.currentStyle)
            return obj.currentStyle[name];
        else
            return getComputedStyle(obj,false)[name];

    }
}

}
