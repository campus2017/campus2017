<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>

</head>

<body>

<script>
    function cleartext() {
        document.getElementById("filetext").value = ""
        document.getElementById("filetext").focus()
    }

    function validate_form(thisform) {
        var flag1 = false, flag2 = false;
        with (thisform) {
            with (filetext) {
                if (value != "") {
                    flag1 = true;
                }
            }
            with (file) {
                if (value != "") {
                    flag2 == true;
                }
            }
            if (flag1 || flag2) {
                return true;
            }
        }
    }


</script>

<p>请输入一段文字</p>


<form method="post" action="/qfc/count.do" onsubmit="return validate_form(this)" accept-charset="UTF-8">

    <input type="radio" name="upload" checked="true" value="text"/>文本输入
    <br/>
    <input id="filetext" name="filetext" style="height: 40px; width: 200px;">
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
            <td>${cr.english_num}
            </td>
        </tr>

        <tr>
            <td> 数字</td>
            <td>
                ${cr.digit_num}
            </td>
        </tr>

        <tr>
            <td> 中文汉字</td>
            <td>
                ${cr.chinese_character_num}
            </td>
        </tr>

        <tr>
            <td> 中英文标点符号</td>
            <td>
                ${cr.punctuation_num}
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
                <c:if test="${maxSize > 0}">
                    ${cr.maxRateCharater.get(0).c}
                </c:if>
            </td>
            <td>
                <c:if test="${maxSize > 0}">
                    ${cr.maxRateCharater.get(0).cnt}
                </c:if>
            </td>
        </tr>

        <tr height="30px">
            <td>
                <c:if test="${maxSize > 1}">
                    ${cr.maxRateCharater.get(1).c}
                </c:if>
            </td>
            <td>
                <c:if test="${maxSize > 1}">
                    ${cr.maxRateCharater.get(1).cnt}
                </c:if>
            </td>
        </tr>

        <tr height="30px">
            <td>
                <c:if test="${maxSize > 2}">
                    ${cr.maxRateCharater.get(2).c}
                </c:if>
            </td>
            <td>
                <c:if test="${maxSize > 2}">
                    ${cr.maxRateCharater.get(2).cnt}
                </c:if>
            </td>
        </tr>
    </table>
</form>

</body>

</html>