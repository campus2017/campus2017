<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <title>qunar homework</title>
    <script src="js/jquery-3.2.1.min.js" language="JavaScript" type="text/javascript"></script>
    <script src="js/bootstrap.js" language="javascript" type="text/javascript"></script>
    <script src="js/fileinput.min.js" language="javascript" type="text/javascript"></script>
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <!-- Custom styles for this template -->
    <link href="css/jumbotron-narrow.css" rel="stylesheet" type="text/css"/>
    <link href="css/fileinput.min.css" rel="stylesheet" type="text/css"/>

</head>


<body>

<div class="container">
    <div class="header clearfix">

        <h3 class="text-muted">CharacterCounter</h3>
    </div>

    <div class="row marketing">

        <div class="col-lg-12">
            <h4>请选择下述方式</h4>
            <div>
                <!-- Nav tabs -->
                <ul class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">文件输入</a></li>
                    <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">文件上传</a></li>
                </ul>

                <!-- Tab panes -->
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="home">
                        <div>
                            <form role="form" action="/largework1/calculator" method="post">
                                <p><h4>请输入一段文字</h4></p>
                                <div class="col-lg-8">
                                    <div class="form-group">
                                        <textarea name="textcal" class="form-control pre-scrollable" rows="5" placeholder="请在此输入文字">${text}</textarea>
                                    </div>
                                </div>
                                <div class="col-lg-2">
                                    <button type="submit" class="btn btn-info">确定</button>
                                    <button type="reset" class="btn btn-warning">清空</button>
                                </div>
                            </form>
                        </div>
                        <div class="col-lg-12">
                            <h4>各统计内容如下</h4>
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th>统计项</th>
                                    <th>个数</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>英文字母</td>
                                    <td>${count.englishWordNum}</td>
                                </tr>
                                <tr>
                                    <td>数字</td>
                                    <td>${count.numberNum}</td>
                                </tr>
                                <tr>
                                    <td>中文汉字</td>
                                    <td>${count.chineseWordNum}</td>
                                </tr>
                                <tr>
                                    <td>标点符号</td>
                                    <td>${count.punctuationNum}</td>
                                </tr>
                                </tbody>
                            </table>

                            <h4>文字中出现频率最高的三个字是</h4>
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th>名称</th>
                                    <th>个数</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${count.map}" var="m">
                                    <tr>
                                        <td id="hihi">${m.key}</td>
                                        <td>${m.value}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="profile">
                        <div>
                        <form id="uploadForm"  enctype="multipart/form-data" onsubmit="return doUpload()">
                        <label class="control-label">请上传要统计的文件:</label>
                        <input id="input-7" name="input7[]" multiple type="file"
                               class="file file-loading" data-allowed-file-extensions='["csv", "txt","pdf"]' data-show-preview="false">
                        </form>
                        </div>
                        <div class="col-lg-12">
                            <h4>各统计内容如下</h4>
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th>统计项</th>
                                    <th>个数</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>英文字母</td>
                                    <td id="eW"></td>
                                </tr>
                                <tr>
                                    <td>数字</td>
                                    <td id="nN"></td>
                                </tr>
                                <tr>
                                    <td>中文汉字</td>
                                    <td id="cW"></td>
                                </tr>
                                <tr>
                                    <td>标点符号</td>
                                    <td id="pN"></td>
                                </tr>
                                </tbody>
                            </table>

                            <h4>文字中出现频率最高的三个字是</h4>
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th>名称</th>
                                    <th>个数</th>
                                </tr>
                                </thead>
                                <tbody id="threeMap">
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

            </div>


        </div>

    </div>

    <footer class="footer">
        <p>&copy; 2017</p>
    </footer>

</div> <!-- /container -->

<script type="text/javascript">

    function doUpload() {
        var formData = new FormData($( "#uploadForm" )[0]);
        var n=false;
        $.ajax({
            url: '/largework1/uploadCalculator' ,
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (returndata) {
                var count =JSON.parse(returndata);
                var map=count.map;
                var eW=count.eW;
                var cW=count.cW;
                var pN=count.pN;
                var nN=count.nN;
                document.getElementById("eW").innerHTML=eW;
                document.getElementById("cW").innerHTML=cW;
                document.getElementById("pN").innerHTML=pN;
                document.getElementById("nN").innerHTML=nN;
                var s="";
                for(var key in map){
                    s=s+"<tr> <td>"+key+"</td><td>"+map[key]+"</td> </tr>";
                }
                document.getElementById("threeMap").innerHTML=s;
            },
            error: function (returndata) {
                alert("this is wrong");
            }

        });
        return false;
    }
</script>
</body>
</html>
