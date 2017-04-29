<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>CharacterCounter</title>
    <link rel='stylesheet' href='./include/bootstrap-3.3.5/css/bootstrap.min.css'>
</head>
<body>
<div class="container" style="width: 80%">
    <form>
        <input type="radio" name="radio" id="radio0" value="0" runat="server" checked />文件上传
        <input type="radio" name="radio" id="radio1" value="1" runat="server" />文本输入
    </form>
    <br/>
    <br/>
<form >
    <input id="myupload" type="file" name="myfile" class="input-large">
    <br />
    <input type="button" id="submit" class="btn btn-primary" value="文件上传">
    <textarea id="mytext"> </textarea>
    <input type="button" class="btn-warning" id="clear" value="清空内容">
</form>
    <br />
    <br />

    <button class="btn-success" id="counter">统计</button>


    <h4>各统计内容的个数如下：</h4>
    <table border="1">
        <tr>
            <td>统计项</td>
            <td>个数</td>
        </tr>
        <tr>
            <td>英文字母</td>
            <td id="alp">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        </tr>
        <tr>
            <td>数字</td>
            <td id="num">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        </tr>
        <tr>
            <td>中文汉字</td>
            <td id="chi">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        </tr>
        <tr>
            <td>中英文标点符号</td>
            <td id="other">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        </tr>
    </table>

    <h4>文字中出现频率最高的三个字是：</h4>
    <table border="1">
        <tr>
            <td id="top1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td id="val1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        </tr>
        <tr>
            <td id="top2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td id="val2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        </tr>
        <tr>
            <td id="top3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td id="val3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        </tr>
    </table>

</div>
</body>
<script type="text/javascript" src="./include/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
    $(function(){
        $('#mytext').hide();
        $('#clear').hide();
    });

    $('#submit').on('click', function() {
        var fd = new FormData();
        fd.append("upload", 1);
        fd.append("upfile",$("#myupload").get(0).files[0]);
        $.ajax({
            url: "./upload",
            type: "POST",
            processData: false,
            contentType: false,
            data: fd,
            success: function(d) {
                console.log(d);
            }
        });
    });

    $('#counter').on('click',function(){
        var type = $("input[type='radio']:checked").val();
        if(type == '1'){
            var filename = ".";
        }else{
            var filename = $("#myupload").get(0).files[0].name;
        }
        $.ajax({
            url:"./counter",
            type:"POST",
            data:"type="+type+"&text="+$('#mytext').val()+"&filename="+filename,
            success: function(d) {
                console.log(d);
                //清空top先
                $('#top1').html("&nbsp;&nbsp;&nbsp;");
                $('#val1').html("&nbsp;&nbsp;&nbsp;");
                $('#top2').html("&nbsp;&nbsp;&nbsp;");
                $('#val2').html("&nbsp;&nbsp;&nbsp;");
                $('#top3').html("&nbsp;&nbsp;&nbsp;");
                $('#val3').html("&nbsp;&nbsp;&nbsp;");

                $('#alp').html("&nbsp;&nbsp;&nbsp;"+d.type.alp);
                $('#num').html("&nbsp;&nbsp;&nbsp;"+d.type.num);
                $('#chi').html("&nbsp;&nbsp;&nbsp;"+d.type.chi);
                $('#other').html("&nbsp;&nbsp;&nbsp;"+d.type.other);
                $('#top1').html("&nbsp;&nbsp;&nbsp;"+d.top.first.key);
                $('#val1').html("&nbsp;&nbsp;&nbsp;"+d.top.first.val);
                $('#top2').html("&nbsp;&nbsp;&nbsp;"+d.top.second.key);
                $('#val2').html("&nbsp;&nbsp;&nbsp;"+d.top.second.val);
                $('#top3').html("&nbsp;&nbsp;&nbsp;"+d.top.third.key);
                $('#val3').html("&nbsp;&nbsp;&nbsp;"+d.top.third.val);
            }
        }) ;
    });

    $('#radio0').on('click',function(){
        $('#mytext').hide();
        $('#clear').hide();
        $('#myupload').show();
        $('#submit').show();
    })

    $('#radio1').on('click',function(){
        $('#myupload').hide();
        $('#submit').hide();
        $('#mytext').show();
        $('#clear').show();
    })

    $('#clear').on('click',function(){
        $('#mytext').val("");
    })

</script>
</html>

