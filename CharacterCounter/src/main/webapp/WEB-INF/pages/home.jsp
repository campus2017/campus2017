<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>去哪儿大作业</title>
    <style type="text/css">
        body {
            position: fixed;
            width: 100%;
            height: 100%;
            top: 0;
            left: 0;
        }

        .center-div {
            position: absolute;
            top: 10px;
            left: 0;
            bottom: 0;
            right: 0;
            width: 25%;
            height: 100%;
            margin: 0 auto;
        }

    </style>

    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>

    <script type="application/javascript">
        $("document").ready(function () {

            $("#text-input-radio").click(function () {
                $("#text-input-div").show();
                $("#file-upload-div").hide();
            });

            $("#file-upload-radio").click(function () {
                $("#text-input-div").hide();
                $("#file-upload-div").show();
            });

            $("#resetButton").click(function () {
                $("#inputText").text("");
            })



        })


    </script>

</head>
<body>
<div class="center-div">
    <div>
        <span><strong>请选择一段文字</strong></span>
        <br/>
        <br/>
        <input type="radio" name="file" ${selectOne}id="file-upload-radio"/>
        <span style="margin-right: 60px;">文件上传</span>
        <input type="radio" name="file"  ${selectTwo} id="text-input-radio"/>
        <span>文件输入</span>
        <br/>
        <br/>
        <div id="change-div">
            <div id="file-upload-div" ${fileDisplay}>
                <form action="/resolve/text" method="post" enctype="multipart/form-data">
                    <input type="file" name="uploadfile" style=" display: inline-block;">
                    <button type="submit" class="btn btn-info" style="width: 80px;">统计</button>
                </form>
            </div>


            <div ${textDisplay} id="text-input-div">
                <form action="/resolve/text" method="post">
                <div style="float: left;">
                    <textarea rows="5" cols="57" name="textarea" id="inputText" placeholder="请输入文本内容" >${content}</textarea>
                </div>
                <div style="display: inline-block;margin-left: 20px;">
                    <button type="submit" class="btn btn-info" style="width: 80px;">统计</button>
                    <br>
                    <button type="reset" class="btn btn-warning" style="margin-top: 30px;" id="resetButton">清空内容</button>
                </div>
                </form>
            </div>
        </div>
        <table class="table table-bordered">
            <caption>各统计内容的个数如下：</caption>
            <thead>
            <tr>
                <th>统计项</th>
                <th>个数</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>英文字母</td>
                <td>${englishWord}</td>
            </tr>
            <tr>
                <td>数字</td>
                <td>${numberWord}</td>
            </tr>
            <tr>
                <td>中文汉字</td>
                <td>${chineseWord}</td>
            </tr>
            <tr>
                <td>中英文标点字符</td>
                <td>${punctuationWord}</td>
            </tr>

            </tbody>
        </table>
        <br/>
        <table class="table table-bordered">
            <caption>文字中出现频率最高的三个字是：</caption>
            <thead>
            <tr>
                <th>名称</th>
                <th>个数</th>
            </tr>
            </thead>
            <tbody>
            <tr style="height:37px;">
                <td>${firstWord}</td>
                <td>${firstCount}</td>
            </tr>
            <tr style="height:37px;">
                <td>${secondWord}</td>
                <td>${secondCount}</td>
            </tr>
            <tr style="height:37px;">
                <td>${thirdWord}</td>
                <td>${thirdCount}</td>
            </tr>
            </tbody>
        </table>

    </div>


</div>
</body>
</html>