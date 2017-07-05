<%@ page import="com.qfc.model.CharacterResult" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.qfc.model.*" %>
<jsp:useBean id="cr" class="com.qfc.model.CharacterResult"></jsp:useBean>
<html>

<head>

</head>

<body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js">
    function cleartext() {
        document.getElementById("filetext").value = ""
        document.getElementById("filetext").focus()
    }

    function validate_form(thisform) {
        return false
        var flag1 = false, flag2 = false;
        with (thisform) {
            with (filetext) {
                if (value != "") {
                    flag1 = true;
                }
            }
            with (file) {
                if (value != "") {
                    flag2 = true;
                }
            }
            if (flag1 || flag2) {
                return true;
            }
            return false;
        }
        return false;
    }


</script>

<p>请输入一段文字</p>


<form method="post" action="/qfc/count.do" onsubmit="return validate_form(this)" accept-charset="UTF-8">

    <input type="radio" name="upload"  value="text"/>文本输入
    <br/>
    <input id="filetext" name="filetext"  style="height: 40px; width: 200px;">
    <input type="submit" value="统计"/>
    <input type="button" onclick="cleartext()" value="清空"/>
    <br/><br/>
    <table border="2" width="300px" height="40px">

        <tr>
            <td> 统计项</td>
            <td> 个数</td>
        </tr>

        <tr>
            <td> 英文字母</td>
            <td><%= cr.getEnglish_num() == 0 ? "" : cr.getEnglish_num()%>
            </td>
        </tr>

        <tr>
            <td> 数字</td>
            <td><%=cr.getDigit_num() == 0 ? "" : cr.getDigit_num()%>
            </td>
        </tr>

        <tr>
            <td> 中文汉字</td>
            <td><%=cr.getChinese_character_num()%>
            </td>
        </tr>

        <tr>
            <td> 中英文标点符号</td>
            <td><%=cr.getPunctuation_num()%>
            </td>
        </tr>

    </table>

    <p>文中出现频率最高的三个字是：</p>
    <table border="2" width="300px" height="40px">
        <tr>
            <td>名称</td>
            <td>个数</td>
        </tr>
        <tr height="30px">
            <td>
                <%
                    int sz = cr.getMaxRateCharater().size();
                %>
                <%= sz > 0 ? cr.getMaxRateCharater().get(0).getC() : ""%>
            </td>
            <td>
                <%= sz > 0 ? cr.getMaxRateCharater().get(0).getC() : ""%>
            </td>
        </tr>

        <tr height="30px">
            <td>
                <%= sz > 1 ? cr.getMaxRateCharater().get(1).getC() : ""%>
            </td>
            <td>
                <%= sz > 1 ? cr.getMaxRateCharater().get(1).getC() : ""%>
            </td>
        </tr>

        <tr height="30px">
            <td>
                <%= sz > 2 ? cr.getMaxRateCharater().get(2).getC() : ""%>
            </td>
            <td>
                <%= sz > 2 ? cr.getMaxRateCharater().get(2).getC() : ""%>
            </td>
        </tr>
    </table>
</form>

</body>

</html>