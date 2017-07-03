
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>result</title>
</head>
<body>
<p> 输入的字符串：</p>
${inputString}
<p></p>

<table border="0" style="border:1px solid #000000;">
    <tr>
        <td>统计项</td>
        <td>个数</td>
    </tr>
 <tr>
     <td>英文字母</td>
     <td>${alphbetCnt}</td>
 </tr>
    <tr>
        <td>数字</td>
        <td>${numCnt}</td>
    </tr>
    <tr>
        <td>中文汉字</td>
        <td>${chineseCnt}</td>
    </tr>
    <tr>
        <td>标点符号</td>
        <td>${splitCnt}</td>
    </tr>
</table>


<p></p>

<table border="0" style="border:1px solid #000000;">
    <tr >
        <td>名称</td>
        <td>个数</td>
    </tr>
    <tr>
        <td>${mostOneName}</td>
        <td>${mostOneValue}</td>
    </tr>
    <tr>
        <td>${mostTwoName}</td>
        <td>${mostTwoValue}</td>
    </tr>
    <tr>
        <td>${mostThreeName}</td>
        <td>${mostThreeValue}</td>
    </tr>
</table>



</body>
</html>
