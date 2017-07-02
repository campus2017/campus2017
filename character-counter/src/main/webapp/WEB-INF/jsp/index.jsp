<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>CharacterCounter</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $(":radio").click(function () {
                if (this.checked) {
                    if ($(this).attr("id") == "fileUp") {
                        $("#file-upload").show();
                        $("#text-input").hide();

                    }
                    if ($(this).attr("id") == "textIn") {
                        $("#text-input").show();
                        $("#file-upload").hide();
                    }
                }
            });
            $("#btn-upload").click(function () {
                $("#uploadedFile").click();
            });
            $("#uploadedFile").change(function () {
                var dis = $("#uploadedFile").val();
                var index = dis.lastIndexOf('\\');
                if(index >= 0)
                    var fileName = dis.substring(index+1,dis.length,dis)
                $("#fileName").text(fileName);
            });
            $("#doCountBtn").click(function () {
                $("#textFileForm").submit();
            });
            $("#doUploadBtn").click(function () {
                $("#uploadFileForm").submit();
            });


        });
    </script>
</head>
<body>
<div class="container">
    <div class="center-block">
       <h4>请选择一段文字</h4>
            <div class="row">
                <div class="col-md-3"><input name="input_type" id="fileUp" value="file" type="radio" checked>文件上传</input></div>
                <div class="col-md-3"><input name="input_type" id="textIn" value="text" type="radio">文本输入</input></div>
            </div>
            <div id="file-upload">
                <div class="boder-row">
                    <div class="row">
                    <div class="col-md-2">
                        <form id="uploadFileForm" action="/fileUpload" method="post" enctype="multipart/form-data">
                            <input id="uploadedFile" name="uploadedFile" type="file" style="display: none"></input>
                            <button id="btn-upload" type="button" class="btn btn-primary">上传文件</button>
                        </form>
                    </div>
                        <div class="col-md-2">
                            <div id="fileName" style="overflow: hidden"></div>
                        </div>
                        <div class="col-md-2">
                        <button id ="doUploadBtn" type="button" class="btn btn-primary">统计</button>
                    </div>
                </div>
                </div>
            </div>

            <div id="text-input" style="display: none">
            <div class="boder-row">
                <div class="row">
                <div class="col-md-4">
                    <form id="textFileForm" action="/textInput" method="post" enctype="application/x-www-form-urlencoded">
                    <textarea name="text" class="text_area" placeholder="请输入"></textarea>
                    </form>
                </div>
                <div class="double-btn">
                    <div class="btn-margin"><button id="doCountBtn" type="button" class="btn btn-primary">统计</button></div>
                    <div class="btn-margin"><button type="button" class="btn btn-warning">清空内容</button></div>
                </div>
            </div>
            </div>
        </div>
        <div class="result">
            <div class="row">
                <div class="col-md-4">
                    <table class="table table-bordered">
                        <caption>各统计内容的个数如下：</caption>
                        <thead>
                        <tr>
                            <th><b>统计项</b></th>
                            <th><b>个数</b></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>英文字母</td>
                            <td>${countResult.engCount}</td>
                        </tr>
                        <tr>
                            <td>数字</td>
                            <td>${countResult.numCount}</td>
                        </tr>
                        <tr>
                            <td>中文汉字</td>
                            <td>${countResult.chineseCount}</td>
                        </tr>
                        <tr>
                            <td>中英文标点符号</td>
                            <td>${countResult.punctuationCount}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <table class="table table-bordered">
                        <caption>文字中出现频率最高的三个字是:</caption>
                        <thead>
                        <tr>
                            <th><b>名称</b></th>
                            <th><b>个数</b></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${topThree[0].character}</td>
                            <td>${topThree[0].integer}</td>
                        </tr>
                        <tr>
                            <td>${topThree[1].character}</td>
                            <td>${topThree[1].integer}</td>
                        </tr>
                        <tr>
                            <td>${topThree[2].character}</td>
                            <td>${topThree[2].integer}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    </div>
</div>

</body>
</html>