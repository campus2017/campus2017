<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>"/>

    <title>统计字符数</title>

    <link rel="stylesheet" type="text/css" href="css/countCharacter.css"/>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="js/countCharacter.js"></script>
</head>
<body>
<div><b>请选择一段文字</b></div>
<div class="main">
    <div class="type">
            <span class="type"><label>
                <input name="textType" type="radio" value=0 ${checked0}/>文件上传
            </label></span>
            <span class="type2"><label>
                <input name="textType" type="radio" value=1 ${checked1}/> 文本输入
            </label></span>
    </div>
    <div class="uploadType">
        <form method="post" id="upload" enctype="multipart/form-data" action="/CharacterCounter/count/upload" >
            <span class="upload"> 上传文件<input type="file" id="file" name="file"/>
            </span><lable>${filename}</lable>
            <span><input type="submit" value="统计" class="count" /></span>
            <%--<span class="count" onclick="countUpload()">统计</span>--%>
        </form>
    </div>
    <div class="inputType">
        <form method="post" id="input" enctype="multipart/form-data" action="/CharacterCounter/count/input">
            <div>
                <textarea id="text" cols="40" rows="5" placeholder="请在此输入文本内容" name="inputtxt">${txtstr}</textarea>

            </div>
            <div class="count2"><input type="submit" value="统计" class="count2" style="border:none;background:none;cursor:pointer;"/></div>
            <div class="clear"><a href="/CharacterCounter/count/counter" >清空内容</a></div>
            <%--<div><input type="submit" value="清空内容" class="clear"/></div>--%>
        </form>
    </div>



    <div class="result">
        <div>各统计内容的个数如下：</div>
        <div>
            <table>
                <tr>
                    <th>统计项</th>
                    <th>个数</th>
                </tr>
                <tr>
                    <td>英文字母</td>
                    <td>${result.engCount}</td>
                </tr>
                <tr>
                    <td>数字</td>
                    <td>${result.numCount}</td>
                </tr>
                <tr>
                    <td>中文汉字</td>
                    <td>${result.chnCount}</td>
                </tr>
                <tr>
                    <td>中英文标点符号</td>
                    <td id="pun">${result.punCount}</td>
                </tr>
            </table>
        </div>
    </div>
    <div class="frequency">
        <div>文字中出现频率最高的三个字符是：</div>
        <div>
            <table>
                <tr>
                    <th>名称</th>
                    <th>个数</th>
                </tr>
                <tr>
                    <td >${result.resultTop3.get(0).key}</td>
                    <td>${result.resultTop3.get(0).value}</td>
                </tr>
                <tr>
                    <td >${result.resultTop3.get(1).key}</td>
                    <td>${result.resultTop3.get(1).value}</td>
                </tr>
                <tr>
                    <td >${result.resultTop3.get(2).key}</td>
                    <td>${result.resultTop3.get(2).value}</td>
                </tr>
             </table>
        </div>
    </div>
</div>
</body>
</html>
