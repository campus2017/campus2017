
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>result</title>

    <style type="text/css">
        table.one
        {
            table-layout: fixed;
        }
    </style>

</head>
<body>

<p> 输入的字符串：</p>

${inputString}



<p>各统计内容的个数如下：</p>

<table border="1" cellspacing="0"  cellpadding="5"  class="one" width="400px" style="text-align: center" >
    <tr>
        <td width="50%">统计项</td>
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


<p >文字中出现频率最高的三个字是：</p>

<table  border="1" cellspacing="0"  cellpadding="5"  class="one" width="400px" style="text-align: center;" >
    <tr>
        <td width="50%" height=" 50px">名称</td>
        <td>个数</td>
    </tr>
    <tr>
        <td height=" 50px">${mostOneName}</td>
        <td >${mostOneValue}</td>
    </tr>
    <tr>
        <td height=" 50px">${mostTwoName}</td>
        <td>${mostTwoValue}</td>
    </tr>
    <tr>
        <td height=" 50px">${mostThreeName}</td>
        <td>${mostThreeValue}</td>
    </tr>
</table>



</body>
</html>
