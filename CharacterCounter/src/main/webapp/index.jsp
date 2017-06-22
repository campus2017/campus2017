<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>CharacterCounter</title>
    <script src="resources/js/jquery-1.11.3.min.js"></script>
    <script src="resources/js/jquery.validate.min.js"></script>
    <script src="resources/js/jquery.form.js"></script>
    <script src="resources/js/charcounter.js"></script>
    <link rel="stylesheet" href="resources/css/charcounter.css" type="text/css"/>
</head>
<body>

<div class="main">
    <h2>请选择一段文字</h2>
    <div name="radio_group">
        <input type="radio" id="switch_text" name="switch" value="text" checked="checked">文本输入
        <input type="radio" id="switch_file" name="switch" value="file">文件上传
    </div>

    <div id="text_operate" style="margin-top: 10px">
        <form id="text_form">
            <textarea placeholder="请在此输入文本内容" id="text" name="text" style="width: 100%; height: 100px"></textarea>
            <div style="margin-top: 10px">
                <button id="submit" type="submit">统计</button>
                <button id="reset" type="reset">清空内容</button>
            </div>
        </form>
    </div>

    <div id="file_operate" style="margin-top: 10px">
        <form id="file_form" action="/charcounter/upload" method="post" enctype="multipart/form-data">
            <input id="file" type="file" name="file" accept="text/plain" onchange="handleFileChange(this.files)">
            <button type="submit">上传文件</button>
        </form>
        <button id="file_count" style="margin-top: 10px">统计</button>
    </div>

    <div id="result" style="margin-top: 20px">
        <div style="margin-bottom: 10px">各统计内容的个数如下</div>
        <table id="stats" border="1">
            <tr>
                <th>统计项</th>
                <th>个数</th>
            </tr>
            <tr>
                <td>英文字母</td>
                <td id="result_letter">0</td>
            </tr>
            <tr>
                <td>数字</td>
                <td id="result_number">0</td>
            </tr>
            <tr>
                <td>中文汉字</td>
                <td id="result_chinese">0</td>
            </tr>
            <tr>
                <td>中英文标点符号</td>
                <td id="result_punctuation">0</td>
            </tr>
        </table>
        <div style="margin-top:10px; margin-bottom: 10px">文字中出现频率最高的三个字符</div>
        <table id="top" border="1">
            <tr>
                <th>名称</th>
                <th>个数</th>
            </tr>
            <tr>
                <td id="top1_name">*</td>
                <td id="top1_value">0</td>
            </tr>
            <tr>
                <td id="top2_name">*</td>
                <td id="top2_value">0</td>
            </tr>
            <tr>
                <td id="top3_name">*</td>
                <td id="top3_value">0</td>
            </tr>
        </table>
    </div>
</div>

</body>
</html>
