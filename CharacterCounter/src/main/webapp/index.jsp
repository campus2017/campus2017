<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文字统计</title>
    <meta charset="UTF-8">
      <script src="js/material.min.js"></script>
    <link rel="stylesheet" href="css/material.min.css"></link>
</head>
<body id="loading">

<div class="container">
    <ul id="myTab" class="nav nav-tabs">
        <li class="active">
            <a href="#upload" data-toggle="tab">
                文件上传
            </a>
        </li>
        <li>
            <a href="#text" data-toggle="tab">
                文本输入
            </a>
        </li>
    </ul>
    <div id="myTabContent" class="tab-content">
        <div class="tab-pane fade in active" id="upload">

            <form id="upload_form" action="/upload" method="post" enctype="multipart/form-data">
                <input type="file" name="file" id="upload_input">
            </form>

            <button class="btn btn-sm" onclick="upload()">开始统计</button>

        </div>
        <div class="tab-pane fade" id="text">
            <form id="text_form" action="/text" method="post">
                <label for="text_area">文本</label>
                <textarea id="text_area" name="textarea" cols="40" rows="10"></textarea>
            </form>

            <button class="btn btn-sm" onclick="text()">开始统计</button>
        </div>
    </div>

    <table class="table table-bordered">
        <caption>各统计内容个数如下：</caption>
        <thead>
        <tr>
            <th>统计项</th>
            <th>个数</th>
        </tr>
        </thead>
        <tbody id="count_table_body">
        <tr>
            <td>英文字母</td>
            <td id="count_table_letter"></td>
        </tr>
        <tr>
            <td>数字</td>
            <td id="count_table_num"></td>

        </tr>
        <tr>
            <td>中文汉字</td>
            <td id="count_table_chinese"></td>

        </tr>
        <tr>
            <td>中英文标点符号</td>
            <td id="count_table_pun"></td>
        </tr>
        </tbody>
    </table>

    <table class="table table-bordered">
        <caption>文字中出现频率最高的三个字是：</caption>
        <thead>
        <tr>
            <th>名称</th>
            <th>个数</th>
        </tr>
        </thead>
        <tbody id="top_table_body">

        </tbody>
    </table>
</div>


<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/json2/20160511/json2.min.js"></script>

<script type="text/javascript">
    function upload() {
        $("#loading").showLoading();
        if (window.FormData) {
            var formData = new FormData();
            // 建立一个upload表单项，值为上传的文件
            formData.append('file', document.getElementById('upload_input').files[0]);

            var xhr = new XMLHttpRequest();
            xhr.open('POST', $('#upload_form').attr('action'));
            // 定义上传完成后的回调函数
            xhr.onreadystatechange = function () {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    var array = JSON.parse(xhr.responseText);
                    createTable(array);
                }
            };
            xhr.send(formData);
        }
    }

    function text() {
        $("#loading").showLoading();

        if (window.FormData) {
            var formData = new FormData();
            // 建立一个upload表单项，值为上传的文件
            formData.append('text', $("#text_area").val());

            var xhr = new XMLHttpRequest();
            xhr.open('POST', $('#text_form').attr('action'));
            // 定义上传完成后的回调函数
            xhr.onreadystatechange = function () {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    var array = JSON.parse(xhr.responseText);
                    createTable(array);
                }
            };
            xhr.send(formData);
        }
    }

    function createTable(json) {
        $('#count_table_letter').html(json.letterCount);
        $('#count_table_num').html(json.numCount);
        $('#count_table_chinese').html(json.chineseCount);
        $('#count_table_pun').html(json.punctuationCount);

        var body = $("#top_table_body");
        body.empty();

        var top = json.top;
        for (var node in top) {
            var tr = document.createElement("tr");

            var td = document.createElement("td");
            td.innerHTML = top[node].c;
            tr.appendChild(td);

            var td = document.createElement("td");
            td.innerHTML = top[node].count;
            tr.appendChild(td);

            body.append(tr);
        }

        $("#loading").hideLoading();
    }
</script>

</body>
</html>
