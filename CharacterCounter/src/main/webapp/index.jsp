<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/19
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>CharacterCounter</title>
    <script type="text/javascript" src="/js/jquery.min.js"></script>
</head>
<script type="text/javascript">
    function redioselected($item)
    {
        if($item.value =="fileup")
        {
            document.getElementById("textinput").style.display="none";
            document.getElementById("textinput1").style.display="none";
            document.getElementById("textinput2").style.display="none";
            document.getElementById("upfile").style.display="";
        }
        if($item.value =="textinput")
        {
            document.getElementById("upfile").style.display="none";
            document.getElementById("textinput").style.display="";
            document.getElementById("textinput1").style.display="";
            document.getElementById("textinput2").style.display="";
        }

    };

    function uploadfile(){
        var form = new FormData(document.getElementById("tf"));

        $.ajax({
            url:"/charactercounter/fileup",
            type:"post",
            data:form,
            processData:false,
            contentType:false,
            success:function(data){
                loaddatas(data);
            },
            error:function(e){
                alert("错误！！");
            }
        });
    };

    function loaddatas(data)
    {

        document.getElementById("chCharacter").innerText = '';
        document.getElementById("enCharacter").innerText = '';
        document.getElementById("numberCharacter").innerText = '';
        document.getElementById("otherCharacter").innerText = '';
        document.getElementById("top1_key").innerText = '';
        document.getElementById("top1_value").innerText = '';
        document.getElementById("top2_key").innerText = '';
        document.getElementById("top2_value").innerText = '';
        document.getElementById("top3_key").innerText = '';
        document.getElementById("top3_value").innerText = '';


        document.getElementById("chCharacter").innerText = data["chCharacter"];
        document.getElementById("enCharacter").innerText = data["enCharacter"];
        document.getElementById("numberCharacter").innerText = data["numberCharacter"];
        document.getElementById("otherCharacter").innerText = data["otherCharacter"];

        if(data["top1_key"] != null) {
            document.getElementById("top1_key").innerText = data["top1_key"];
            document.getElementById("top1_value").innerText = data["top1_value"];
        }
        if(data["top2_key"] != null) {
            document.getElementById("top2_key").innerText = data["top2_key"];
            document.getElementById("top2_value").innerText = data["top2_value"];
        }
        if(data["top3_key"] != null) {
            document.getElementById("top3_key").innerText = data["top3_key"];
            document.getElementById("top3_value").innerText = data["top3_value"];
        }
    };

    function showfilename()
    {
        var file = $("#upfileform").val();
        var fileName = getFileName(file);

        function getFileName(o){
            var pos=o.lastIndexOf("\\");
            return o.substring(pos+1);
        }
        document.getElementById("filename").innerText =fileName ;
    };

    function count()
    {
        var text = document.getElementById("textinput").value;
        var  data = {fileUpload:text}
        $.ajax({
            type: "POST",
            url: "/charactercounter/count",
            data:data,
            success:function(data){
                loaddatas(data);
            }
        });
    };

    function toClear()
    {
        document.getElementById("textinput").value="";

    };
</script>
<body>
<h1>请选择一段文字</h1>
<form>
    <input type="radio" checked="checked" name="type" value="fileup" onclick="redioselected(this)"/>文件上传
    <input type="radio" name="type" value="textinput" onclick="redioselected(this)"/>文本输入
</form>
<div style="float:left;width:400px">
    <div style="float:left;width:300px">
        <div id="upfile">
        <label class="ui_button ui_button_primary" for="upfileform" style="background-color: aqua;display:inline">上传文件</label>
        <form id="tf"  style= "display:inline">
            <input  title="上传文件" type="file" name="file" id="upfileform"  style="position:absolute;clip:rect(0 0 0 0);display:inline" onchange="showfilename()" >&nbsp;&nbsp;&nbsp;<h4 style= "display:inline" id="filename"></h4>
            <input type="button" value="统计" style="background-color: aqua;width: 70px;display:inline;float: right;margin-right: -90px" onclick="uploadfile()">
        </form>
        </div>
        <textarea   id="textinput"  rows="4" name="fileUpload" style="overflow-x:hidden;overflow-y:scroll;display: none;width: 100%" ></textarea>
    </div>
    <div style="float: right;width: 100px">
        <button id="textinput1" style="background-color: aqua;display: none;width: 70px;margin-left: 20px;margin-bottom: 10px" onclick="count()">统计</button>
        <button id="textinput2" style="background-color: gold;display: none;width: 70px;margin-left: 20px;margin-top: 10px" onclick="toClear()">清除内容</button>
    </div>
</div>
<br/>
<br/>
<div style="margin-top: 90px">
<h3>统计内容的个数如下：</h3>
<table border="1" cellpadding="10" width="300px">
    <tr>
        <th>统计项</th>
        <th>个数</th>
    </tr>
    <tr>
        <td>英文字母</td>
        <td id="enCharacter"></td>
    </tr>
    <tr>
        <td>数字</td>
        <td id="numberCharacter"></td>
    </tr>
    <tr>
        <td>中文汉字</td>
        <td id="chCharacter"></td>
    </tr>
    <tr>
        <td>中英文标点符号</td>
        <td id="otherCharacter"></td>
    </tr>
</table>
<br/>
<h3>文字中出现频率最高的三个字是：</h3>
<table border="1" cellpadding="10" width="300px">
    <tr>
        <th>名称</th>
        <th>个数</th>
    </tr>
    <tr>
        <td id="top1_key"></td>
        <td id="top1_value"></td>
    </tr>
    <tr>
        <td id="top2_key"></td>
        <td id="top2_value"></td>
    </tr>
    <tr>
        <td id="top3_key"></td>
        <td id="top3_value"></td>
    </tr>
</table>
    </div>
</body>
</html>
