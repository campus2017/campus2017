<%--
  Created by IntelliJ IDEA.
  User: WangWeiyi
  Date: 2017/6/24
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="UTF-8">

    <script language="javascript">
        // 展示和隐藏文本框
        function hiddenTextarea(){
            document.getElementById("div_file").style.display = "inline";
            document.getElementById("div_string").style.display = "none";
        }
        function showTextarea(){
            document.getElementById("div_file").style.display = "none";
            document.getElementById("div_string").style.display = "inline";
        }
    </script>

    <style type="text/css">
        body{
            font-family: "微软雅黑";
            font-size: 14px;
            font-style: normal;
            text-align: center;
            margin: 0px auto;
        }
        #alldivs{
            width : 500px;
            margin : 10px auto;
            padding : 0px;
        }
        .table_result{
            width : 300px;
            height : 200px;
            margin : 5px;
            border : 1px solid black;
            border-collapse : collapse;
            font-size: 15px;
        }
        .table_result th{
            border : 1px solid black;
            text-align : center;
        }
        .table_result td{
            border : 1px solid black;
            text-align : center;
        }
        caption {
            text-align: left;
            padding : 20px;
            padding-left : 0px;
        }
        #textArea{
            width : 300px;
            height : 100px;
        }
        #submitFile,#submitText,#buttonFile{
            background : #89cff0;
            color : white;
            width : 80px;
            padding : 5px;
            cursor : pointer;
        }
        #textFile{
            display: none;
        }
        #reset{
            background : #FFA500;
            color : white;
            width : 80px;
            padding : 5px;
            cursor : pointer;
        }
    </style>

    <title>统计文字信息</title>
</head>
<body>

<div id="alldivs">
    <table>
        <tbody>
        <tr>
            <th colspan="2" align="left">
                请选择一段文字
            </th>
        </tr>
        <tr>
            <td class="radioTd,left">
                <input type="radio" value="1" name="type" id = "selectFile" checked onClick='hiddenTextarea()'>文件上传
            </td>
            <td class="radioTd">
                <input type="radio" value="2" name="type" id = "selectString" onClick='showTextarea()'>文本输入
            </td>
        </tr>
        </tbody>
    </table>

    <%--用于文件上传--%>
    <div id="div_file">
        <form method="post" action="fileUpload.do" enctype="multipart/form-data" name="fileForm" id = "fileForm">
            <table id="formTable1">
                <tbody>
                <tr>
                    <td class="left">
                        <%--为了样式统一，隐藏了file--%>
                        <input type="file" name="textFile" id="textFile" />
                        <input type="button" id="buttonFile" value="文件上传" onclick="textFile.click()" >
                    </td>
                    <td>
                        <label name="filePath" id="filePath"></label>
                    </td>
                    <td class="right">
                        <input type="submit" value="统计" id = "submitFile"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
    </div>

    <%-- 用于文本直接上传 --%>
    <div id="div_string" hidden >
        <form method = "post" action = "stringUpload.do" name = "stringForm" id = "stringForm">
            <table>
                <tbody>
                <tr>
                    <td rowspan="2">
                        <textarea name = "textArea" id = "textArea" placeholder="请在此输入文本内容"></textarea>
                    </td>
                    <td>
                        <input type="submit" value="统计" id = "submitText"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="reset" value="清空内容" id="reset"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
    </div>

    <%--显示结果--%>
    <div class="table">
        <table id="table1" class="table_result">
            <caption>各统计内容的个数如下：</caption>
            <thead>
            <tr>
                <th>统计项</th>
                <th>个数</th>
            </tr>
            </thead>
            <tbody>
            <tr id="ENGLISH_Char">
                <td>英文字母</td>
                <td>${requestScope.EngChar}</td>
            </tr>
            <tr id="Digit_Char">
                <td>数字</td>
                <td>${requestScope.DigitChar}</td>
            </tr>
            <tr id="CHINESE_Char">
                <td>中文汉字</td>
                <td>${requestScope.ChiChar}</td>
            </tr>
            <tr id="PUNCTUTION">
                <td>中英文标点符号</td>
                <td>${requestScope.PunChar}</td>
            </tr>
            </tbody>
        </table>
    </div>

    <div class="table">
        <table id="table2" class="table_result" >
            <caption>文字中出现频率最高的三个字是：</caption>
            <thead>
            <tr>
                <th>名称</th>
                <th>个数</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${requestScope.Char1}</td>
                <td>${requestScope.Char1Num}</td>
            </tr>
            <tr>
                <td>${requestScope.Char2}</td>
                <td>${requestScope.Char2Num}</td>
            </tr>
            <tr>
                <td>${requestScope.Char3}</td>
                <td>${requestScope.Char3Num}</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
