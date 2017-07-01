<%--
  Created by IntelliJ IDEA.
  User: woo
  Date: 6/30
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="me.woostam.Word" %>
<%@ page import="me.woostam.TextInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.xml.soap.Text" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>统计</title>
    <style type="text/css">
        table,th,td {border:1px solid black;text-align:center}
        table.singlineBorder {border-collapse:collapse;}
        #count1{position: absolute;
            left:415px;
            top:170px;
            padding:6px 12px;
            display: inline-block;
            background: #1E90FF;
            border: 1px solid #99D3F5;
            border-radius: 4px;
            color: #FFFFFF;
            text-decoration: none;
            text-indent: 0;
            line-height: 20px;
            width:90px}
        #reset1{position: relative;
            bottom:14px;
            padding:6px 12px;
            display: inline-block;
            background: #FF8000;
            border: 1px solid #99D3F5;
            border-radius: 4px;
            margin:6px 0px 0px 15px;
            color: #FFFFFF;
            text-decoration: none;
            text-indent: 0;
            line-height: 20px;
            width:90px}
        .file:hover {
            background: #AADFFD;
            border-color: #78C3F3;
            color: #004974;
            text-decoration: none;
        }
    </style>
    <script type="text/javascript">

        window.onload=function autochioce(){
            document.getElementById("textarea").style.display="none";
            document.getElementById("goup").style.display="block";
        }
        function divClick(){

            var show="";
            var apm = document.getElementsByName("ways");
            for(var i=0;i<apm.length;i++){
                if(apm[i].checked)
                    show = apm[i].value;
            }

            switch (show){
                case '1':
                    document.getElementById("textarea").style.display="none";
                    document.getElementById("goup").style.display="block";
                    break;
                case '2':
                    document.getElementById("textarea").style.display="block";
                    document.getElementById("goup").style.display="none";
                    break;
            }
        }
    </script>
    <%
        List<Word> list = (List<Word>) request.getAttribute("top");
        TextInfo textInfo = (TextInfo) request.getAttribute("textInfo");
    %>
</head>
<body>
<form action="/filecounter" method="post" enctype="multipart/form-data">
    <label>你的方式:</label></br>
    <label>文件上传</label>
    <input type="radio" value="1" name="ways" onclick="divClick();" checked="checked"/>&nbsp;&nbsp;&nbsp;&nbsp;
    <label>文本输入</label>
    <input type="radio" value="2"  name="ways" onclick="divClick();"/>
    <div></div>
</form>
<div class="form_row" id="goup" >
    <form action="/filecounter" method="post" enctype="multipart/form-data">
        <input type="file" value="上传文件" name="file" />
        <input type="submit" style="width: 80px;height: 30px;background-color: #00ccff" value="统计">
    </form>
</div>
</br></br>
<div class="form_row" id="textarea">
    <form action="/textcounter" method="post" >
        <label>&nbsp;&nbsp;</label>
        <textarea name="text" cols="50" rows="10">请在此输入文本内容...</textarea>
        <input type="submit" value="统计"  name="submit" id="count1"/>
        <input type="reset" value="清空内容"  name="reset" id="reset1"/>
    </form>
</div>


<div>
    <table cellpadding="7px" class="singlineBorder" width=30%>
        各统计内容的个数如下：
            <tr>
                <th width=50%>统计项</th>
                <th>个数</th>
            </tr>
            <tr>
                <td>英文字母</td>
                <td>
                    <%= textInfo == null ? "" : textInfo.getEnCount() + ""%>
                </td>
            </tr>
            <tr>
                <td>数字</td>
                <td>
                    <%= textInfo == null ? "" : textInfo.getDigitCount() + ""%>
                </td>
            </tr>
            <tr>
                <td>中文汉字</td>
                <td>
                    <%= textInfo == null ? "" : textInfo.getCnCount()+ ""%>
                </td>
            </tr>
            <tr>
                <td>中英文标点符号</td>
                <td>
                    <%= textInfo == null ? "" : textInfo.getPunCount() + ""%>
                </td>
            </tr>
    </table>
    </div>
    <div>
        <table cellpadding="7px" class="singlineBorder" width=30%>
            文字中出现频率最高的三个字是：
            <tr>
                <th>名称</th>
                <th>个数</th>
            </tr>
            <tr>
                <td>
                    <%= list == null ? "" : list.get(0).getWord() %>
                </td>
                <td>
                    <%= list == null ? "" : list.get(0).getCnt() + "" %>
                </td>
            </tr>
            <tr>
                <td>
                    <%= list == null ? "" : (list.size() < 2 ? "" : list.get(1).getWord()) %>
                </td>
                <td>
                    <%= list == null ? "" : (list.size() < 2 ? "" : list.get(1).getCnt()+"") %>
                </td>
            </tr>
            <tr>
                <td>
                    <%= list == null ? "" : (list.size() < 3 ? "" : list.get(2).getWord()) %>
                </td>
                <td>
                    <%= list == null ? "" : (list.size() < 3 ? "" : list.get(2).getCnt()+"") %>
                </td>
                </tr>
        </table>
    </div>
</body>
</html>

