<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/24
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>CharacterCounter</title>
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/index.js"></script>
    <style type="text/css">
        .table th, .table td {
            text-align: center;
            vertical-align: middle!important;
        }
        .table th {
            width: 50%;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>请选择一段文字</h1>
    <ul id="myTab" class="nav nav-tabs">
        <li class="active">
            <a href="#fileUpload" data-toggle="tab">
                文件上传
            </a>
        </li>
        <li>
            <a href="#textInput" data-toggle="tab">
                文本输入
            </a>
        </li>
    </ul>
    <div id="myTabContent" class="tab-content">
        <div class="tab-pane fade in active" id="fileUpload">
            <form class="form" action="<c:url value="/home/file_upload"/>" method="post" enctype="multipart/form-data">
                <div class="container" style="margin-top: auto">
                    <div class="col-sm-10 col-md-10 col-lg-10" style="margin-top: 8px">
                        <input type="file" name="file" id="file">
                    </div>
                    <div class="col-sm-2 col-md-2 col-lg-2">
                        <button type="submit" class="btn btn-primary" style="margin-top: 2px">统计</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="tab-pane fade" id="textInput">
            <form class="form" action="<c:url value="/home/text_input"/>" method="post" role="form">
            <div class="container" style="margin-top: auto">
                <div class="col-sm-10 col-md-10 col-lg-10">
                    <div class="form-group" >
                        <textarea name="text" id="text" class="form-control" rows="3" style="margin-top: 2px"></textarea>
                    </div>
                </div>
                <div class="col-sm-2 col-md-2 col-lg-2">
                    <div>
                        <button type="submit" class="btn btn-primary submit-btn submit-text" style="margin-top: 2px">统计</button>
                    </div>
                    <div>
                        <button type="reset" id="reset" class="btn btn-info" style="margin-top: 2px">清空</button>
                    </div>
                </div>
            </div>
            </form>
        </div>
    </div>
    <div class="text-danger msg">&nbsp;</div>
    <div class="container">
        <div>
            <p>各统计内容的个数如下：</p>
            <table class="table table-striped table-bordered table-hover table-responsive">
                <thead>
                <tr>
                    <th>统计项</th>
                    <th>个数</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>英文字母</td>
                    <td id="engAlphabet"></td>
                </tr>
                <tr>
                    <td>数字</td>
                    <td id="number"></td>
                </tr>
                <tr>
                    <td>中文汉字</td>
                    <td id="chiCharacters"></td>
                </tr>
                <tr>
                    <td>中英文标点符号</td>
                    <td id="punctuation"></td>
                </tr>
                </tbody>
            </table>
        </div>

        <div>
            <p>文字中出现频率最高的三个字是：</p>
            <table class="table table-striped table-bordered table-hover table-responsive">
                <thead>
                <tr>
                    <th>名称</th>
                    <th>个数</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td id="top0">&nbsp;</td>
                    <td id="count0"></td>
                </tr>
                <tr>
                    <td id="top1">&nbsp;</td>
                    <td id="count1"></td>
                </tr>
                <tr>
                    <td id="top2">&nbsp;</td>
                    <td id="count2"></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
