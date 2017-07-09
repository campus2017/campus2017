<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: nipingchen
  Date: 16-12-14
  Time: 上午11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="stylesheet" type="text/css" href="../css/style.css" />
</head>

<body>
<h3>请选择一段文字</h3>
<input type="radio" class="tab" name="inputType" checked="checked" id="input1"/><label for="input1">文件上传</label>
<input type="radio" class="tab" name="inputType" id="input2"/><label for="input2">文本输入</label>

<form class="inputData"action="uploadFileAndAnalysis" method="post" enctype="multipart/form-data">
    <lable class="upload">上传文件</lable>
    <input id="inputFile" type="file" name="inputFile" />
    <input id="file_submit" class="btn" type="submit" value="统计" />
</form>

<form class="inputData" action="addContextAndAnalysis" method="post">
    <textarea id="inputText" name="inputText" rows="8" cols="50"></textarea>
    <input class="btn"  id="context_submit" type="submit" value="统计"/>
    <input class="btn" id="reset-btn" type="reset" value="清空内容"/>
</form>

<p>各统计内容的个数如下</p>
<table class="items">
    <tr>
        <th>统计项</th>
        <th>个数</th>
    </tr>
    <tr>
        <td>英文字母</td>
        <td> ${countDto.enCounts}</td>
    </tr>
    <tr>
        <td>数字</td>
        <td> ${countDto.numCounts}</td>
    </tr>
    <tr>
        <td>中文汉字</td>
        <td> ${countDto.chCounts}</td>
    </tr>
    <tr>
        <td>中英文标点符号</td>
        <td> ${countDto.numCounts}</td>
    </tr>
</table>

<p>文字中出现频率最高的三个字是：</p>
<table class="frequency">
    <tr>
        <th>名称</th>
        <th>个数</th>
    </tr>
    <c:forEach	items="${countDto.maxNumThreeCharacter}" var="CharacterAndCount">
        <tr>
            <td>${CharacterAndCount.character}</td>
            <td>${CharacterAndCount.counts}</td>
        </tr>
    </c:forEach>

</body>
<script type="text/javascript" src="../js/tab.js" ></script>
</html>