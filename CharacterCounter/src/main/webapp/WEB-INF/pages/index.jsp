<%--
  Created by IntelliJ IDEA.
  User: muhongfen
  Date: 17/1/6
  Time: 上午11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/uploadify/uploadify.css" >
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>字符统计</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>CharacterCounter</title>
</head>
<body style="margin: 1cm">
<label class="label label-info" style="font-size: 100%">选择一个文件或者输入一段文字</label><br/>
<div id="checkbox" style="margin-top: 5px">
    <label class="checkbox-inline">
        <input type="radio" name="optionsRadiosinline" id="optionsRadios1" value="option1" onclick="radio1click()" checked> 文件上传
    </label>
    <label class="checkbox-inline">
        <input type="radio" name="optionsRadiosinline" id="optionsRadios2"  value="option2" onclick="radio2click()"> 文本输入
    </label>
</div>
<div id="fileUpload" style="overflow:hidden">
   <input type="file" name="file_upload" id="file_upload" style="float:left"/>
    <label id="fileNameLabel" style="float:left;margin-top: -40px;margin-left: 130px"> </label>
    <button id="fileCount" onclick="fileCount()" style="float:left;margin-top:-40px;margin-left: 130px">统计</button>
<br/>
</div>

<div id="stringInput" style="overflow: hidden">
    <form name="strFrom" style="float:left">
        <textarea id="inputString" rows="3"  style="float:left;width:400px"></textarea>
        <input type="reset" id="cleanStr" style="float:left;margin-left:12px;"/>
    </form>
    <button id="strCount" onclick="strCount()" style="float:left; margin-left:-43px;margin-top:30px;">统计</button>

</div>

<div id = "result"></div>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/uploadify/jquery.uploadify.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/uploadify/jquery.json.js"></script>
<script >
    $(document).ready(function() {
        $("#stringInput").css('display','None');
        $("#fileUpload").css('display', 'Block');
        $("#file_upload").uploadify({
            'buttonText' : '请选择',
            'height' : 30,
            'swf' : '<%=request.getContextPath()%>/uploadify/uploadify.swf',
            'uploader' : '/CharacterCounter/uploadFile',
            'width' : 120,
            'auto':true,
            'fileObjName'   : 'file',
            'onUploadSuccess' : function(file, data, response) {
                $("#fileNameLabel").text(JSON.parse(data)['data'])
                $("#fileCount").css('margin-left','250px');
            }

        });
    });
    function radio1click(){

        $("#stringInput").css('display','None');
        $("#fileUpload").css('display', 'Block');
    }
    function radio2click() {

        $("#fileUpload").css('display', 'None');
        $("#stringInput").css('display','Block');
    }
    function fileCount(){
        var url = "http://localhost:8080/CharacterCounter/fileCount?filename="+$("#fileNameLabel").text();
        url = encodeURI(encodeURI(url));
        $.get(url,function(data){

            var datas = data['data'];
            var count = datas[0];
            var count_result = "</br><label class='label label-success'style='font-size: 100%'>字符统计</label></br><table class='table table-bordered' style='width: 50%'> <tr><th style='width: 50%'>中文汉字</th><th>"+count['中文汉字']+"</th></tr><tr><th>英文字母</th><th>"+count['英文字母']+"</th></tr><tr><th>数字</th><th>"+count['' +
                '数字']+"</th></tr><tr><th>中英文标点符号</th><th>"+count['中英文标点符号']+"</th></tr></table>";
            var most  = datas[1];
            var most_result = "</br><label class='label label-success'style='font-size: 100%'>出现最多的三个字符</label></br><table class='table table-bordered' style='width: 50%'> <tr><th style='width: 50%'>"+most['0'].split(":")[0]+"</th><th>"+most['0'].split(":")[1]+"</th></tr><tr><th>" +
                ""+most["1"].split(":")[0]+"</th><th>"+most["1"].split(":")[1]+"</th></tr><tr><th>"+most["2"].split(":")[0]+"</th><th>"+most["2"].split(":")[1]+"</th></tr></table>";
            console.log(count_result);
            $("#result").html(count_result+most_result);

        },'json');
    }

    function strCount(){
        var url = "http://localhost:8080/CharacterCounter/stringCount";
        url = encodeURI(encodeURI(url));
        var param = {str:$("#inputString").val()};
        console.log(param);
        $.post(url,param,function(data){
            var datas = data['data'];
            var count = datas[0];
            var count_result = "</br><label class='label label-success'style='font-size: 100%'>字符统计</label></br><table class='table table-bordered' style='width: 50%'> <tr><th style='width: 50%'>中文汉字</th><th>"+count['中文汉字']+"</th></tr><tr><th>英文字母</th><th>"+count['英文字母']+"</th></tr><tr><th>数字</th><th>"+count['' +
                '数字']+"</th></tr><tr><th>中英文标点符号</th><th>"+count['中英文标点符号']+"</th></tr></table>";
            var most  = datas[1];
            var most_result = "</br><label class='label label-success'style='font-size: 100%'>出现最多的三个字符</label></br><table class='table table-bordered' style='width: 50%'> <tr><th style='width: 50%'>"+most['0'].split(":")[0]+"</th><th>"+most['0'].split(":")[1]+"</th></tr><tr><th>" +
                ""+most["1"].split(":")[0]+"</th><th>"+most["1"].split(":")[1]+"</th></tr><tr><th>"+most["2"].split(":")[0]+"</th><th>"+most["2"].split(":")[1]+"</th></tr></table>";
            console.log(count_result);
            $("#result").html(count_result+most_result);
        },'json');

    }

</script>

</body>
</html>
