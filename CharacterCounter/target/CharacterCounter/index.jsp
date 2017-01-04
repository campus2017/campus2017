<%@ page isELIgnored="false" language="java"  pageEncoding="UTF-8" autoFlush="true"  contentType="text/html;charset=utf-8" %>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>单词统计页面</title>
    <style type="text/css" media="screen">
        *{margin:0;padding:0;font-family:'SimSun';}
        input[ type = radio ] {
            margin-right: 2px;

        }
        .button_font_style {
            color:white;
            font:12px 'Microsoft YaHei';
        }
        .container{
            padding:20px 10px;
            margin: 0px auto;
            height: 800px;
            width: 500px;
        }
        .form_wrap{
            width: 100%;
            padding:10px 0px;
            height: 100px;
        }
        .input_wrap{
            diaplay:block;
            margin-bottom:10px;
            font-size: 12px;
        }
        .upload_div{
            height: 50px;
            padding-top: 30px;
        }
        .bule_btn{
            background-color:cyan;
            width: 60px;
            height: 25px;
            border: none;
            outline: none;
            cursor: pointer;
        }
        .file_btn{
            height: 25px;
            width: 100px;
            border: none;
            outline: none;
            cursor: pointer;
        }
        .text_div{
            display: none;
            height: 50px;
        }
        .text_div > div{
            float:left;
        }
        input[ name = up_btn ] {
            float: left;
        }
        input[ name = text_btn ] {
            float: right;
        }
        table thead th{
            font:12px bolder 'Microsoft YaHei';
            text-align: center;
        }
        table tbody td{
            font:12px 'Microsoft YaHei';
            text-align: center;
        }
        .result_wrap{
            margin-top: 20px;
        }
        .table1{
            margin-top: 10px;
            height: 300px;
            width: 350px;
            border-collapse: collapse;
        }
        .table1 tr{
            height: 60px;
        }
        .table1 th{
            width: 50%;
        }
        .table2{
            margin-top: 10px;
            height: 200px;
            width: 350px;
            border-collapse: collapse;
        }
        .table2 tr{
            height: 50px;
        }
        .table2 th{
            width: 50%;
        }
        .button_wrap{
            width: 100px;
            padding: 6px 30px;
        }
        .textarea_wrap textarea{
            font-size:16px;
            color:#666;
            padding-left: 4px;
            overflow-y:scroll;
        }
    </style>
