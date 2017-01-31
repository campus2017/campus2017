<%--
  Created by IntelliJ IDEA.
  User: Fly
  Date: 2017/1/17
  Time: 16:17
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CharacterCounter</title>
</head>
<body>
    <h4><strong>请选择一段文字</strong></h4>
    <div>
        <label class="radio-inline">
            <input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1">文件上传
        </label>
        <label class="radio-inline">
            <input type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2" checked> 文本输入
        </label>
    </div>
    <div class="row">
        <div class="col-xs-12 col-sm-8 col-md-8">
            <textarea id="content" class="form-control" rows="3" placeholder="请在此输入文本内容"></textarea>
        </div>
        <div class="col-xs-6 col-sm-4 col-md-4">
            <div>
                <input id="btnCount" type="button" value="统计" class="btn btn-info">
            </div>
            <div>
                <input id="btnReset" type="button" value="清空内容" class="btn btn-warning">
            </div>
        </div>
    </div>
    <h5>各统计内容的个数如下：</h5>
    <div >
        <table class="table-bordered">
            <thead>
                <tr>
                    <th>统计项</th>
                    <th>个数</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>英文字母</td>
                    <td id="englishNum"></td>
                </tr>
                <tr>
                    <td>数字</td>
                    <td id="numberNum"></td>
                </tr>
                <tr>
                    <td>中文汉字</td>
                    <td id="chineseNum"></td>
                </tr>
                <tr>
                    <td>中英文标点符号</td>
                    <td id="pointNum"></td>
                </tr>
            </tbody>
        </table>
    </div>
    <h5>文字中出现频率最高的三个字是：</h5>
    <div>
        <table class="table-bordered">
            <thead>
                <tr>
                    <th>名称</th>
                    <th>个数</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td id="firstName"></td>
                    <td id="firstCount"></td>
                </tr>
                <tr>
                    <td id="secondName"></td>
                    <td id="secondCount"></td>
                </tr>
                <tr>
                    <td id="thirdName"></td>
                    <td id="thirdCount"></td>
                </tr>
            </tbody>
        </table>
    </div>
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/home.js"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/home.css">
</body>
</html>
