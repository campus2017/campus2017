<%--
  Created by IntelliJ IDEA.
  User: canda
  Date: 6/20/17
  Time: 12:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>字符统计</title>
    <link rel="stylesheet" type="text/css" href="../../css/index.css" >
    <script type="text/javascript">
        function formReset()
        {
            document.getElementById("inputString").value="";
            document.getElementById("letterCount").innerHTML="";
            document.getElementById("numberCount").innerHTML="";
            document.getElementById("wordOfCnCount").innerHTML="";
            document.getElementById("punctutionCount").innerHTML="";
            document.getElementById("firstFrequencyKey").innerHTML="";
            document.getElementById("firstFrequencyValue").innerHTML="";
            document.getElementById("secondFrequencyKey").innerHTML="";
            document.getElementById("secondFrequencyValue").innerHTML="";
            document.getElementById("thirdFrequencyKey").innerHTML="";
            document.getElementById("thirdFrequencyValue").innerHTML="";

        }
    </script>
</head>
<body>
<form  action="<%= request.getContextPath()%>/CharacterCounter/index" method="post">
    <div class="container">
        <h3>请输入一段文字</h3>
        <div class="input">
            <textarea placeholder="请在此输入文本内容" id="inputString" name="inputString">${ counterResult.inputString}</textarea>
            <div>
                <input type="submit" class="commit"   value="统计"></input>
                <input type="button" class="clear" onclick="formReset()" value="清空内容"></input>
            </div>
        </div>
        <div class="out1">
            <h5>各统计内容的个数如下</h5>
            <table>
                <tr>
                    <th>统计项</th>
                    <th>个数</th>
                </tr>
                <tr>
                    <td>英文字母</td>
                    <td id="letterCount">${ counterResult.letterCount}</td>
                </tr>
                <tr>
                    <td>数字</td>
                    <td id="numberCount">${ counterResult.numberCount}</td>
                </tr>
                <tr>
                    <td>中文汉字</td>
                    <td id="wordOfCnCount">${ counterResult.wordOfCnCount}</td>
                </tr>
                <tr>
                    <td>中英文标点符号</td>
                    <td id="punctutionCount">${ counterResult.punctutionCount}</td>
                </tr>

            </table>
        </div>
        <div class="out2">
            <h5>文字中出现频率最高的三个字是</h5>
            <table>
                <tr>
                    <th>名称</th>
                    <th>个数</th>
                </tr>
                <tr>
                    <td id="firstFrequencyKey">${ counterResult.firstFrequencyKey}</td>
                    <td id="firstFrequencyValue">${ counterResult.firstFrequencyValue}</td>
                </tr>
                <tr>
                    <td id="secondFrequencyKey">${ counterResult.secondFrequencyKey}</td>
                    <td id="secondFrequencyValue">${ counterResult.secondFrequencyValue}</td>
                </tr>
                <tr>
                    <td id="thirdFrequencyKey">${ counterResult.thirdFrequencyKey}</td>
                    <td id="thirdFrequencyValue">${ counterResult.thirdFrequencyValue}</td>
                </tr>
            </table>
        </div>
    </div>
</form>
</body>
</html>