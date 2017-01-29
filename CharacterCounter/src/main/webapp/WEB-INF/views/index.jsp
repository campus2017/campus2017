<%--
  Created by IntelliJ IDEA.
  User: zdl
  Date: 2017/1/25
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>CharacterCounter</title>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
    <script>
        $(document).ready(function(){
            $("input[name=method]").click(function(){
                show();
            });
            $("#cleartext").click(function () {
                $("#ta").val("");
            });
            $("#file").click(function () {
                upload();
            });
        });

        function show() {
            switch ($("input[name=method]:checked").attr("id")){
                case "upload":
                    $("form[name=m1]").show();
                    $("form[name=m2]").hide();
                    break;
                case "plain":
                    $("form[name=m2]").show()
                    $("form[name=m1]").hide();
                    break;
                default:
                    break;
            }
        }
        function upload() {
            var selectedFile = document.getElementById("file").files[0];
            var name = selectedFile.name;
            var size = selectedFile.size;
            var reader = new FileReader();
            var content;
            reader.onload = (function (e) {
                content = e.target.result;
                alert(content);
            });
            reader.readAsText(selectedFile);
        }

    </script>
</head>
<body>
<p> 请选择一段文字</p>
<input type="radio" name="method" id="upload" checked="checked"/>
<label>文件上传</label>
<input type="radio" name="method" id="plain"/>
<label>文本输入</label>
<!--<form action="upload.do" method="post" enctype="multipart/form-data" name="m1">-->
<!--<input type="file" name="file" value="文件上传"/>-->
<!--<input type="button" id="import" value="统计"/>-->
<!--</form>-->
<form action="/CharacterCounter/statistic.dao" method="post" enctype="multipart/form-data" name="m1">
    <input type="file" id="file" value="文件上传" onchange="upload();"/>
    <input type="submit" value="统计"/>
</form>
<form action="/CharacterCounter/statistic.dao" method="post" style="display:none" name="m2">
    <table>
        <tr>
            <td>
                <textarea name="ta" rows="4" cols="50" placeholder="请在此输入文本"></textarea>
            </td>
            <td>
                <table>
                    <tr>
                        <td><input type="submit" value="统计"/></td>
                    </tr>
                    <tr>
                        <td><input type="button" id="cleartext" value="清空内容"></td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</form>
<p>各统计内容的个数如下:</p>
<table id="statistic" border="1">
    <tr>
        <th>统计项</th>
        <th>个数</th>
    </tr>
    <tbody>
    <tr >
        <td>英文字母</td>
        <td id="characters">${characters}</td>
    </tr>
    <tr>
        <td>数字</td>
        <td id="numbers">${numbers}</td>
    </tr>
    <tr>
        <td>中文汉字</td>
        <td id="chineses">${chineses}</td>
    </tr>
    <tr>
        <td>中英文标点符号</td>
        <td id="punctuations">${punctuations}</td>
    </tr>
    </tbody>
</table>
<p>文字中出现频率最高的三个字:</p>
<table id="frequence" border="1">
    <tr>
        <th>名称 </th>
        <th>个数 </th>
    </tr>
    <tbody>
    <tr>
        <td id="name1"> ${name1} </td>
        <td id="value1"> ${value1} </td>
    </tr>
    <tr>
        <td id="name2"> ${name2} </td>
        <td id="value2"> ${value2} </td>
    </tr>
    <tr>
        <td id="name3">${name3} </td>
        <td id="value3">${value3} </td>
    </tr>
    </tbody>
</table>
</body>
</html>
