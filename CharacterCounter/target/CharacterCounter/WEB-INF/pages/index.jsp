
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Iterator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>文件统计</title>

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<center>
    <h4>请选择一段文字</h4>
    <form >
        <%-- <input type="button"> <br/>--%>
        <input type="radio" name="selectMethod" value="文件上传" checked onclick="divClick();">文件上传
        <input type="radio" name="selectMethod" value="文本输入" onclick="divClick();">文本输入<br/>
            <%--<input type="radio" name="sex" <c:if test="${param.sex== '男'}">checked="checked"</c:if>>男--%>
            <%--<input type="radio" name="sex" <c:if test="${param.sex== '女'}">checked="checked"</c:if>>女--%>
    </form>
<form id="uploadFormText" action="textInput" enctype="multipart/form-data" method="post">
    <div id="keyInText" style="display: none" >
        <table>
            <tr>
                <td rowspan="2">
                    <textarea style="width:300px;height:300px;" name="textBox"></textarea></td>
                <td> <button id="WordCount" name="WordCount" >统计</button></td></tr>
            <tr><td><input type="reset" value="重置"></td></tr>
        </table>
    </div>
</form>
    <form id="uploadFormFile" action="upload" enctype="multipart/form-data" method="post">


        <%--<form id="uploadForm"  enctype="multipart/form-data" method="post">--%>
<div id="keyInFile">
    <table><tr><td>
        <input type="file" name="file" id="File_input" ></td><td>
        <button id="startCount" name="startCount" >统计</button></td>
        </tr>
    </table>
</div>
        <%--<input type="submit" value="统计" name="startCount" />--%>
        <br/>
        <div>各统计的内容如下<br>
            <div id="allContent">
                <%
                    HttpSession s = request.getSession();

                %>
                <table border="1">
                    <tr>
                        <td>统计项</td>
                        <td>个数</td>
                    </tr>

                    <tr>
                        <td>英文字母</td>
                        <%--<% String en_ch_count="${sessionScope.en_ch_count}";%>--%>
                        <c:choose>
                            <c:when test="${ empty sessionScope.en_ch_count}">
                                <td></td>
                            </c:when>
                            <c:otherwise><td><%=request.getSession().getAttribute("en_ch_count").toString()%></td></c:otherwise>
                        </c:choose>
                    </tr>
                    <tr>
                        <td>数字</td>
                        <c:choose>
                            <c:when test="${empty sessionScope.number_count}">
                                <td></td>
                            </c:when>
                            <c:otherwise><td><%=request.getSession().getAttribute("number_count").toString()%></td></c:otherwise>
                        </c:choose>
                        </td>
                    </tr>
                    <tr><td>中文汉字</td>
                        <c:choose>
                            <c:when test="${ empty sessionScope.chinese_ch_count}">
                                <td></td>
                            </c:when>
                            <c:otherwise><td><%=request.getSession().getAttribute("chinese_ch_count").toString()%></td></c:otherwise>
                        </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <td>中英文标点符号</td>

                        <c:choose>
                            <c:when test="${empty sessionScope.punctuation_count}">
                                <td></td>
                            </c:when>
                            <c:otherwise><td><%=request.getSession().getAttribute("punctuation_count").toString()%></td></c:otherwise>
                        </c:choose>
                    </tr>


                </table>
                <br>
                文字中出现频率最高的3个字是：</div><br>


            <table border="1">
                <tr>
                    <td>名称</td>
                    <td>个数</td>
                </tr>
                <c:choose>
                    <c:when test="${empty sessionScope.topThree}">
                        <tr height="30px"><td ></td><td></td></tr>
                        <tr height="30px"><td></td><td></td></tr>
                        <tr height="30px"><td></td><td></td></tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="num" items="${sessionScope.topThree}">

                            <tr height="30px">
                                <c:choose>

                                    <c:when test="${empty num.word}">
                                        <%--<td></td>--%>
                                    </c:when>
                                    <c:when test="${empty num.counts}">
                                        <%--<td></td>--%>
                                    </c:when>
                                    <c:otherwise>
                                        <td>${num.word}</td>

                                        <td>${num.counts}</td>
                                    </c:otherwise>
                                </c:choose>

                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>

            </table>
        </div>
    </form>
    <%--<form name="Form2" action="upload" method="post"  enctype="multipart/form-data">--%>
    <%--<h1>使用spring mvc提供的类的方法上传文件</h1>--%>
    <%--<input type="file" name="file" id="File_input">--%>
    <%--<button>test</button>--%>
    <%--</form>--%>
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</center>
</body>

</html>
<%--<input type="button" id="click_event" value="点击隐藏"/>--%>
<%--<div id="hidden_enent">蚂蚁部落欢迎您</div>--%>

<script type="text/javascript">
    function divClick(){

        var show="";
        var apm = document.getElementsByName("selectMethod");
        for(var i=0;i<apm.length;i++){
            if(apm[i].checked)
                show = apm[i].value;
        }

        switch (show){
            case "文件上传":
                document.getElementById("keyInText").style.display="none";
                document.getElementById("keyInFile").style.display="block";

                break;
            case "文本输入":
                document.getElementById("keyInText").style.display="block";
                document.getElementById("keyInFile").style.display="none";
                break;
            default:
                document.getElementById("keyInText").style.display="none";
                document.getElementById("keyInFile").style.display="block";
                break;

        }
    }
    </script>

