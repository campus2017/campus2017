<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Character Counter</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<h4>请选择一段文字</h4>
<div>
    <input type="radio" name="choose" id="choose1" checked onclick="choose_option('SelectB','SelectA')">
    <label for="choose1">文件上传</label>&ensp;&ensp;&ensp;
    <input type="radio" name="choose" id="choose2" onclick="choose_option('SelectA','SelectB')">
    <label for="choose2">文本输入</label>
    <div id="SelectA">
        <form id="uploadForm" enctype="multipart/form-data">
            <label for="textFile">上传文件</label>
            <input type="file" id="textFile" name="textFile" onchange="fileUp()">
            <span id="filename"></span>
            <input type="button" class="submit" value="统计" onclick="uploadFile()">
        </form>
    </div>
    <div id="SelectB">
        <textarea name="textArea" id="textArea" cols="43" rows="5" placeholder="请在此输入文本内容"></textarea>
        <input type="button" class="position submit" value="统计" onclick="submitText()">
        <input type="reset" class="position1 submit" value="清空内容" onclick="clearText()">
    </div>
    <table>
        <caption>各统计内容的个数如下:</caption>
        <thead>
        <tr>
            <th>统计项</th>
            <th>个数</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>英文字母</td>
            <td id="enCount"></td>
        </tr>
        <tr>
            <td>数字</td>
            <td id="numCount"></td>
        </tr>
        <tr>
            <td>中文汉字</td>
            <td id="chCount"></td>
        </tr>
        <tr>
            <td>中英文标点符号</td>
            <td id="punctCount"></td>
        </tr>
        </tbody>
    </table>
    <table>
        <caption>文字中出现频率最高的三个字是:</caption>
        <thead>
        <tr>
            <th>名称</th>
            <th>个数</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td id="top1Char"></td>
            <td id="top1Count"></td>
        </tr>
        <tr>
            <td id="top2Char"></td>
            <td id="top2Count"></td>
        </tr>
        <tr>
            <td id="top3Char"></td>
            <td id="top3Count"></td>
        </tr>
        </tbody>
    </table>

</div>
<script type="text/javascript">
    function choose_option(div1, div2) {
        document.getElementById(div1).style.display = "none";
        document.getElementById(div2).style.display = "block";
    }
    function fileUp() {
        var filename = document.getElementById("textFile").value;
        var arr = filename.split("\\");
        filename = arr.pop();
        document.getElementById("filename").innerHTML = filename;
    }
    function clearText() {
        document.getElementById("textArea").value = "";
    }
</script>
<script type="text/javascript">
    function submitText() {
        var text = $("#textArea").val();
        $.ajax({
            url: "/parseText",
            type: "post",
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            data: text,
            success: function (data) {
                console.log(data);
                $("#enCount").html(data.enCount);
                $("#numCount").html(data.numCount);
                $("#chCount").html(data.chCount);
                $("#punctCount").html(data.punctCount);
                $("#top1Char").html(data.top1Char);
                $("#top1Count").html(data.top1Count);
                $("#top2Char").html(data.top2Char);
                $("#top2Count").html(data.top2Count);
                $("#top3Char").html(data.top3Char);
                $("#top3Count").html(data.top3Count);
            },
            error: function (res) {
                console.log(res.responseText);
                console.log('出错了？？？');
            }
        });
    }
</script>
<script type="text/javascript">
    function uploadFile() {
        $.ajax({
            url: '/parseFile',
            type: 'POST',
            cache: false,
            data: new FormData($('#uploadForm')[0]),
            processData: false,
            contentType: false,
            success: function (data) {
                console.log(data);
                $("#enCount").html(data.enCount);
                $("#numCount").html(data.numCount);
                $("#chCount").html(data.chCount);
                $("#punctCount").html(data.punctCount);
                $("#top1Char").html(data.top1Char);
                $("#top1Count").html(data.top1Count);
                $("#top2Char").html(data.top2Char);
                $("#top2Count").html(data.top2Count);
                $("#top3Char").html(data.top3Char);
                $("#top3Count").html(data.top3Count);
            },
            error: function (res) {
                console.log(res.responseText);
                console.log('出错了？？？');
            }
        });
    }
</script>
<script src="../js/jquery-3.1.1.min.js"></script>
</body>
</html>