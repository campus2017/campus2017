<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>字符统计 CharacterCounter</title>
    <style type="text/css">
        .main {
            margin: 10px;
            width: 400px;
        }
        .tab {
            height: 100px;
            width: 100%;
            padding: 10px;
        }
        .tab textarea {
            width: 240px;
            height: 80px;
        }
        #upload {
            position:absolute;margin-top: 10px; margin-left:35px;
        }
        .bluebutton {
            margin: 10px;
            padding: 5px;
            width: 80px;
            background-color: #00ccff;
            text-align: center;
        }
        .bluebutton a {
            color: #ffffff;
            text-decoration: none;
        }

        .box {
            border-top: solid 1px;
            border-left: solid 1px;
        }
        .box th {
            border-bottom: solid 1px;
            border-right: solid 1px;
            padding: 5px;
            width: 180px;
            text-align: center;
        }
        .box td {
            border-bottom: solid 1px;
            border-right: solid 1px;
            padding: 5px;
            width: 180px;
            text-align: center;
        }
    </style>


    <script type="text/javascript">
        function showfile() {
            document.getElementById('file').style.display = '';
            document.getElementById('text').style.display = 'none';
        }
        function showtext() {
            document.getElementById('file').style.display = 'none';
            document.getElementById('text').style.display = '';
        }

        function countfile() {
            var file = document.getElementById('upload').files[0];
            if (file) {
                var reader = new FileReader();
                reader.readAsText(file,'gbk');
                reader.onload = function (e) {
                    var xmlhttp = new XMLHttpRequest();
                    xmlhttp.onreadystatechange = function() {
                        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                            var result = eval('(' + xmlhttp.responseText + ')');
                            // console.log(result);
                            document.getElementById('Eletter').innerText = result.english;
                            document.getElementById('number').innerText = result.number;
                            document.getElementById('Cletter').innerText = result.chinese;
                            document.getElementById('punc').innerText = result.punctuation;
                            document.getElementById('letter1').innerText = result.top3[0].ch;
                            document.getElementById('count1').innerText = result.top3[0].count;
                            document.getElementById('letter2').innerText = result.top3[1].ch;
                            document.getElementById('count2').innerText = result.top3[1].count;
                            document.getElementById('letter3').innerText = result.top3[2].ch;
                            document.getElementById('count3').innerText = result.top3[2].count;
                        }
                    }
                    xmlhttp.open("POST", "/countfile", true);
                    xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
                    xmlhttp.send("text=" +encodeURIComponent(encodeURIComponent(this.result)));// POST提交数据是编码后的格式
                }
            } else {
                alert('请选择文件！')
            }
        }

        function counttext() {
            var xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function() {
                if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                    var result = eval('(' + xmlhttp.responseText + ')');
                    console.log(result);
                    document.getElementById('Eletter').innerText = result.english;
                    document.getElementById('number').innerText = result.number;
                    document.getElementById('Cletter').innerText = result.chinese;
                    document.getElementById('punc').innerText = result.punctuation;
                    document.getElementById('letter1').innerText = result.top3[0].ch;
                    document.getElementById('count1').innerText = result.top3[0].count;
                    document.getElementById('letter2').innerText = result.top3[1].ch;
                    document.getElementById('count2').innerText = result.top3[1].count;
                    document.getElementById('letter3').innerText = result.top3[2].ch;
                    document.getElementById('count3').innerText = result.top3[2].count;
                }
            }
            xmlhttp.open("POST", "/counttext", true);
            xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
            xmlhttp.send("text=" +encodeURIComponent(encodeURIComponent(document.getElementById('textinput').value)));// POST提交数据是编码后的格式
        }

        function clean() {
            document.getElementById("textinput").value = "";
        }
    </script>
</head>
<body>
<h4>请选择一段文字</h4>
<div class="main">
    <form>
        <input type="radio" name="texttype" onclick="showfile()" checked />文件上传
        <input type="radio" name="texttype" onclick="showtext()"/>文本输入

        <div id="file" class="tab">
            <input type="file" name="file" id="upload">
            <div style="float: left; position:absolute" class="bluebutton">
                <a href="javascript:void(0)" onclick="document.getElementById('upload').click()">上传文件</a>
            </div>
            <div style="float: right;" class="bluebutton">
                <a href="javascript:void(0)" onclick="countfile(); return false"> 统计 </a>
            </div>
        </div>

        <div id="text" class="tab" style="display: none;">
            <textarea id="textinput" autofocus placeholder="请在此输入文本内容" style="resize:none;"></textarea>
            <div style="float: right">
                <div class="bluebutton">
                    <a href="javascript:void(0)" onclick="counttext(); return false"> 统计 </a>
                </div>
                <div class="bluebutton" style="background-color: #FF9900">
                    <a href="javascript:void(0)" onclick="clean(); return false">清空内容</a>
                </div>
            </div>
        </div>
    </form>


    <p>各统计内容的个数如下：</p>
    <table cellspacing="0" cellpadding="0" class="box">
        <tr>
            <th>统计项</th>
            <th>个数</th>
        </tr>
        <tr>
            <td>英文字母</td>
            <td id="Eletter">0</td>
        </tr>
        <tr>
            <td>数字</td>
            <td id="number">0</td>
        </tr>
        <tr>
            <td>中文汉字</td>
            <td id="Cletter">0</td>
        </tr>
        <tr>
            <td>中英文标点符号</td>
            <td id="punc">0</td>
        </tr>
    </table>
    <p>文字中出现频率最高的三个字是：</p>
    <table cellspacing="0" cellpadding="0" class="box">
        <tr>
            <th>名称</th>
            <th>个数</th>
        </tr>
        <tr>
            <td id="letter1">&nbsp;</td>
            <td id="count1">&nbsp;</td>
        </tr>
        <tr>
            <td id="letter2">&nbsp;</td>
            <td id="count2">&nbsp;</td>
        </tr>
        <tr>
            <td id="letter3">&nbsp;</td>
            <td id="count3">&nbsp;</td>
        </tr>
    </table>
</div>
</body>
</html>