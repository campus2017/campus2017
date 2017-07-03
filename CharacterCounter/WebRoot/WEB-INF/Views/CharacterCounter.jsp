<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>统计</title>
<script type="text/javascript">
function gd() {
var value = document.getElementById("rdo1").checked;
if (value) {
text1.style.visibility='hidden';
upload.style.visibility='visible';
qingkong.style.visibility='hidden';
} else {
text1.style.visibility='visible';
upload.style.visibility='hidden';
qingkong.style.visibility='visible';
}
}
</script>
</head>

<body>
<br>请选择一段文字<br/>
<br/>
<input id="rdo1" name="rdo1" type="radio" value="1" checked="checked" onclick="gd()"/>文件上传 
<input id="rdo1" name="rdo1" type="radio" value="0" onclick="gd()"/>文本输入
<br/>
<form action="/CharacterCounter/CharacterCounter1" method="post" enctype="multipart/form-data">
<br/>
<input id="upload" type="file" name="upload" value="选择文件" style="visibility:visible;background-color:#00BBFF">
<textarea id="text1" name="text1" cols="20" rows="3" style="visibility:hidden"></textarea>
<input type="submit" value="统计" style="background-color:#00BBFF">
<input id="qingkong" type="reset" value="清空内容" style="visibility:hidden;background-color:#FFAA33">
</form>
    <br/>
<br>各统计内容的个数如下：<br/>
<br/>
	<table width="300" height="20" border="1">
       <tr>
           <td width="300" align="center">统计项</td>
           <td width="300" align="center">个数</td>
       </tr>
       <tr>
           <td align="center">英文字母</td>
           <td align="center">${english}</td>
       </tr>
       <tr>
           <td align="center">数字</td>
           <td align="center">${number}</td>
       </tr>
       <tr>
           <td align="center">中文汉字</td>
           <td align="center">${chinese}</td>
       </tr>
       <tr>
           <td align="center">中英文标点符号</td>
           <td align="center">${others}</td>
       </tr>
   </table>
<br>文字中出现频率最高的三个字是：<br/>
<br/>
   <table border="1" width="300" height="20">
       <tr>
           <td width="300" height="20" align="center">名称</td>
           <td width="300" height="20" align="center">个数</td>
       </tr>
       <tr>
           <td width="300" height="20" align="center">${chinese1}</td>
           <td width="300" height="20" align="center">${chinese1Number}</td>
       </tr>
       <tr>
           <td width="300" height="20" align="center">${chinese2}</td>
           <td width="300" height="20" align="center">${chinese2Number}</td>
       </tr>
       <tr>
           <td width="300" height="20" align="center">${chinese3}</td>
           <td width="300" height="20" align="center">${chinese3Number}</td>
       </tr>
   </table>
</body>

</html>

 