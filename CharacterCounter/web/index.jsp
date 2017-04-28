<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/2/9
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>CharacterCounter</title>

    <!--common css -->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>lib/bootstrap/css/bootstrap.min.css" />

    <!--common js -->
    <script type="text/javascript" src="<%=basePath%>lib/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>lib/bootstrap/js/bootstrap.min.js"></script>

    <!--self css-->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>lib/self/css/index.css" />

    <!--self js-->
    <script type="text/javascript" src="<%=basePath%>lib/self/js/index.js"></script>
</head>
<body>
    <div id="container" class="div_container">
        <div class="div_sep div_h2x"></div>
        <div class="div_row title">请选择一段文字</div>
        <div class="div_sep div_h2x"></div>
        <div class="div_row" style="margin-left: 20px;">
            <label class="checkbox-inline">
                <input id="fileRadio" type="radio" name="optionsRadiosinline" value="option1" checked>文件上传
            </label>
            <label class="checkbox-inline">
                <input id="textRadio" type="radio" name="optionsRadiosinline" value="option2">文本输入
            </label>
        </div>
        <div class="div_sep div_h2x"></div>
        <div class="div_row content" id="fileDiv">
            <button id="fileUpload" type="button" class="btn btn-info" style="font-size: 8px;width: 90px">
                <span class="glyphicon glyphicon-file"></span> 选择文件
            </button>
            <button id="fileCount" type="button" class="btn btn-info" style="font-size: 8px;margin-left:5px;width: 90px">
                <span class="glyphicon glyphicon-pencil"></span> 统计
            </button>
            <label style="margin-left:5px;font-weight:normal;">文件名称：</label>
            <label id="filePath" style="font-weight:normal;"></label>
            <iframe id="registerIframe" name="registerIframe" src="" style="display: none"></iframe>
            <form id="fileForm" action="/file" method="post" enctype="multipart/form-data" target="registerIframe">
                <input id="lefile" type="file" name="file" style="display:none" onchange="fileChange(this)">
            </form>
        </div>
        <div id="textDiv"style="height: 100px;">
            <div id="divTextArea" style="float: left;margin-left: 40px;">
                <textarea id="textArea" class="form-control" placeholder="请在此输入文本内容" style="height: 100px;"></textarea>
            </div>
            <div id="textBtn" style="float: right;margin-right: 40px;display: flex;height: 100px;">
                <div class="div_btn">
                    <div>
                        <button  id="textCount" type="button" class="btn btn-info" style="font-size: 8px;width: 90px;">
                            <span class="glyphicon glyphicon-pencil"></span> 统计
                        </button>
                    </div>
                    <div>
                        <button id="textClear" type="button" class="btn btn-warning" style="font-size: 8px;width: 90px;">
                            <span class="glyphicon glyphicon-pencil"></span> 清空内容
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div class="div_sep div_h2x"></div>
        <div class="div_row content">
            <span>各统计内容的个数如下：</span>
        </div>
        <div class="div_sep div_h1x"></div>
        <div class="content">
            <table cellspacing="0" cellpadding="0" style="border-collapse:collapse;" border="1">
                <tr style="height: 30px">
                    <td width="150px"style="text-align:center;font-weight: bold">统计项</td>
                    <td width="150px" style="text-align:center;font-weight: bold">个数</td>
                </tr>
                <tr style="height: 30px">
                    <td width="150px"style="text-align:center;">英文字母</td>
                    <td id="engCount" width="150px" style="text-align:center;"></td>
                </tr>
                <tr style="height: 30px">
                    <td width="150px"style="text-align:center;">数字</td>
                    <td id="numCount" width="150px" style="text-align:center;"></td>
                </tr>
                <tr style="height: 30px">
                    <td width="150px"style="text-align:center;">中文汉字</td>
                    <td id="chnCount" width="150px" style="text-align:center;"></td>
                </tr>
                <tr style="height: 30px">
                    <td width="150px"style="text-align:center;">中英文标点符号</td>
                    <td id="symCount" width="150px" style="text-align:center;"></td>
                </tr>
            </table>
        </div>
        <div class="div_sep div_h2x"></div>
        <div class="div_row content">
            <span>文字中出现频率最高的三个字是：</span>
        </div>
        <div class="div_sep div_h1x"></div>
        <div class="content">
            <table cellspacing="0" cellpadding="0" style="border-collapse:collapse;" border="1">
                <tr style="height: 30px">
                    <td width="150px"style="text-align:center;font-weight: bold">名称</td>
                    <td width="150px" style="text-align:center;font-weight: bold">个数</td>
                </tr>
                <tr style="height: 30px">
                    <td id="firstName" width="150px"style="text-align:center;"></td>
                    <td id="firstCount" width="150px" style="text-align:center;"></td>
                </tr>
                <tr style="height: 30px">
                    <td id="secondName" width="150px"style="text-align:center;"></td>
                    <td id="secondCount" width="150px" style="text-align:center;"></td>
                </tr>
                <tr style="height: 30px">
                    <td id="thirdName" width="150px"style="text-align:center;"></td>
                    <td id="thirdCount" width="150px" style="text-align:center;"></td>
                </tr>
            </table>
        </div>
    </div>
</body>
</html>
