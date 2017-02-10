<%--
  Created by IntelliJ IDEA.
  User: zhourunsheng
  Date: 17/2/2
  Time: 下午1:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8">
    <title>Character Counter</title>
</head>
<body>

<form method="post" action="/count">
    <p>请输入一段文字：</p><br>
    <input type="text" id="flag" name="identify" value="co" style="display: none">
    <table width="300">
        <tr>
            <td rowspan="2">
                <textarea rows="5" cols="25" name="text">${text}</textarea>
            </td>
            <td>
                <input type="submit" id="btn_submit_text" class="btn_submit" value="统计">
            </td>
        </tr>
        <tr>
            <td>
                <input type="reset" id="btn_reset" class="btn_reset" value="清空内容">
            </td>
        </tr>

    </table>
    <br>

    <p>各统计内容如下</p><br>
    <table border="1" width="200" cellpadding="0" cellspacing="0">
        <tr>
            <th>统计项</th>
            <th>个数</th>
        </tr>
        <tr>
            <td>英文字母</td>
            <td>${statisticsInfo.letters}</td>
        </tr>
        <tr>
            <td>数字</td>
            <td>${statisticsInfo.numbers}</td>
        </tr>
        <tr>
            <td>汉字</td>
            <td>${statisticsInfo.chineseChars}</td>
        </tr>
        <tr>
            <td>中英文标点</td>
            <td>${statisticsInfo.punctuations}</td>
        </tr>
    </table>

    <p>文字中出现频率最高的三个字是：</p><br>
    <table border="1" width="200" cellpadding="0" cellspacing="0">
        <tr>
            <th>名称</th>
            <th>个数</th>
        </tr>
        <tr>
            <td height="20">${most.countKey}</td>
            <td>${most.countValue}</td>
        </tr>
        <tr>
            <td height="20">${secondMore.countKey}</td>
            <td>${secondMore.countValue}</td>
        </tr>
        <tr>
            <td height="20">${thirdMore.countKey}</td>
            <td>${thirdMore.countValue}</td>
        </tr>

    </table>
</form>

</body>
</html>

