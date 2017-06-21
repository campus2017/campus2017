<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="import.css" type="text/css">
    <title>文本统计</title>
</head>
<body>
<div class="py-5">
    <div class="container">
        <div class="row" id="titleRow">
            <div class="col-md-12" id="title">
                <h1 class="">文本统计</h1>
            </div>
        </div>
        <div class="row" id="formRow">
            <div class="col-md-12">
                <div class="row"> <label class="m-1 p-1"><input name="choose" type="radio" value="0" checked="">文本输入</label> <label class="m-1 p-1"><input name="choose" type="radio" value="1">文件上传 </label> </div>
                <div class="row" id="inputRow">
                    <form class="form-horizontal">
                        <div class="form-group"> <textarea name="inputText" class="form-control" rows="8" placeholder="输入待统计的文本" id="textarea"></textarea> </div>
                        <button type="button" class="btn btn-primary" id="statisticsinput">统计</button>
                        <button type="button" class="btn btn-primary" id="clear">清空</button>
                    </form>
                </div>
                <div class="row" id="fileRow" style="display:none">
                    <form  class="form-inline">
                        <div class="form-group">
                            <input type="file" name="file" id="uploadfile">
                        </div>
                        <div class="form-group">
                            <button type="button" class="btn btn-primary" id="statisticsfile">统计</button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="row" id="resultRow">
                <div class="col-md-12" id="mytitle">
                    <p class="text-info">统计内容：</p>
                    <table class="table table-hover table-bordered" id="resultTable">
                        <thead>
                        <tr>
                            <th> 统计项 </th>
                            <th> 个数 </th>
                        </tr>
                        </thead>
                        <tbody id="resultTbody">
                        <tr>
                            <td> 英文字母 </td>
                            <td> </td>
                        </tr>
                        <tr>
                            <td> 数字 </td>
                            <td> </td>
                        </tr>
                        <tr>
                            <td> 中文汉字 </td>
                            <td> </td>
                        </tr>
                        <tr>
                            <td> 中英文标点符号 </td>
                            <td> </td>
                        </tr>
                        </tbody>
                    </table>
                    <p class="text-info">文本中出现频率最高的3个字符：</p>
                    <table class="table table-hover table-bordered">
                        <thead>
                        <tr>
                            <th> 名称 </th>
                            <th> 个数 </th>
                        </tr>
                        </thead>
                        <tbody id="topbody">
                        <tr>
                            <td> </td>
                            <td> </td>
                        </tr>
                        <tr>
                            <td> </td>
                            <td> </td>
                        </tr>
                        <tr>
                            <td> </td>
                            <td> </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
    <script src="https://pingendo.com/assets/bootstrap/bootstrap-4.0.0-alpha.6.min.js"></script>
    <script>
        function resulttoTable(result){
            var tbody=$("#resultTbody");
            tbody.children().eq(0).children().eq(1).text(result.en);
            tbody.children().eq(1).children().eq(1).text(result.num);
            tbody.children().eq(2).children().eq(1).text(result.ch);
            tbody.children().eq(3).children().eq(1).text(result.pu);
            var topbody=$("#topbody");
            for(var i=0;i<=result.Topelement.length-1;i++)
            {
                if(result.Topelement[i]==" ")
                    topbody.children().eq(i).children().eq(0).text("空格");
                else if(result.Topelement[i]=="\t")
                    topbody.children().eq(i).children().eq(0).text("制表符");
                else if(result.Topelement[i]=="\n")
                    topbody.children().eq(i).children().eq(0).text("换行符");
                else
                    topbody.children().eq(i).children().eq(0).text(result.Topelement[i]);
                topbody.children().eq(i).children().eq(1).text(result.Topcount[i]);
            }

        }
        $(function () {
            $("input:radio[name=choose]").change(function () {
                $("#inputRow").toggle();
                $("#fileRow").toggle()
            });
            $("#clear").click(function(event) {
                $("#textarea").val("");
            });
            $("#statisticsinput").click(function(event) {
                $.ajax({
                    type: "POST",
                    url: "inputtext",
                    data: {inputText:$("#textarea").val()},
                    dataType: "json",
                    success: function(result){
                        console.log(result);
                        resulttoTable(result);

                    }
                });
            });


            $("#statisticsfile").click(function(event) {
                var filedata=new FormData();
                filedata.append("file",$("#uploadfile").get(0).files[0]);
                console.log(filedata.get("file"));
                $.ajax({
                    type: "POST",
                    url: "uploadfile",
                    data: filedata,
                    contentType: false,
                    processData: false,
                    success: function(result){
                        console.log(result);
                        resulttoTable(result);

                    }
                });
            });

        });

    </script>
</div>
</body>

</html>