</head>
<body>
<div class="container">
    <p style="font-size:14px;font-weight:bolder;">请选择一段文字</p>
    <div style="margin:10px 20px;width: 460px;height: 700px;">
        <script  type="text/javascript" charset="utf-8" >
            var viewFunc = {
                'uploadView':function(show){
                    var upload =document.getElementById('countFileForm')
                    upload.style.display = show?'block':'none';
                },
                'textView':function(show){
                    var text =document.getElementById('countTextForm')
                    text.style.display = show?'block':'none';
                }
            }
            var View = [viewFunc['uploadView'],viewFunc['textView']]
            var index = 0;
            function change(eve){
                tabIndex = parseInt(eve.value);
                View[tabIndex].call(this,true)
                View[index].call(this,false)
                index = tabIndex ;
            }

        </script>
        <div class="form_wrap">
                <div class="input_wrap">
                    <input type="radio" name="type"  onclick="change(this)" checked value="0" >文件上传</input>
                    <input type="radio" name="type"  onclick="change(this)"  value="1" style="margin-left:30px;">文本输入</input>
                </div>
                <form id="countFileForm" style="width:100%;height:100%;" class="input_wrap upload_div"  action="/fileCount" >
                    <input type="file" name="up_file" name="99" width="100" onchange="filename.value=value" value=""  style="display:none;"/>
                    <input class="button_font_style bule_btn"  type="button" name="up_btn"  value="上传文件" onclick="up_file.click()"/>
                    <input type="text" name="filename"  style="margin-left:30px;border:none;outline:none;width:300px;height:25px;overflow-x:auto;" readonly="true"/>
                    <input class="button_font_style bule_btn"   type="button" name="text_btn" value="统计"/>
                </form>
                <form id="countTextForm" style="width:100%;height:100%;"  class="input_wrap text_div" action="/wordCount.servlet" >
                    <div class="textarea_wrap">
                        <textarea id="text_content" name="content"  cols="34" rows="4"></textarea>
                    </div>
                    <div class="button_wrap">
                        <button id="count_btn" class="button_font_style bule_btn"   value="统计">统计</button>
                        <button id="clear_btn" class="button_font_style bule_btn" style="background-color:orange;margin-top:10px;" value="清空内容">清空内容</button>
                    </div>
                </form>
        </div>
        <label class="" id="info" style="color:darkred;display: block;">未上传</label>
        <div class="result_wrap">
            <p style="font-size:14px;">各统计内容的个数如下:</p>
            <table class="table1" width="350" border="1">
                <thead>
                <tr>
                    <th>统计项</th>
                    <th>个数</th>
                </tr>
                </thead>
                <tbody>
                <tr id="ENGLISH_WORD">
                    <td>英文字母</td>
                    <td></td>
                </tr>
                <tr id="NUMBER">
                    <td>数字</td>
                    <td></td>
                </tr>
                <tr id="CHINESE_WORD">
                    <td>中文字母</td>
                    <td></td>
                </tr>
                <tr id="PUNCTUTION">
                    <td>中英文标点符号</td>
                    <td></td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="result_wrap">
            <p style="font-size:14px;">文字中出现频率最高的三个字是:</p>
            <table id="table2" class="table2" width="350" border="1">
                <thead>
                <tr>
                    <th>名称</th>
                    <th>个数</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <script type="text/javascript">
        var tableChange = {
            classifyTable:function(classify){
                for(var index in classify){
                    var tr = document.getElementById(index);
                    tr.getElementsByTagName("td")[1].innerHTML = classify[index];
                }
            },
            sortedTable:function(rateSort){
                var table = document.getElementById("table2");
                var trs =  table.querySelectorAll("tbody tr");
                var count  = 0;
                for(var index in rateSort){
                    console.log(rateSort[index]);
                    for(var key in rateSort[index]){
                        trs[count].getElementsByTagName("td")[0].innerHTML = key;
                        trs[count].getElementsByTagName("td")[1].innerHTML = rateSort[index][key];
                    }
                    count++;
                }
            }
        }
    </script>
    <script type="text/javascript">
        var ajax = {
            _validate:function(ajaxData){
                if(typeof ajaxData == 'undefined'
                        || typeof  ajaxData['url'] == 'undefined'
                        || typeof  ajaxData['datas'] == 'undefined'
                        || typeof ajaxData['type'] == 'undefined')
                    return false;
                ajaxData['ajaxSuccess']
                        =typeof  ajaxData['ajaxSuccess'] == 'undefined'?
                        function(){}:ajaxData['ajaxSuccess'];
                ajaxData['ajaxError']
                        =typeof  ajaxData['ajaxError'] == 'undefined'?
                        function(){}:ajaxData['ajaxError'];
                return true;
            },
            ajaxSend:function (ajaxData) {
                if(!ajax._validate(ajaxData)){
                    throw Error("参数错误");
                }
                if(window.XMLHttpRequest){
                    AjaxObj =  new XMLHttpRequest();
                }else{
                    AjaxObj = new ActiveXObject("XMLHTTP");
                }
                AjaxObj.open(ajaxData['type'],ajaxData['url']+"?new="+new Date().getTime(),true);
                if(typeof  ajaxData['contentType'] != 'undefined') {
                    AjaxObj.setRequestHeader("Content-type", ajaxData['contentType']);
                }
                AjaxObj.send(ajaxData['datas']);
                AjaxObj.onreadystatechange = function  () {
                    if(AjaxObj.readyState == 4){
                        if(AjaxObj.status == 200){
                            console.log('ok');
                            ajaxData['ajaxSuccess'](AjaxObj.responseText);
                        }else{
                            ajaxData['ajaxError'](AjaxObj.readyState,AjaxObj.status,AjaxObj.responseText);
                        }
                    }else{
                        ajaxData['ajaxError'](AjaxObj.readyState);
                    }
                }
            }
        }
    </script>
    <script type="text/javascript">
        var submitBtn = document.getElementsByName("text_btn")[0];
        function showResult(result){
            if(typeof result['error'] != 'undefined'){
                alert(result['error'])
            }else {
                tableChange.classifyTable(result['classify']);
                tableChange.sortedTable(result['rateSort']);
            }
        }
        submitBtn.onclick = function(eve){
            eve.preventDefault();
            var file = document.getElementsByName("up_file")[0].files[0];
            console.log(file);
            var formData = new FormData();
            formData.append("up_file",file);
            console.log(formData)
            ajax.ajaxSend(({
                url:'/filecount',
                datas:formData,
                type:'post',
                ajaxSuccess:function(result){
                    var json = JSON.parse(result);
                    showResult(json);
                }
            }))
        }
        var countBtn = document.getElementById("count_btn");
        countBtn.onclick = function(eve){
            eve.preventDefault();
            var datas ={stream:document.getElementById("text_content").value};
            ajax.ajaxSend(({
                url:'/textcount',
                contentType:'application/json;charset=UTF-8',
                datas:JSON.stringify(datas),
                type:'post',
                ajaxSuccess:function(result){
                    var json = JSON.parse(result);
                    showResult(json);
                }
            }))
        }
        var clearBtn = document.getElementById("clear_btn");
        clearBtn.onclick = function (eve) {
            eve.preventDefault();
            document.getElementById("text_content").value = "";
        }
    </script>
</div>
</body>
</html>
