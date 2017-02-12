<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=gb2312" pageEncoding="utf-8"%>
<html>
<head>
<title>character counter Form</title>

<script type="text/javascript" >
function displayupload(){
document.getElementById("p1").style.display="block";
document.getElementById("p2").style.display="none";
}
function displayinput(){ 
document.getElementById("p1").style.display="none";  
document.getElementById("p2").style.display="block";
}
function checkradio()
{
      var x=document.getElementsByName("ways");
      if (x[1].checked)
      {
         document.getElementById("p1").style.display="none";  
         document.getElementById("p2").style.display="block";
      }
      else
      {
         document.getElementById("p1").style.display="block";
         document.getElementById("p2").style.display="none";
      }
}
function ResetText(){
      document.getElementById("textarea").value =" ";
      }
</script>
<style type="text/css">@import url(css/main.css);</style>
</head>

<body onload="checkradio()">

<div id="global">
    <fieldset>
        <legend>请选择一段文字</legend>
            <p>
              <input type="radio" value="0" name="ways" checked="checked" id="radio1" onclick="displayupload()"/>文件上传
              <input type="radio" value="1" name="ways" id="radio2" onclick="displayinput()"/>文本输入
            </p>
            <form:form commandName="characterown" action="/character_upload_counter" method="post" enctype="multipart/form-data">
            <div id="p1" style="display:block" align="left">
                                            上传文件: 
                <input type="file" id="description" 
                    name="file[0]"/>
                <input type="submit" value="统计">
            </div>
            </form:form>
            <form:form commandName="characterown" action="/character_input_counter" method="post">
            <div id="p2" style="display:none">
            <table width="200" border="0" >
                <tr>
                <td >
                <form:textarea id="textarea" path="contents" style="background-color:#eeeeee;height:50px;width:200px;vertical-align:top;"/>
                </td>
                <td>
                <input id="count" type="submit" value="统计" style="background-color:#06F; width:100px; vertical-align:top;" name="1111"><br>
                <input id="empty" type="button" value="清除内容" style="background-color:#FFD700;width:100px;vertical-align:top;" onclick="ResetText()"><br>
                </td>
               </tr>
            </table>
            </div>
            </form:form>
<p align="left">
各统计内容的个数如下：
</p>
<table width="221" border="1">
  <tr>
    <th width="99">统计项</th>
    <th width="85">个数</th>
</tr>
<tr>
    <td>英文字母</td>
    <c:choose>
       <c:when test="${characterown.flag}">
          <td>${characterown.english_num}</td>
       </c:when>
       <c:when test="${!characterown.flag}">
          <td>&nbsp;</td>
       </c:when>
    </c:choose>
</tr>
<tr>
    <td>数字</td>
    <c:choose>
       <c:when test="${characterown.flag}">
          <td>${characterown.num}</td>
       </c:when>
       <c:when test="${!characterown.flag}">
          <td>&nbsp;</td>
       </c:when>
    </c:choose>
</tr>
<tr>
    <td>中文汉字</td>
    <c:choose>
       <c:when test="${characterown.flag}">
          <td>${characterown.chinese_num}</td>
       </c:when>
       <c:when test="${!characterown.flag}">
          <td>&nbsp;</td>
       </c:when>
    </c:choose>
</tr>
<tr>
    <td>中英文标点符号</td>
    <c:choose>
       <c:when test="${characterown.flag}">
          <td>${characterown.sysmbol_punc}</td>
       </c:when>
       <c:when test="${!characterown.flag}">
          <td>&nbsp;</td>
       </c:when>
    </c:choose>
</tr>
</table>
<p align="left">
文字中出现频率最高的三个字：</p>
<table width="221" border="1">
<tr>
    <th>名称</th>
    <th>个数</th>
</tr>
<c:choose>
<c:when test="${characterown.flag}">
<c:forEach items="${characterown.map}" var="ch">
    <tr>
        <td>${ch.key}</td>
        <td>${ch.value}</td>
    </tr>
</c:forEach>
</c:when>
<c:when test="${!characterown.flag}">
<tr>
<td>&nbsp;</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>&nbsp;</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>&nbsp;</td>
<td>&nbsp;</td>
</tr>
</c:when>
</c:choose>
</table>
</fieldset>
</div>
</body>
</html>
