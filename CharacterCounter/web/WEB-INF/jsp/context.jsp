<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: steamedfish
  Date: 17-6-27
  Time: 下午8:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<html>
<head>
    <title>Character Counter</title>
</head>
<body>
<h2>请选择一段文字</h2>
<form method="POST" id="form1" >
    <table>
        <tr>
            <td rowspan="2"><textarea name="text" id="t1" rows="10" cols="50"></textarea></td>

                <td><input type="button" value="统计" style="width: 100px;height: 60px;" id="count_btn"/></td>
        </tr>
        <tr>
            <td><input type="button" value="清除内容" style="width: 100px;height: 60px;" id="clear_btn" onclick="dosomething()"/></td>
        </tr>
        <tr>
            <td>
            </td>
        </tr>

    </table>
    <br>
    <div>各统计内容的个数如下：</div>
    <br>
    <table border="1">
        <tr>
            <td><div style="width: 200px;word-wrap: break-word">统计项</div></td>
            <td><div style="width: 200px;word-wrap: break-word">个数</div></td>
        </tr>
        <tr>
            <td><div style="width: 200px;word-wrap: break-word">英文字母</div></td>
            <td><p id="letter_p"></p></td>
        </tr>
        <tr>
            <td><div style="width: 200px;word-wrap: break-word">数字</div></td>
            <td><p id="number_p"></p></td>
        </tr>
        <tr>
            <td><div style="width: 200px;word-wrap: break-word">中文汉字</div></td>
            <td><p id="chinese_p"></p></td>

        </tr>
        <tr>
            <td><div style="width: 200px;word-wrap: break-word">中英文标点符号</div></td>
            <td><p id="symbol_p"></p></td>

        </tr>
    </table>
    <br>
    <div>文字中出现频率最高的三个字是：</div>
    <br>
    <table border="1">
        <tr>
            <td><div style="width: 200px;word-wrap: break-word">名称</div></td>
            <td><div style="width: 200px;word-wrap: break-word">个数</div></td>
        </tr>
        <tr>
            <td><p id="letter1"></p></td>
            <td><p id="l1_num"></p></td>

        </tr>
        <tr>
            <td><p id="letter2"></p></td>
            <td><p id="l2_num"></p></td>

        </tr>
        <tr>
            <td><p id="letter3"></p></td>
            <td><p id="l3_num"></p></td>

        </tr>
    </table>

</form>
</body>
<script type="text/javascript">
    $("#clear_btn").click(function (event) {
        $("#t1").val("");
    })

    $("#count_btn").click(function (event) {
        //$("#form1").submit();
        $.ajax({
            url:'<%=request.getContextPath()%>/count',
            type:'post',
            data:$("#form1").serialize(),
            dataType:"json",
            success:function(data) {
               // alert("succeed");
                $("#letter_p").text(data['1']);
                $("#number_p").text(data['2']);
                $("#chinese_p").text(data['3']);
                $("#symbol_p").text(data['4']);
                if(data['5'].charAt(1)==' '){
                    $("#letter1").text(data['5'].charAt(0));
                    $("#l1_num").text(data['5'].substring(2,data['5'].length));
                }

                if(data['6'].charAt(1)==' ') {

                    $("#letter2").text(data['6'].charAt(0));
                    $("#l2_num").text(data['6'].substr(2));
                }

                if(data['7'].charAt(1)==' ') {

                    $("#letter3").text(data['7'].charAt(0));
                    $("#l3_num").text(data['7'].substr(2));
                }

               // alert(data['1']);
                //$("#t1").val(data['1']);
                //alert(data['1']);
                //var js=$.parseJSON(data);

                //


            },
            error:function(){
                alert("error!");
            }
        });

    })


</script>
</html>
