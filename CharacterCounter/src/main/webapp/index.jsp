<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD//XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
    <title>CharacterCounter</title>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery-3.1.1.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script type="text/javascript">
        $(document).ready(function () {
            /*var dataValue;
             $('input[type="file"]').change(function(e){
             dataValue = e;
             });*/
            $("#countFile").click(function () {
                countFile();
            });
            $("#countTextArea").click(function () {
                count("get", "contentCounter.do", $("#content").val());
            });
            $("#clear").click(function () {
                $("#content").val("");
                count("get", "contentCounter.do", $("#content").val());
            });
        });

        function count(type, url, dataValue) {
            $.ajax({
                type: type,
                url: url,
                data: {content: dataValue},
                dataType: "json",
                success: function (msg) {
                    $("#EnglishCharacter").html("zhangjian");
                    $("#ChineseCharacter").text(msg.chineseCharacter);
                    $("#number").text(msg.number);
                    $("#punctuation").text(msg.punctuation);
                    var html = "";
                    $.each(msg.mostThree, function (key, value) {
                        html = html + "<tr>";
                        html += "<td>" + key + "</td>" + "<td>" + value + "</td>";
                        html += "</tr>";
                    });
                    $("#mostThree").html(html);
                }
            });
        }

        function countFile() {
            var id = Math.random();
            $.ajax({
                url: "fileCounter.do?requestId=" + id,
                type: "post",
                cache: false,
                data: new FormData($("#submitFile")[0]),
                processData: false,
                contentType: false,
                success: function (data) {
                    var json = JSON.parse(data);
                    if (json.flag == true) {
                        var msg = json.content;
                        $("#EnglishCharacter").html(msg.englishCharacter);
                        $("#ChineseCharacter").text(msg.chineseCharacter);
                        $("#number").text(msg.number);
                        $("#punctuation").text(msg.punctuation);
                        var html = "";
                        $.each(msg.mostThree, function (key, value) {
                            html = html + "<tr>";
                            html += "<td>" + key + "</td>" + "<td>" + value + "</td>";
                            html += "</tr>";
                        });
                        $("#mostThree").html(html);
                    } else if(json.type == false){
                        alert("不支持的文件类型");
                    }else{
                        alert("解析文件失败");
                    }
                },
                error: function (data) {
                    alert("上传文件失败");
                }
            });
        }
    </script>
</head>
<body>
<div class="container">
    <div class="col-md-12">
        <h4>请选择一段文字</h4>
        <!-- Nav tabs -->
        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">文件上传</a>
            </li>
            <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">文本输入</a>
            </li>
        </ul>

        <!-- Tab panes -->
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="home">
                <form id="submitFile" enctype="multipart/form-data">
                    <input id="file" type="file" name="file"/>
                    <input type="button" id="countFile" value="统计"/>
                </form>
            </div>
            <div role="tabpanel" class="tab-pane" id="profile">

                <textarea id="content" rows="10" cols="40" name="content"></textarea>
                <button id="countTextArea">统计</button>
                <button id="clear">清除内容</button>

            </div>
        </div>
    </div>

    <div class="col-md-12" style="margin-top: 20px">
        <div class="col-md-12">
            <h5>各统计内容如下：</h5>
            <table id="table1" class="table-bordered col-md-3">
                <thead>
                <tr>
                    <th>统计项</th>
                    <th>个数</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>英文字母</td>
                    <td id="EnglishCharacter"></td>
                </tr>
                <tr>
                    <td>数字</td>
                    <td id="number"></td>
                </tr>
                <tr>
                    <td>中文汉字</td>
                    <td id="ChineseCharacter"></td>
                </tr>
                <tr>
                    <td>中英文标点符号</td>
                    <td id="punctuation"></td>
                </tr>
                </tbody>
            </table>
        </div>

        <div class="col-md-12" style="margin-top: 20px">
            <h5>文字中出现频率最高的三个字符是：</h5>
            <table id="table2" class="table-bordered col-md-3">
                <thead>
                <tr>
                    <th>名称</th>
                    <th>个数</th>
                </tr>
                </thead>
                <tbody id="mostThree">

                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>