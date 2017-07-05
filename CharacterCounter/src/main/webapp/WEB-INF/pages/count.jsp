<%--
  Created by IntelliJ IDEA.
  User: dayong.gao
  Date: 2016/12/12
  Time: 11:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Demo</title>
    <link href="../css/Demo.css" rel="stylesheet" type="text/css"/>
    <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <script src="../js/jquery-2.2.1.min.js"></script>
    <script src="../js/Demo.js"></script>
    <script src="../js/jquery-form.js"></script>

    <script type="text/javascript">

    </script>
</head>
<body>
<div class="container">
    <div class="box">
        <h4>请选择一段文字</h4>
        <div class="content top">
            <input type="radio" name="choose" id="submit" value="上传文件" checked="true"/>文件上传
            <input type="radio" name="choose" id="input" value="文本输入"/>文本输入
            <div class="box1">
                <form id="uploadform" method="post" action="/upLoadCount" enctype="multipart/form-data">
                    <div class="div0">
                        <a class="file">选择文件
                        <input type="file" name="file">
                    </a>
                    </div>
                    <div id="showFileName" class="div1"></div>
                </form>
            </div>
            <div class="box2">
                <textarea id="strdata" placeholder="请在此输入文本内容"></textarea>
            </div>
            <input type="button" id="count" class="button bt1 btn btn-info" value="统计"/>
            <input type="button" class="button bt2 btn btn-warning" id="clear" value="清空内容"/>

        </div>
        <div class="content">
            <h5>各统计内容的个数如下:</h5>
            <table>
                <tr class="header">
                    <td>统计项</td>
                    <td>个数</td>

                </tr>
                <tr>
                    <td>英文字母</td>
                    <td id="en_letter"></td>
                </tr>
                <tr>
                    <td>数字</td>
                    <td id="num"></td>
                </tr>
                <tr>
                    <td>中文汉字</td>
                    <td id="ch_letter"></td>
                </tr>
                <tr>
                    <td>中英文标点符号</td>
                    <td id="punctuation"></td>
                </tr>
            </table>
        </div>
        <div class="content">
            <h5>文字中出现频率最高的三个字符是:</h5>
            <table>
                <tr class="header">
                    <td>名称</td>
                    <td>个数</td>
                </tr>
                <tr>
                    <td id="char_top1"></td>
                    <td id="num_top1"></td>
                </tr>
                <tr>
                    <td id="char_top2"></td>
                    <td id="num_top2"></td>
                </tr>
                <tr>
                    <td id="char_top3"></td>
                    <td id="num_top3"></td>
                </tr>
            </table>
        </div>


    </div>
</div>
</body>
</html>