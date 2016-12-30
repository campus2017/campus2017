<%--
  Created by IntelliJ IDEA.
  User: DW
  Date: 2016/12/25
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath%>"/>

    <title>统计字符数</title>

    <link rel="stylesheet" type="text/css" href="css/countCharacter.css"/>
    <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="js/countCharacter.js"></script>
</head>
<body>
    <div><b>请选择一段文字</b></div>
    <div class="main">
        <div class="type">
            <span><label><input name="textType" type="radio" value=0 checked="checked" />文件上传</label></span>
            <span class="type2"><label><input name="textType" type="radio" value=1 />文本输入</label></span>
        </div>
        <div class="uploadType">
            <form id="upload" enctype="multipart/form-data">
                <span class="upload">上传文件<input type="file" id="file" name="file"/></span>
                <span class="filename"></span>
                <span class="count" onclick="countUpload()">统计</span>
            </form>
        </div>
        <div class="inputType">
            <div>
                <textarea id="text" cols="40" rows="5" placeholder="请在此输入文本内容"></textarea>
            </div>
            <div class="count2" onclick="countInput()">统计</div>
            <div class="clear" onclick="clearContent()">清空内容</div>
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
                        <td id="eng"></td>
                    </tr>
                    <tr>
                        <td>数字</td>
                        <td id="num"></td>
                    </tr>
                    <tr>
                        <td>中文汉字</td>
                        <td id="chn"></td>
                    </tr>
                    <tr>
                        <td>中英文标点符号</td>
                        <td id="pun"></td>
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
                        <td id="first"></td>
                        <td id="firstNum"></td>
                    </tr>
                    <tr>
                        <td id="second"></td>
                        <td id="secondNum"></td>
                    </tr>
                    <tr>
                        <td id="third"></td>
                        <td id="thirdNum"></td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
