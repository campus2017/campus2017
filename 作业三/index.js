/**
 * Created by hust on 2016/11/28.
 */
function createXHR(){
    if (typeof XMLHttpRequest != "undefined"){
        return new XMLHttpRequest();
    } else if (typeof ActiveXObject != "undefined"){
        if (typeof arguments.callee.activeXString != "string"){
            var versions = ["MSXML2.XMLHttp.6.0", "MSXML2.XMLHttp.3.0",
                    "MSXML2.XMLHttp"],
                i, len;
            for (i=0,len=versions.length; i < len; i++){
                try {
                    new ActiveXObject(versions[i]);
                    arguments.callee.activeXString = versions[i];
                    break;
                } catch (ex){
                    //skip
                }
            }
        }
        return new ActiveXObject(arguments.callee.activeXString);
    } else {
        throw new Error("No XHR object available.");
    }
}

var data;//全局变量，用来存储解析后的json数据
var filtered_data1=null;//根据tab条件过滤后的数据,初始值为null
var filtered_data2=null;//根据搜索条件过滤后的数据，初始值为null
var table_data={
    page:1,//页码
    data:null
};//存储目前表格中正在展示的数据，可能为上面的三种数据之一，

var xhr = createXHR();
xhr.onreadystatechange = function(event){
    if (xhr.readyState == 4){
        if ((xhr.status >= 200 && xhr.status < 300) || xhr.status == 304){
            data=JSON.parse(xhr.responseText);
            table_data.data=data;
            fillTable();
        } else {
            alert("Request was unsuccessful: " + xhr.status);
        }
    }
};
xhr.open("get", "data.json", true);
xhr.send(null);

var content=document.getElementsByClassName("content")[0];
var tbody=content.getElementsByTagName("tbody")[0];


function fillTable() {
    var data=table_data.data;
    var index1=(table_data.page-1)*13;
    var index2=((data.length-index1)<13)?(data.length-1):(index1+13-1);
    var html="";
    for(var i=index1;i<=index2;i++){
        html+='<tr><td>'+dateFormat(data[i].time)+'</td><td>'+data[i].name+'</td><td>'+data[i].num+'</td><td>'+data[i].ecoin+'</td><td>'
            +(data[i].status?"兑换成功":"兑换失败")+'</td><td>'+data[i].info+'</td><td><span class="exchange">兑换信息</span></td></tr>';
    }
    tbody.innerHTML=html;
    updatePagenum();
}


var page_count=document.getElementById("page_count");
var page_num=document.getElementById("page_num");
function updatePagenum() {//更新页码
    var count=Math.ceil(table_data.data.length/13);
    page_count.innerHTML='共'+count+'页';
    page_num.innerHTML=table_data.page;

}

/*格式化日期*/
function dateFormat(time) {
    var date=new Date(time);
    var formatString=(date.getFullYear())+'-'+((date.getMonth()+1>=10)?(date.getMonth()+1):('0'+(date.getMonth()+1)))+'-'+ ((date.getDate()>=10)?date.getDate():('0'+date.getDate()));
    formatString+='</br>';
    formatString+=(date.getHours()>=10?date.getHours():('0'+date.getHours()))+':'+(date.getMinutes()>=10?date.getMinutes():('0'+date.getHours()))+':'+(date.getSeconds()>=10?date.getSeconds():('0'+date.getSeconds()));
    return formatString;
}

//切换tab操作
var tab=document.getElementsByClassName("tab")[0];
var tabs=tab.getElementsByTagName("li");
tab.onclick=function (e) {
    var target=e.target;
    if (target.className=="active"){
        return;
    }
    for (var i=0,len=tabs.length;i<len;i++){
        tabs[i].className="";
    }
    target.className="active";

    var _data=null;
    if (filtered_data2!=null){//如果已经选择了日期条件就叠加效果
        _data=filtered_data2;
    }else {
        _data=data;//如果没有选择日期就在原始的数据基础上执行过滤
    }

    if (target.id=="all"){

        filtered_data1=_data;

    }else if (target.id=="success"){
        filtered_data1=_data.filter(function(item){
            return (item.status==1);
        });

    }else {
        filtered_data1=_data.filter(function(item){
            return (item.status==0);
        });

    }

    table_data.data=filtered_data1;
    table_data.page=1;
    fillTable();//重新填充表格数据
}


/*按照时间搜索结果*/
var search=document.getElementsByClassName("search")[0];
var so=document.getElementById("so");
var dates=search.getElementsByTagName("input");

so.onclick=function () {
    var startDate=dates[0].value;
    var endDate=dates[1].value;
    if (endDate<startDate){
        alert("截止日期必须大于开始日期");
    }
    var time1=+new Date(startDate);
    var time2=+new Date(endDate)+86400000;//注意这里截止时间需要加上1天的时间，单位为ms

    var _data=null;
    if (filtered_data1!=null){//如果已经选择了tab条件就叠加效果
        _data=filtered_data1;
    }else {
        _data=data;//如果没有tab条件就在原始的数据基础上执行过滤
    }

    filtered_data2=_data.filter(function (item) {
        return ((item.time>=time1)&&(item.time<time2));
    });
    table_data.data=filtered_data2;
    table_data.page=1;
    fillTable();
    return false;//阻止点击按钮产生的页面刷新
}


/*页脚页面跳转控制*/
var footer=document.getElementsByClassName("footer")[0];
var to_page=footer.getElementsByTagName("input")[0];
var go_page=footer.getElementsByTagName("button")[0];

go_page.onclick=function () {

    var page_no=parseInt(to_page.value);
    if ((!page_no)|| page_no>(Math.ceil(table_data.data.length/13))){
        alert("请输入合适的页码");
        return;
    }
    table_data.page=page_no;
    fillTable();
}

var a=footer.getElementsByTagName("a");

a[0].onclick=function () {//跳转到首页
    table_data.page=1;
    fillTable();
    return false;//阻止a标签的默认跳转行为
}
a[1].onclick=function () {//跳转到尾页
    table_data.page=Math.ceil(table_data.data.length/13);
    fillTable();
    return false;//阻止a标签的默认跳转行为
}


