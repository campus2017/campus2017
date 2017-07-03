<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>cesji</title>
    <script language="javascript">
        //文本框和文件上传的选择
        function hidemenu() {
            document.all["menu1"].style.display = "none";
            document.all["menu2"].style.display = "";
            document.FrontPage_Form1.a.checked = false;
            document.FrontPage_Form1.b.checked = true;
        }
        function showmenu() {
            document.all["menu1"].style.display = "";
            document.all["menu2"].style.display = "none";
            document.FrontPage_Form1.b.checked = false;
            document.FrontPage_Form1.a.checked = true;
        }
        function show1(a) {
            if (a != "0") {
                hidemenu();
            }
            else {
                showmenu();
            }
        }
        //文本框统计和清空
        function cl() {
            document.getElementById('txt').value = "";
        }
        //局部刷新
        /* $.ajax({
         type: "post",
         dataType:'json', //接受数据格式
         cache:false,
         data:"strIds="+strIds,
         url: "${ctx}/feedbackonline/updateMessageStateUnread.action",
         //    beforeSend: function(XMLHttpRequest){
         },
         success: function(data){
         var str=data.str;//接收返回的数据
         for(var p in str){ //遍历接受的数组对象
         var x="#r"+str[p];//获取要改变的记录的图标id
         $(x).attr("src","${ctx}/images/04.png");
         //把对应的id值的图标src属性值变成相应图标的路径
         }
         }
         error: function() {
         //请求出错处理
         alert("Error!");
         }
         });*/
    </script>

</head>
<body>
<form action="sample8.asp" method="post" name="FrontPage_Form1" onsubmit="return FrontPage_Form1_Validator(this)">
    <input type="radio" value="0" name="a" onclick="show1(document.FrontPage_Form1.a.value);">按钮一
    <input type="radio" value="1" name="b" onclick="show1(document.FrontPage_Form1.b.value);">按钮二
    <div id="menu1">
        <form method="post" id="form1" action="?action=add">
            <input type="hidden" name="div1" value="div1">
            <input type="file" value="" name="文件上传">
            <input type="submit" value="统计">
        </form>
    </div>
    <div id="menu2" style="display: none">
        <form method="post" id="form2" action="?action=add">
            <textarea id="text_area"></textarea>
            <input type="hidden" name="div2" value="div2">
            <input type="submit" name="submit" id="submit" value="统计"/>
            <input type="reset" name="reset" id="reset" value="清空" onclick="cl()"/>
        </form>
    </div>
</form>

</body>
</html>