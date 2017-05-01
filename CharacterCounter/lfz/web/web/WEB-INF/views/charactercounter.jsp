<%--
  Created by IntelliJ IDEA.
  User: lfz
  Date: 2017/5/1
  Time: 下午1:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <h3>请输入一段文字</h3>
</head>
<body>
    <div>
        <form action="http://localhost:8080/charactercounter" method="post" enctype="multipart/form-data">
            <input id="uploadFile" type="radio" name="uploadType" value="file" onclick=showFileStyle(); checked/>文件上传
            <input id="uploadText" type="radio" name="uploadType" value="text" onclick=showTextStyle(); />文本输入
            <div id="fileStyle" >
                <input type="file" id="file" name="file" /> <br />
                <input type=image src = "images/submit.png">
            </div>
            <div id="textStyle" style="display:none">
                <div style="float: left;width: auto;height: auto">
                <textarea id="text" name="text" rows = "4" cols = '45'></textarea>
                </div>
                <div style="float: left">
                    <div style="padding-top: 5px; padding-left: 8px">
                        <input type = image src = "images/submit.png">
                    </div>
                    <div style="padding-top: 10px; padding-left: 8px">
                        <img src = "images/clear.png" onclick=clearContent();>
                    </div>
                </div>
            </div>
            <div style="position:absolute;left:10px;top:150px">
                <table border="1" cellspacing="0" width="300">
                    <caption>各统计内容的个数如下</caption>
                    <tr>
                        <td align="center" width="150">统计项</td>
                        <td align="center">个数</td>
                    </tr>
                    <tr>
                        <td height="30" align="center">英文字母</td>
                        <td height="30" align="center">${englishCount}</td>
                    </tr>
                    <tr>
                        <td height="30" align="center">数字</td>
                        <td align="center">${digitCount}</td>
                    </tr>
                    <tr>
                        <td height="30" align="center">中文汉字</td>
                        <td align="center">${chineseCount}</td>
                    </tr>
                    <tr>
                        <td height="30" align="center">中英文标点符号</td>
                        <td align="center">${puncCount}</td>
                    </tr>
                </table>
            </div>
            <div style="position:absolute;left:10px;top:330px">
                <table border="1" cellspacing="0" width="300">
                    <caption>文字中出现频率最高的三个字是</caption>
                    <tr>
                        <td height="30" align="center">名称</td>
                        <td height="30" align="center">个数</td>
                    </tr>
                    <c:set var="cnt" value="0"/>
                    <c:forEach items ="${ranking}" var= "m" begin="0" end="2">
                        <c:set var="cnt" value="${cnt+1}" />
                        <tr>
                            <td height="30" align="center">${m.key}</td>
                            <td height="30" align="center">${m.value}</td>
                        </tr>
                    </c:forEach >
                    <c:forEach var= "x" begin="1" end="${3 - cnt}">
                        <tr>
                            <td height="30" align="center"></td>
                            <td height="30" align="center"></td>
                        </tr>
                    </c:forEach >
                </table>
            </div>
        </form>
    </div>
</body>
<script type="text/javascript">
    function clearContent() {
        document.getElementById("text").value="";
    }
    function showFileStyle() {
        document.getElementById("fileStyle").style.display="";
        document.getElementById("textStyle").style.display="none";
    }
    function showTextStyle() {
        document.getElementById("fileStyle").style.display="none";
        document.getElementById("textStyle").style.display="";
    }
</script>

</html>
