window.onload = function () {
    // body...
    var time = document.getElementsByClassName('col1');
    var name = document.getElementsByClassName('col2');
    var num = document.getElementsByClassName('col3');
    var ecoin = document.getElementsByClassName('col4');
    var state = document.getElementsByClassName('col5');
    var mes = document.getElementsByClassName('col6');
    var contentlis = document.getElementsByClassName('content-li');
    var tablis = document.getElementsByClassName('tab-li');
    var tabUl = document.getElementById('tab');
    var allBtn = document.getElementById('all');
    var successBtn = document.getElementById('success');
    var loseBtn = document.getElementById('lose');
    var btns = document.getElementsByClassName('k');
    var changeMes = document.getElementsByClassName('changeMes');
    var searchBtn = document.getElementById('searchBtn');
    var reset = document.getElementById("reset");
    //三个tab标签点击时，背景颜色变换
    function tabchange1(){
        tabUl.onclick = function(){
            //利用事件委托
            var e = event || window.event;
            var tar = e.target||e.srcElement;
            var index = Array.prototype.indexOf.call(this.children,tar);//获取当下点击的li元素的索引
            for (var j = 0; j < tablis.length; j++) {
                if (tablis[j].className.indexOf("off-state")==-1) {
                    tablis[j].className = tablis[j].className+" off-state";
                }
                contentlis[j].style.display = "none";
                contentlis[index].style.display = "block";
            }
            tar.className = tar.className.replace("off-state","on-state");
            var whitespace = document.getElementById('white-space');
            whitespace.style.left = 914-87*index+"px";
        }
    }

    var allMessages = [];//所有的信息
    var nowMessages = [];//现有的信息
    function getJSON(flag){
        var xmlHttp = window.XMLHttpRequest?new XMLHttpRequest():new ActiveXobject("Microsoft.xmlHttp");

        xmlHttp.open("get","data.js?random="+Math.random(),true);
        xmlHttp.onreadystatechange = function(){
            if (xmlHttp.readyState == 4 && xmlHttp.status==200) {
                var result = xmlHttp.responseText;
                allMessages = eval("("+result+")");
                if(nowMessages.length==0){nowMessages = allMessages;}
                if (flag=="success") {
                    nowMessages = allMessages.filter(function(curEle, index, messages){
                        return curEle.state=="兑换成功";
                    });

                    fill(nowMessages);
                }else if (flag=="lose") {
                    nowMessages = allMessages.filter(function(curEle, index, messages){
                        return curEle.state=="兑换失败";
                    });
                    fill(nowMessages);
                }else{
                    fill(allMessages);
                }
            }
        }
        xmlHttp.send();
    }
    //验证日期输入是否合法
    function isValid(s) {
        var reg = /^(\d{4})-(\d{2})-(\d{2})$/;
        var f = reg.test(s);
        if(f==false){
            return false;
        }else{
            var ss = s.split("-");
            if(ss[0]>2000&&ss[0]<2018 && ss[1]>0&&ss[1]<=12 && ss[2]>=0&&ss[2]<=60){
                return true;
            }else{
                return false;
            }
        }
    }
    //删除联需要的class名
    function deleteClassName(arr, name){
        for (var i = 0; i < arr.length; i++) {
            var s = arr[i].className;
            arr[i].className = s.replace(name,"");
        }
    }
    //字段全清楚
    function clearField() {
        for(var i=0; i<time.length-1; i++){
            mes[i+1].innerHTML =  state[i+1].innerHTML=ecoin[i+1].innerHTML =num[i+1].innerHTML =name[i+1].innerHTML = time[i+1].innerHTML ="";
        }
    }
    //查找字段
    function searchField(searchMes) {
            var t1 = document.getElementById('date1').value;
            var t2 = document.getElementById('date2').value;
            var d1 = Date.parse(new Date(t1));
            var d2 = Date.parse(new Date(t2));
            if(isValid(t1)==false || isValid(t2)==false || d2<=d1){
                alert("请输入正确的时间");
            }
        searchMes = searchMes.filter(function(curEle, index, searchMes){
                var d = Date.parse(new Date(curEle.time));
                console.log("d="+d+" d1="+d1+" d2="+d2);
                return d>=d1 && d<=d2;
            });

            return searchMes;
    }
    //填充字段
    function fill(m){
        var len = m.length;
        for(var i=0; i<len; i++){
            var arr = m[i];
            time[i+1].innerHTML = arr.time;
            name[i+1].innerHTML = arr.name;
            num[i+1].innerHTML = arr.num;
            ecoin[i+1].innerHTML =arr.Ecoin;
            state[i+1].innerHTML =arr.state;
            mes[i+1].innerHTML = arr.mes;

        }
        //messages更新后，兑换信息按键相应的添加或删除
        for(var i=0; i<len; i++){
            changeMes[i].style.display="block";
        }
        for(var i=len; i<time.length-1; i++){
            changeMes[i].style.display="none";
        }
    }
//添加按钮们的点击事件
    success.onclick = function(){
        clearField();
        getJSON("success");
        deleteClassName(btns,"choose");
        this.className = this.className+" choose";
    }
    lose.onclick = function(){
        clearField();
        getJSON("lose");
        deleteClassName(btns,"choose");
        this.className = this.className+" choose";
    }
    all.onclick =reset.onclick= function(){
        getJSON();
        deleteClassName(btns,"choose");
        this.className = this.className+" choose";
    }
    searchBtn.onclick = function () {
        clearField();
        nowMessages =  searchField(nowMessages);
        fill(nowMessages);
    }

    tabchange1();
    getJSON();
}