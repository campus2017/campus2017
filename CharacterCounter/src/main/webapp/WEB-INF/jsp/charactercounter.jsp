<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
    <title>CharacterCounter</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script language=javascript>
        function init(){
            document.getElementById("div1").style.display="block";
            document.getElementById("div2").style.display="none";
        }
        function check(flag){
            if(flag == true){
                document.getElementById("div1").style.display="block";
                document.getElementById("div2").style.display="none";
            }else{
                document.getElementById("div1").style.display="none";
                document.getElementById("div2").style.display="block";
            }
        }
        var file;
        function handleFile(){
            file = document.getElementById("upfile");
            var fileName = document.getElementById("fileName");
            fileName.value = file.value;
        }
        function showFile(){
            var fileName = document.getElementById("fileName");
            fileName.value = file.value;
        }
        function reset(){
            document.getElementById("div1").style.display="block";
            document.getElementById("div2").style.display="none";
        }
    </script>
</head>
<meta charset="UTF-8">
<style type="text/css">
    .tp{border:solid #add9c0; border-width:1px 0px 0px 1px;border-color:#000;font-size:15px;width:320px;}
    td{border:solid #add9c0; border-width:0px 1px 1px 0px; width:160px;height:30px;border-color:#000;text-align:center;}
    th{border:solid #add9c0; border-width:0px 1px 1px 0px; width:160px;height:30px;border-color:#000;}

    .ui_button {
        position: relative;
        display: inline-block;
        line-height: 20px;
        line-width: 60px;
        color: #FFFFFF;
        background: #4EE2EC;
        font-family: "黑体";
        text-decoration: none;
        padding: 4px 12px;
        border:none;
        font-size: 15px;
    }

    .file {
        position: relative;
        display: inline-block;
        background: #4EE2EC;
        font-family: "黑体";
        padding: 4px 12px;
        overflow: hidden;
        color: #FFFFFF;
        text-decoration: none;
        text-indent: 0;
        line-height: 20px;
        line-width: 60px;
        font-size: 15px;
    }
    .file input {
        position: absolute;
        right: 0;
        top: 0;
        opacity: 0;
    }
    .file:hover {
        background: #AADFFD;
        border-color: #78C3F3;
        color: #004974;
        text-decoration: none;
    }

    ::-ms-browse, [type='file'] {
        padding: .4em;
        line-height: 24px;
        border: 1px solid #a0b3d6;
        background: #f0f3f9;
        color: #34538b;
    }
    ::-webkit-file-upload-button {
        padding: .4em;
        line-height: 30px;
        border: 1px solid #a0b3d6;
        background: #f0f3f9;
        color: #34538b;
    }
</style>
<body onload="init()">
<div style="border:solid 1px #add9c0;margin:0 auto;width:480px;height:525px;align:center;border-color:#000;padding:10px 10px 10px 10px;font-size:15px;">
    <p><b>请选择一段文字</b></p>
    <div style="padding:0px 0px 0px 30px;">

            <div>

                <input type="radio" name="upload" value="1" checked="true" onclick="check(true)">文件上传&nbsp;&nbsp;&nbsp;
                <input type="radio" name="upload" value="2" onclick="check(false)">文本输入

                <div id=div1 style="height:35px;">
                    <br>
                    <form method="post" action="/doUpFile" enctype="multipart/form-data">
                    <a href="javascript:;" class="file">上传文件
                        <input type="file" id="upfile" name="file" onchange="handleFile()">
                    </a>
                    <input type="text" value="" id="fileName" disabled="disable" style="width:200px;background:none;border:0;position:relative;top:-10px;">
                    <input class="ui_button" type="submit" value="统计" id="bt1" style="left:30px;top:-10px;width:80px;">
                    </form>
                </div>

                <div id=div2 style="height:50px;">
                    <br>
                    <form method="post" action="/doUpText">
                    <table style="border:none;">
                    <tr>
                        <td rowspan="2" colspan="3" style="border:none;"><textarea name="text" cols="43" rows="4" placeholder="请在此输入文本内容" style="overflow-y:scroll"></textarea></td>
                        <td style="border:none;"><input class="ui_button" type="submit" value="统计" id="bt2" style="width:80px;"></td>
                    </tr>
                    <tr>
                        <td style="border:none;"><input class="ui_button" type="button" value="清空内容" onclick="reset()" id="bt3" style="background:#FFA500;width:80px;"></td>
                    </tr>
                    </table>
                    </form>
                </div>
            </div>

        <br>
        <br>
        <div>
        <p >各统计内容的个数如下:</p>
        <table class="tp" cellspacing="0" cellpadding="5">
            <tr>
                <th>统计项</th>
                <th>个数</th>
            </tr>
            <tr>
                <td>英文字母</td>
                <td>${letter}</td>
            </tr>
            <tr>
                <td>数字</td>
                <td>${number}</td>
            </tr>
            <tr>
                <td>中文汉字</td>
                <td>${chinese}</td>
            </tr>
            <tr>
                <td>中英文标点符号</td>
                <td>${punctuation}</td>
            </tr>
        </table>
        <p></p>
        <p>文字中出现频率最高的三个字是:</p>
        <table class="tp" cellspacing="0" cellpadding="5">
            <tr>
                <th>名称</th>
                <th>个数</th>
            </tr>
            <tr>
                <td>${firstChar}</td>
                <td>${firstNum}</td>
            </tr>
            <tr>
                <td>${secondChar}</td>
                <td>${secondNum}</td>
            </tr>
            <tr>
                <td>${thirdChar}</td>
                <td>${thirdNum}</td>
            </tr>
        </table>
        </div>
    </div>
</div>
</body>
</html>