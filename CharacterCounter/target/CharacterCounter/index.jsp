<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src = "/js/jquery-1.6.4.js"></script>

<html>
<body>
<h2>请选择一段文字</h2><br/>
<div style="margin-bottom: 10px" >
    <input id = "bt1" type="radio" name="model" value="file" checked="checked" onchange="bt1()" />文件上传&nbsp;&nbsp;
    <input id = "bt2" type="radio" name="model" value="text" onchange="bt2()" />文本输入
</div>
<div id="div2" style="display:none;height:100px;">
    <div style="float:left;">
        <textarea id="text" placeholder="请在此输入文本内容" style= "resize:none;width:500px;height:100px;" ></textarea>
    </div>
    <div style="float:left; padding-left:20px;">
        <input type=button id="textCount" style="background:#2fcaff;color:white;width:70px;height:30px;" value="统计" onclick="textCount()"/>  <br/><br/>
        <input type=button id="deleteText" style="background:#ffa031;color:white;width:70px;height:30px;" value="清空内容" onclick="delText()"/>
    </div>
</div>
<div id="div1" style="display:block;">
    <input type="button" value="上传文件" onclick="openBrowse()" style="background:#51b1ff;color: white" />
    <input type="text" size="20" name="upfile" id="upfile" readonly="true" style="border: none"/>
    <input type="file" id="path" style="display:none" onchange="a(this)" />
    <input type="button" value="统计" style="background:#51b1ff;color: white" onclick="fileCount()"/>
    <span>（只支持文本文件）</span>
    <img src="/images/upload.gif" style="display:none" id="imgWait" />
</div>
    <br/>
        <div style="margin-bottom: 10px" >各统计内容的个数如下:
        </div>
    <div>
    <table border="1" cellpadding="0" cellspacing="0" bordercolor="#000000" style="table-layout:fixed;border-collapse:collapse;width:300px;height:150px;text-align:center" >
        <tr>
            <th width="150px" height="30px">统计项</th>
            <th width="150px">个数</th>
        </tr>
        <tr>
            <td>英文字母</td>
            <td id="enWord"></td>
        </tr>
        <tr>
            <td>数字</td>
            <td id="digit"></td>
        </tr>
        <tr>
            <td>中文汉字</td>
            <td id="cnWord"></td>
        </tr>
        <tr>
            <td>中英文标点符号</td>
            <td id="punctuation"></td>
        </tr>
    </table>
    </div>
    <div style="margin-bottom: 10px;margin-top: 10px;" >文字中出现频率最高的三个字是:</div>
    <div>
    <table border="1" cellpadding="0" cellspacing="0" bordercolor="#000000" style="table-layout:fixed;border-collapse:collapse;width:300px;height:120px;text-align:center" >
        <tr>
            <th width="150px"  height="30px">名称</th>
            <th width="150px">个数</th>
        </tr>
        <tr>
            <td id="word1"></td>
            <td id="time1"></td>
        </tr>
        <tr>
            <td id="word2"></td>
            <td id="time2"></td>
        </tr>
        <tr>
            <td id="word3"></td>
            <td id="time3"></td>
        </tr>
    </table>
    </div>
</body>
<script>
    function bt1(){
        document.getElementById('div1').style.display='block';
        document.getElementById('div2').style.display='none';
    }
    function bt2(){
        document.getElementById('div1').style.display='none';//<span style="color: rgb(51, 51, 51); font-family: 'Microsoft YaHei', arial, 宋体, sans-serif, tahoma; font-size: 14px; line-height: 24px;">display = "none"//隐藏div</span>
        document.getElementById('div2').style.display='block';//<span style="color: rgb(51, 51, 51); font-family: 'Microsoft YaHei', arial, 宋体, sans-serif, tahoma; font-size: 14px; line-height: 24px;">//display = "block"//显示div</span>
    }
    function a(obj){
        var filePath =obj.value.split("\\");
        var length = filePath.length;
        document.getElementById("upfile").value=filePath[length-1];
    }
    function openBrowse(){
        var ie=navigator.appName=="Microsoft Internet Explorer" ? true : false;
        if(ie){
            document.getElementById("path").click();
        }else{
            var a=document.createEvent("MouseEvents");//FF的处理
            a.initEvent("click", true, true);
            document.getElementById("path").dispatchEvent(a);
        }
    }
    function textCount(){
        var text = document.getElementById("text").value;
        var url = "/aaa.do";
        if(text==null || text==""){
            alert("请输入内容")
        }else{
            $.ajax({
                "url":url,
                "data":{"text":text},
                "type":"POST",
                success:function(data){
                    document.getElementById("enWord").innerHTML=data.enWord;
                    document.getElementById("cnWord").innerHTML=data.cnWord;
                    document.getElementById("digit").innerHTML=data.digit;
                    document.getElementById("punctuation").innerHTML=data.punctuation;

                    document.getElementById("word1").innerHTML=data.word1;
                    document.getElementById("word2").innerHTML=data.word2;
                    document.getElementById("word3").innerHTML=data.word3;

                    document.getElementById("time1").innerHTML=data.time1;
                    document.getElementById("time2").innerHTML=data.time2;
                    document.getElementById("time3").innerHTML=data.time3;

                },
                error: function () {
                    alert("失败！");
                },
                "dataType":"json"
            });
        }
    }
    function delText(){
        document.getElementById("text").innerHTML="";
        document.getElementById("enWord").innerHTML="";
        document.getElementById("cnWord").innerHTML="";
        document.getElementById("digit").innerHTML="";
        document.getElementById("punctuation").innerHTML="";

        document.getElementById("word1").innerHTML="";
        document.getElementById("word2").innerHTML="";
        document.getElementById("word3").innerHTML="";

        document.getElementById("time1").innerHTML="";
        document.getElementById("time2").innerHTML="";
        document.getElementById("time3").innerHTML="";
    }
    function fileCount(){
        var myfile = document.getElementById("path").files[0];
        if( myfile==null){
            alert("请选择文件");
        }else if(myfile.size>104857600){
            alert("文件不得大于100MB");
        }else{
            $("#imgWait").show();
            var formData = new FormData();
            formData.append("myfile",myfile);
                $.ajax({
                    url: "bbb.do",
                    type: "POST",
                    data: formData,
                    /**
                     *必须false才会自动加上正确的Content-Type
                     */
                    contentType: false,
                    /**
                     * 必须false才会避开jQuery对 formdata 的默认处理
                     * XMLHttpRequest会对 formdata 进行正确的处理
                     */
                    processData: false,
                    success: function (data) {
                        if (data.status == "true") {
                            $("#imgWait").hide();
                            alert("上传成功！");
                            document.getElementById("enWord").innerHTML=data.enWord;
                            document.getElementById("cnWord").innerHTML=data.cnWord;
                            document.getElementById("digit").innerHTML=data.digit;
                            document.getElementById("punctuation").innerHTML=data.punctuation;

                            document.getElementById("word1").innerHTML=data.word1;
                            document.getElementById("word2").innerHTML=data.word2;
                            document.getElementById("word3").innerHTML=data.word3;

                            document.getElementById("time1").innerHTML=data.time1;
                            document.getElementById("time2").innerHTML=data.time2;
                            document.getElementById("time3").innerHTML=data.time3;
                        }else{
                            $("#imgWait").hide();
                            alert(data.status);
                        }

                    },
                    error: function () {
                        alert("上传失败！");
                        $("#imgWait").hide();
                    }
                });
            }
    }
</script>
</html>
