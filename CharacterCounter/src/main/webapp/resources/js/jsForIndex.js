/*
    通过id获取DOM对象
 */
function $(id){
    return document.getElementById(id);
}
/*
    当点击选项时，显示一个(display : block)，隐藏另一个(display : none)。
 */
function changeType(){
    var radios = document.getElementsByName("type");
    /*
        检查是哪个选项被选择了
     */
    for(var i = 0; i < radios.length; i++){
        if(radios[i].checked){
            break;
        }
    }
    if(i == 0){
        $("formDiv1").style.display = "block";
        $("formDiv2").style.display = "none";
    }
    else{
        $("formDiv1").style.display = "none";
        $("formDiv2").style.display = "block";
    }
}
function addAction(){
    /*
        传递点击事件给docFile
     */
    $("btn").onclick=function(){
        $("docFile").click();
    }
    /*
        this = docFile
        获取上传文件名，并显示出来，这里只是模拟上传组件，文件名filePath标签对于后端没什么用处
     */
    $("docFile").onchange=function(){
        var str = this.value.substring(this.value.lastIndexOf("\\") + 1);
        $("filePath").innerHTML = str;
    }
    $("selectFile").onchange = changeType;
    $("selectContent").onchange = changeType;
    /*
        点击统计按钮，发起Ajax请求
     */
    $("submitFile").onclick = function(){
        request(document.forms[0]);
    }
    $("submitContent").onclick = function(){
        request(document.forms[1]);
    }
}
/*
    创建xhr请求对象，考虑兼容性
 */
function createXHR(){
    if(typeof XMLHttpRequest != "undefined"){
        return new XMLHttpRequest();
    }
    else if(typeof ActiveXObject != "undefined"){
        var versions = ["MSXML2.XMLHttp.6.0", "MSXML2.XMLHttp.3.0", "MSXML2.XMLHttp"], i, len;
        for(i = 0; i < versions.length; i++){
            try{
                new ActiveXObject(versions[i]);
                arguments.callee.activeXString = versions[i];
                break;
            }
            catch(ex){

            }
        }
        return new ActiveXObject(arguments.callee.activeXString);
    }
    else{
        throw new Error("No XHR object available.");
    }
}
/*
    利用FormData对表单直接序列化，发送Ajax请求
 */
function request(form){
    var xhr = createXHR();
    /*函数处理句柄*/
    xhr.onreadystatechange = function(){
        /*处理完成*/
        if(xhr.readyState == 4){
            /*当请求正确，或者文件没有修改(304)*/
            if(xhr.status >= 200 && xhr.status < 300 || xhr.status == 304){
                /*获得xml文件，解析为xml对象*/
                xmlDoc = xhr.responseXML;
                /*利用xml中的数据，分别填充表格*/
                var key = "com.baobao.domain.Entry";
                var item = 0;
                var entry = xmlDoc.getElementsByTagName(key)[item++];
                $("alpha").innerHTML = entry.getElementsByTagName("cnt")[0].innerHTML;
                entry = xmlDoc.getElementsByTagName(key)[item++];
                $("digit").innerHTML = entry.getElementsByTagName("cnt")[0].innerHTML;
                entry = xmlDoc.getElementsByTagName(key)[item++];
                $("chinese").innerHTML = entry.getElementsByTagName("cnt")[0].innerHTML;
                entry = xmlDoc.getElementsByTagName(key)[item++];
                $("punctuation").innerHTML = entry.getElementsByTagName("cnt")[0].innerHTML;

                entry = xmlDoc.getElementsByTagName(key)[item++];
                $("first_key").innerHTML = entry.getElementsByTagName("str")[0].innerHTML;
                $("first_value").innerHTML = entry.getElementsByTagName("cnt")[0].innerHTML;

                entry = xmlDoc.getElementsByTagName(key)[item++];
                $("second_key").innerHTML = entry.getElementsByTagName("str")[0].innerHTML;
                $("second_value").innerHTML = entry.getElementsByTagName("cnt")[0].innerHTML;

                entry = xmlDoc.getElementsByTagName(key)[item++];
                $("third_key").innerHTML = entry.getElementsByTagName("str")[0].innerHTML;
                $("third_value").innerHTML = entry.getElementsByTagName("cnt")[0].innerHTML;
            }
            else{
                /*请求出错*/
                alert("request fail....");
            }
        }
    };
    /*异步请求，不会出错*/
    xhr.open("post", form.action, true);
    var formData = new FormData(form);
    xhr.send(formData);
}
/*页面加载完成后，初始化*/
window.onload = addAction