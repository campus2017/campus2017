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
    };

    SlideDoor.prototype.slide = function (direction) {
        var that = this;
        that.direction = direction;
        if(animation.list.indexOf(that) === -1){
            that.startT = new Date().getTime();
            animation.list.push(that);
        }
        animation.play();
    };

    SlideDoor.prototype.slideStep = function (step) {
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

        playing: 0,

        play: function () {
            var that = this;
            if(that.playing === 1){
                return;
            }
            that.playing = 1;
            that.lastT = new Date().getTime();

            (function _play() {
                if(that.list.length === 0){
                    that.playing = 0;
                    return;
                }
                var nowT = new Date().getTime();
                that.list.forEach(function (e, i) {
                    if(!e.slideStep(Math.min(nowT - that.lastT, nowT - e.startT))) {
                        that.list.splice(i, 1);
                    }
                });
                that.lastT = nowT;
                raf(_play);
            })();
        }
    };

    var _doors=document.getElementsByClassName('door');

    var doors=[
        new SlideDoor(_doors[0], 33.33, 0, 0, 300),
        new SlideDoor(_doors[1], 33.33, 50, 1, 300),
        new SlideDoor(_doors[2], 33.33, 66.67, 1, 300),
        new SlideDoor(_doors[3], 33.33, 83.33, 1, 300)
    ];

    doors.forEach(function (e, i) {
        (function (index) {
            e.element.onmouseover = function () {
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