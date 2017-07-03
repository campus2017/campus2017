<%--
  Created by IntelliJ IDEA.
  User: marcia
  Date: 2017/6/28
  Time: 09:30
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="UTF-8">
    <title>CharacterCounter</title>
</head>

<body>
<h3> 请输入一段文字</h3>

<form action="/countCharacter/counter" method="post">
    <table>
        <tr>
            <td>
                <textarea name="str" rows="10" cols="40" placeholder="请在此输入文本"></textarea>
            </td>
            <td>
                <table>
                    <tr>
                        <td><input type="submit" value="统计"/>
                        </td>
                    </tr>
                    <tr>
                        <td><input type="reset" value="清空内容"></td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</form>

<h4>各统计内容的个数如下：</h4>
<table border="1">
    <tr>
        <th>统计项</th>
        <th>个数</th>
    </tr>
    <tbody>
    <tr>
        <td>英文字母</td>
        <td>${englishCharacters}</td>
    </tr>
    <tr>
        <td>数字</td>
        <td>${numbers}</td>
    </tr>
    <tr>
        <td>中文汉字</td>
        <td>${chineseCharacters}</td>
    </tr>
    <tr>
        <td>中英文标点符号</td>
        <td>${punctuations}</td>
    </tr>
    </tbody>
</table>

<h4>文字中出现频率最高的三个字:</h4>
<table border="1">
    <tr>
        <th>名称</th>
        <th>个数</th>
    </tr>
    <tbody>
    <tr>
        <td> ${name1} </td>
        <td> ${val1} </td>
    </tr>
    <tr>
        <td> ${name2} </td>
        <td> ${val2} </td>
    </tr>
    <tr>
        <td>${name3} </td>
        <td>${val3} </td>
    </tr>
    </tbody>
</table>
</body>
</html>
