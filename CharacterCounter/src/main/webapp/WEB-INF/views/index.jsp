<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<div class="container">
    <div class="row">
        <h2>请输入一段文字</h2>
        <div>
            <label class="checkbox-inline">
                <input type="radio" name="radio" id="uploadStyle" value="上传文件" onClick=showUpload() checked>上传文件
            </label>
            <label class="checkbox-inline">
                <input type="radio" name="radio" id="inputStyle" value="文本输入" onClick=showInput()>文本输入
            </label>
        </div>
        <div id="tab1">
            <form action="file.do" method="post" enctype="multipart/form-data" accept-charset="UTF-8">
                <div class="col-md-8">
                    <input type="file" class="btn btn-info"  name="file"/>
                </div>
                <div class="col-md-4">
                    <input type="submit" class="btn btn-info" value="统计"/>
                </div>
            </form>
        </div>
        <div id="tab2" style="display: none;">
            <form action="text.do" method="post">
                <div class="col-md-8 form-group">
                    <textarea name="text" rows="5" cols="45"></textarea>
                </div>
                <div class="col-md-4">
                    <input type="submit" id="submit" class="btn btn-info" value=" 统计 " tabindex="3" />
                    <button type="reset" id="reset" class="btn btn-warning" tabindex="4">清空内容</button>
                </div>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-md-8">
            <h3>各统计内容的个数如下：</h3>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>统计项</th>
                    <th>个数</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>英文字母</td>
                    <td>${Result.alphaCount}</td>
                </tr>
                <tr>
                    <td>数字</td>
                    <td>${Result.numberCount}</td>
                </tr>
                <tr>
                    <td>中文汉字</td>
                    <td>${Result.chineseCount}</td>
                </tr>
                <tr>
                    <td>中英文标点符号</td>
                    <td>${Result.otherCount}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col-md-8">
            <h3>文字中出现频率最高的三个字是：</h3>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>名称</th>
                    <th>个数</th>
                </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>${Result.chara1}</td>
                        <td>${Result.num1}</td>
                    </tr>
                    <tr>
                        <td>${Result.chara2}</td>
                        <td>${Result.num2}</td>
                    </tr>
                    <tr>
                        <td>${Result.chara3}</td>
                        <td>${Result.num3}</td>
                    </tr>
                </tbody>
            </table>

        </div>
    </div>
</div>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<script type="text/javascript">
    function showUpload()
    {

        document.getElementById("tab1").style.display="";

        document.getElementById("tab2").style.display="none";

    }

    function showInput()
    {

        document.getElementById("tab1").style.display="none";

        document.getElementById("tab2").style.display="";

    }

</script>
</body>
</html>