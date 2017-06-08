<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	
    <title>CharacterCounter</title>
	
	<link rel="stylesheet" type="text/css" href="./resource/styles.css">
	
	<script>
		window.onload = inputTextHidden;
		
		function clearText()
		{
			document.getElementById("text").value="";
		}
		
		function inputTextHidden() {
			document.getElementById("inputText").style.display="none";
		}
		function inputTextVisible() {
			document.getElementById("inputText").style.display="inline";
		}
		function uploadFileHidden() {
			document.getElementById("uploadFile").style.display="none";
		}
		function uploadFileVisible() {
			document.getElementById("uploadFile").style.display="inline";
		}
	</script>
  </head>  
  
  <body>
  	<div align="center" >
		<div align="left" class="main">
			<p class="head1">请选择一段文字<br/>
			<div class="top">
				<form>
					<input type="radio" name="typeSelect" value="uploadFile" onclick="inputTextHidden(),uploadFileVisible()" checked>文件上传
					<input type="radio" name="typeSelect" value="inputText" onclick="inputTextVisible(),uploadFileHidden()">文本输入
				</form> 
								
				<form id= "inputText" action="submitInputText.do" method="post" >
					<textarea class="textInput" name="text" rows="10" cols="30">请在此输入文本内容</textarea>
					<input class="submitButton" type="submit" name="statistics" value="统计">
					<input class="clearButton" type="button" name="clear" value="清空内容" onclick="clearText()">
				</form>		
				
				<br>
			 
				<form id="uploadFile" action="fileUpload.do" method="post"  enctype="multipart/form-data">
					<input class="fileInput" type="file" name="uploadFile" value="上传文件">
					<input class="submitButton" type="submit" value="统计"/>
				</form>
			</div>	
			<br>
			
			<p class="head2">各统计内容的个数如下：<br>
			<%
				String num0 = request.getParameter("num0");
				String num1 = request.getParameter("num1");
				String num2 = request.getParameter("num2");
				String num3 = request.getParameter("num3");
			%>
			<div class="buttom">
				<table>
				  <tr>
				    <th>统计项</th>
				    <th>个数</th>
				  </tr>
				  <tr>
				  	<td>英文字母</td>
				  	<td><c:out value="${num0}" default=""></c:out></td>
				  </tr>
				  <tr>
				  	<td>数字</td>
				  	<td><c:out value="${num1}" default=""></c:out></td>
				  </tr>
				  <tr>
				  	<td>中文汉字</td>
				  	<td><c:out value="${num2}" default=""></c:out></td>
				  </tr>
				  <tr>
				  	<td>中英文标点符号</td>
				  	<td><c:out value="${num3}" default=""></c:out></td>
				  </tr>
				</table>
				<br><br>
				
				文字中汉字出现频率最高的三个字是:<br><br>
				<table>
				  <tr>
				    <th>名称</th>
				    <th>个数</th>
				  </tr>				  
				  <c:forEach items="${maxResult}" var="res">
				  	<tr>
				  		<td>
				  			<c:out value="${res.key}" default=""></c:out>
				  		</td>
				  		<td>
				  			<c:out value="${res.value}" default=""></c:out>
				  		</td>
				  	</tr>
				  </c:forEach>
				</table>
			</div>
		</div>
	</div>
  </body>
</html>
