<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>homeworkTest</title>

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <div class="row">
        <h2>请输入一段文字</h2>
        <div>
            <label class="checkbox-inline">
                <input type="radio" name="radio" id="upload" value="上传文件" checked>文件上传
            </label>
            <label class="checkbox-inline">
                <input type="radio" name="radio" id="input" value="上传文件">文本输入
            </label>
        </div>
        <div id="tab1">
            <form:form role="form" commandName="context" action="file_save" method="post" enctype="multipart/form-data" accept-charset="UTF-8">
                <div class="col-md-8">
                    <input type="file" class="btn btn-info"  name="file"/>
                </div>
                <div class="col-md-4">
                    <input type="submit" class="btn btn-info" value="统计"/>
                </div>
            </form:form>
        </div>
        <div id="tab2" style="display: none;">
            <form:form role="form" commandName="context" action="context_save" method="post">
                <div class="col-md-8 form-group">
                    <form:textarea path="text" class="form-control" rows="5"></form:textarea>
                </div>
                <div class="col-md-4">
                    <input type="submit" id="submit" class="btn btn-info" value=" 统计 " tabindex="3" />
                    <button type="reset" id="reset" class="btn btn-warning" tabindex="4">清空内容</button>
                </div>
            </form:form>
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
                    <td>${statistics.abcs}</td>
                </tr>
                <tr>
                    <td>数字</td>
                    <td>${statistics.numbers}</td>
                </tr>
                <tr>
                    <td>中文汉字</td>
                    <td>${statistics.chinese}</td>
                </tr>
                <tr>
                    <td>中英文标点符号</td>
                    <td>${statistics.punctuation}</td>
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
                <c:forEach items="${statistics.top3.entrySet()}" var="entry">
                    <tr>
                        <td>${entry.getKey()}</td>
                        <td>${entry.getValue()}</td>
                    </tr>
                </c:forEach>
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
    $(function () {
        $("input[name='radio']").click(function () {
            if($("#upload").is(":checked")){
                $("#tab1").css('display','block')
                $("#tab2").css('display','none')
            }
            if($("#input").is(":checked")){
                $("#tab1").css('display','none')
                $("#tab2").css('display','block')
            }
        })
    })

</script>
</body>
</html>
