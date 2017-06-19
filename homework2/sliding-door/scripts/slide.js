(function () {

    function raf(fun) {
        var raf = window.requestAnimationFrame || window.mozRequestAnimationFrame || window.webkitRequestAnimationFrame || window.msRequestAnimationFrame;
        if(raf){
            raf(function () {
                fun();
            });
        }else {
            setTimeout(function () {
                fun();
            }, 16.7);
        }
    }

    Array.prototype.remove=function (val) {
        var index = this.indexOf(val);
        if(index >= 0){
            this.splice(index, 1);
        }
    };

    var SlideDoor = function (element, slideWidth, org, position, duration) {
        this.element = element;
        this.slideWidth=slideWidth;
        this.org = org;
        this.element.style.left = org + '%';
        if(position === 0){
            this.left = org;
            this.right = org + slideWidth;
        }else {
            this.left = org - slideWidth;
            this.right = org;
        }
        this.duration = duration;
        var that = this;
        this.__slide = function (step) {
            return that._slide(step);
        }
    };

    SlideDoor.prototype.slide = function (direction) {
        var that = this;
        that.direction = direction;
        if(animation.list.indexOf(that.__slide) === -1){
            that.__slide.startT = new Date().getTime();
            animation.list.push(that.__slide);
        }
        animation.play(0);
    };

    SlideDoor.prototype._slide = function (step) {
        var dir = this.direction;
        var edge = (dir === 0)? this.left : this.right;
        var _step = (dir === 0)? -step : step;
        var lastP = this.nowP || this.org;
        if(lastP === edge){
            return false;
        }

        var nowP = lastP + (_step / this.duration) * this.slideWidth;
        if((dir === 0 && nowP <= edge) || (dir === 1 && nowP >= edge)){
            this.element.style.left = edge + '%';
            this.nowP = edge;
            return false;
        }
        this.element.style.left = nowP + '%';
        this.nowP = nowP;
        return true;
    };

    var animation={

        list:[],

        play: function (flag) {
            var that = this;
            if(flag === 0 && that.playing == 1){
                return;
            }
            that.playing = 1;

            if(that.list.length === 0){
                this.playing = 0;
                return;
            }

            var nowT = new Date().getTime();
            var lastT = that.lastT || nowT;
            var step, result;
            that.list.forEach(function (v, i) {
                step = Math.min(nowT - lastT, nowT - v.startT);
                result = v(step);
                if(!result) {
                    that.list.splice(i, 1);
                }
            });
            that.lastT = nowT;

            raf(function () {
                that.play(1);
            });
        }
    };

    var _doors=document.getElementsByClassName('door');

    var doors=[
        new SlideDoor(_doors[0], 33.33, 0, 0, 300),
        new SlideDoor(_doors[1], 33.33, 50, 1, 300),
        new SlideDoor(_doors[2], 33.33, 66.67, 1, 300),
        new SlideDoor(_doors[3], 33.33, 83.33, 1, 300)
    ];

    doors.forEach(function (v, i) {
        (function (index) {
            v.element.onmouseover = function () {
                for (var j=0; j<4; j++) {
                    if(j <= index){
                        doors[j].slide(0);
                    }else{
                        doors[j].slide(1);
                    }
                }
            };
        })(i);
    });

})();