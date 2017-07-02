<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>

<p>请选择一段文字：</p>

<form>

</form>

<form enctype="multipart/form-data">

    <div>
        <b>文本输入：</b><input type="radio" name="inputWords" id="inputWords" value="inputWords" checked> &nbsp;&nbsp;&nbsp;
        <b>文件上传：</b><input type="radio" name="inputWords" id="uploadFile" value="uploadFile">
    </div>

    <div id="showWord">
        <label for="insertWords"></label><textarea name="insertWords" id="insertWords" cols="30" rows="10"
                                                   placeholder="请输入文字..."></textarea>
    </div>
    <div id="showFile" style="display: none;">
        选择文件：<input type="file" id="file" name="file">
    </div>

    <input type="button" value="提交" id="submit">
    <input type="button" value="清空文字" id="clear">
</form>

<p> 各统计内容的个数如下：</p>
<table border="1" id="count1">
    <tr>
        <th>统计项</th>
        <th>个数</th>
    </tr>

</table>
<p>文字中出现频率最高的三个字是：</p>
<table border="1" id="count2">
    <tr>
        <th>名称</th>
        <th>个数</th>
    </tr>
</table>

</body>
<script type="text/javascript" src="js/jquery.js"></script>
<script>
    $(document).ready(function () {
        $(":radio").click(function () {
            if ($(this).val() === "inputWords") {
                $("#showWord").css("display", "block");
                $("#showFile").css("display", "none");
            } else {
                $("#showWord").css("display", "none");
                $("#showFile").css("display", "block")
            }
        });

        $("#clear").click(function () {
            $("#insertWords").val("");
        });

        $("#submit").click(function () {
            if ($(":radio:checked").val() === "inputWords") {
                var data = {
                    "words": $("#insertWords").val()
                };

                $.ajax({
                    url: "/uploadWords",
                    type: "POST",
                    contentType: "application/x-www-form-urlencoded",
                    data: data,
                    dataType: "json",
                    success: function (result) {
                        show(result);

                    }
                });
            } else {
                var request = new FormData();
                request.append("file", $('#file')[0].files[0]);
                $.ajax({
                    url: "/uploadFile",
                    type: "POST",
                    contentType: false,
                    processData: false,
                    data: request,
                    dataType: "json",
                    success: function (result) {
                        show(result)
                    }
                })

            }
        });
    });


    function show(result) {
        var data = result["count"];
        var str =
            "<tr> <th>统计项</th> <th>个数</th></tr>" +
            "<tr> <td>英文字母</td> <td>" + data["ascii"] + "</td>"
            + "<tr> <td>数字</td> <td>" + data["number"] + "</td>"
            + "<tr><td>中文汉字</td><td>" + data["chinese"] + "</td>"
            + "<tr><td>标点</td> <td>" + data["punctuation"] + "</td></tr>";

        $("#count1").html(str);
        data = result["top"];
        str = "<tr><th>名称</th><th>个数</th></tr>";

        $.each(data, function (index, entry) {
            for (key in entry) {
                str += "<tr><td>" + key + "</td><td>" + entry[key] + "</td></tr>"
            }
        });

        $("#count2").html(str);
    }
</script>
</html>
