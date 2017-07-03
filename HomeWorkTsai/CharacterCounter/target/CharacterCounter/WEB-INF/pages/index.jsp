<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <meta charset="UTF-8">
    <title>CharacterCounter</title>

</head>
<body>
<h3> 请输入一段文字</h3>

<form action="/count/count.do" method="post">
    <table>
        <tr>
            <td>
                <textarea name="str" rows="10" cols="40" placeholder="请在此输入文本"></textarea>
            </td>
            <td>
                <table>
                    <tr>
                        <td><input type="submit" value="统计" style="background-color:darkturquoise; color: #efefef"/>
                        </td>
                    </tr>
                    <tr>
                        <td><input type="reset" value="清空内容" style="background-color:orange; color: #efefef"></td>
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
        <td>${enChar}</td>
    </tr>
    <tr>
        <td>数字</td>
        <td>${num}</td>
    </tr>
    <tr>
        <td>中文汉字</td>
        <td>${chChar}</td>
    </tr>
    <tr>
        <td>中英文标点符号</td>
        <td>${punctuation}</td>
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
        <td> ${topChar1} </td>
        <td> ${topCount1} </td>
    </tr>
    <tr>
        <td> ${topChar2} </td>
        <td> ${topCount2} </td>
    </tr>
    <tr>
        <td>${topChar3} </td>
        <td>${topCount3} </td>
    </tr>
    </tbody>
</table>
</body>
</html>
