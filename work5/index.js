
(function() {
    var tabTitle = document.getElementsByClassName("tabTitle")[0].getElementsByTagName("li");
    for (var i = 1, len = tabTitle.length - 1; i < len; i++) {
        listen(tabTitle[i], "click", function () {
            document.getElementsByClassName("curDate")[0].className = "";
            this.className = "curDate";
        });
    }
})();

function listen(ele, type, handler){
    if(document.addEventListener){
        ele.addEventListener(type, handler, false);
    }else if(document.attachEvent){
        ele.attachEvent('on' + type, handler);
    }else{
        ele['on' + type] = handler;
    }
}