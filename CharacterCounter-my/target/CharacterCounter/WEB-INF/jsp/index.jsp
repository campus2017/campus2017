<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>去哪儿网作业</title>
<link rel="stylesheet" href="/css/base.css">
<link rel="stylesheet" href="/css/index.css">
<link rel="shortcut icon" href="/images/logo1.png">
<script type="text/javascript" src="/js/jquery-2.0.3.min.js"></script>
</head>
<body>
	<div class=bg>
		<div class=content>
			<h1>请选择一段文字</h1>
			<div>
				<p>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input id="file" type="radio"
						checked="checked" name="content" value="file" />文件上传
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
						id="text" type="radio" name="content" value="text" />文本输入
				</p>
				<div id="wbsr" class="clearfix wbsr">
					<div  class="fl">
						<textarea id="area" rows="" cols=""></textarea>
					</div>
					<div class="fr qk">
						<div>
							<input id="btn_qk_tj" class="btn_qk_tj" type="button" value="统计" />
						</div>
						<div>
							<input id="btn_qk" class="btn_qk" type="button" value="清空内容" />
						</div>
					</div>
				</div>
				<div id="sc" class="clearfix sc">
					<form id="uploadForm" action="upload.action" method="post" enctype="multipart/form-data">
					<input name="file" id="btn_sc" class="btn_sc" type="file" value="上传文件" /> 
					</form>
					<input id="btn_tj" class="btn_tj" type="button" value="统计" />
					
				</div>
			</div>
			<h2>各统计内容的个数如下：</h2>
			<table>
				<tr class="first">
					<td>统计项</td>
					<td>个数</td>
				</tr>
				<tr>
					<td>英文字母</td>
					<td id="english"></td>
				</tr>
				<tr>
					<td>数字</td>
					<td id="number"></td>
				</tr>
				<tr>
					<td>中文汉字</td>
					<td id="chinese"></td>
				</tr>
				<tr>
					<td>中英文标点符号</td>
					<td id="char"></td>
				</tr>
			</table>
			<h2>文字中出现频率最高的三个字是：</h2>
			<table>
				<tr class="first">
					<td>名称</td>
					<td>个数</td>
				</tr>
				<tr>
					<td id="top1"></td>
					<td id="value1"></td>
				</tr>
				<tr>
					<td id="top2"></td>
					<td id="value2"></td>
				</tr>
				<tr>
					<td id="top3"></td>
					<td id="value3"></td>
				</tr>
			</table>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function () {
	$("#text").change(function(){
		if($("#text").prop("checked")){
			$("#sc").css("display","none");
			$("#wbsr").css("display","block");
		}
	});
	
	$("#file").change(function(){
		if($("#file").prop("checked")){
			$("#wbsr").css("display","none");
			$("#sc").css("display","block");
		}
	});
	
	
	
	$("#btn_qk").click(function(){
		$("textarea").val("");
	});
	
	$("#btn_tj").click(function(){
		var formData = new FormData($( "#uploadForm" )[0]);  
	     $.ajax({  
	          url: 'http://localhost:8080/work/upload.action' ,  
	          type: 'POST',  
	          data: formData,  
	          async: false,  
	          cache: false,  
	          contentType: false,  
	          processData: false, 
	          success: function (data) {  
	        	  $("#english").html(data.englishCount );
					$("#chinese").html(data.chineseCount);
					$("#number").html(data.numberCount);
					$("#char").html(data.charCount);
					$("#top1").html(data.top3[0]);
					$("#top2").html(data.top3[1]);
					$("#top3").html(data.top3[2]);
					$("#value1").html(data.value3[0]);
					$("#value2").html(data.value3[1]);
					$("#value3").html(data.value3[2]);   
	          }  
	})
	})
	
	
	$("#btn_qk_tj").click(function(){
		$.ajax({url : "http://localhost:8080/work/tongji.action",
				type : "POST",
				data :{content:$("textarea").val()
					},
				success : function(data){
				    $("#english").html(data.englishCount );
					$("#chinese").html(data.chineseCount);
					$("#number").html(data.numberCount);
					$("#char").html(data.charCount);
					$("#top1").html(data.top3[0]);
					$("#top2").html(data.top3[1]);
					$("#top3").html(data.top3[2]);
					$("#value1").html(data.value3[0]);
					$("#value2").html(data.value3[1]);
					$("#value3").html(data.value3[2]); 
				}
		})
	})
})
</script>
</html>