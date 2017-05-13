<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>CharacterCounter</title>
    <style type="text/css">
    	body{
    		height: 750px;
    		width: 400px;
    		border:solid #000 1px;
    		margin: 0 auto;
    		margin-top: 20px;
    		padding-left:80px;
    	}
    	.title{
    		margin-top:10px;
    		margin-left:-30px;
    		font-weight:bold;
    	}
    	table{
    		border-collapse:collapse;
    		border:none;
    	}
    	.table tr{
    		border:solid #000 1px;
    	}
    	.table th,td{
    		border:solid #000 1px;
    		height:40px;
    		width:150px;
    	}
		.txtArea{
			width:300px;
			resize:none;
			overflow-y:scroll;
			overflow-x:hidden;
		}
		.statistics{
			background-color: #00CCFF;
			color: #FFFFFF;
			width:80px; ;
			height: 36px;
		}
    </style>
    <script type="text/javascript" src="js/jquery-1.8.0.js"></script>
    <script type="text/javascript">
		$(function(){
			
			//页面加载控制第一个radio被选中，第一个div显示，第二个隐藏
			$("#radio").attr("checked",true);
			$("#div1").show();
			$("#div2").hide();
			
			//文件上传方式中点击按钮触发事件
	   		$("#submit").click(function(){
				var file = $("#myFile").val();
				if(file == null || file == ""){
					alert("文件不能为空");
					return false;
				}else{
					var formData = new FormData();
					formData.append("file", document.getElementById("myFile").files[0]);   
					$.ajax({
	                    url: "${basePath}counter/counterByTypeOfFileUpload.action",
	                    type: "POST",
	                    data: formData,
	                    contentType: false,
	                    processData: false,
	                    success: function (data) {
	                        if(data != null){
			   					$("#character").html(data.character);
			   					$("#digital").html(data.digital);
			   					$("#chinese").html(data.chinese);
			   					$("#punctuation").html(data.punctuation);
			   					var html = "<tr><th>名称</th><th>个数</th></tr>";
			   					$("#tr1").remove();
			   					$("#tr2").remove();
			   					$("#tr3").remove();
			   					$("#tr4").remove();
								for(var i=0;i<data.topklist.length;i++){
									html += "<tr><td class='content' align='center'>"+data.topklist[i].data +"</td><td class='content' align='center'>"+ data.topklist[i].count +"</td></tr>";
								}
								if(data.topklist.length<3)
								{
									for(var i=0;i<3-data.topklist.length;i++){
										html += "<tr><td  class='content' >"+"</td><td  class='content' >"+"</td></tr>";
									}
								}
								$("#tableFrequencyId").html(html);
								$("#myFile").val("");
	   						}
	                    }
                	});
				}
	   		});

	   		//文本输入方式点击按钮触发事件
	   		$("#counter").click(function(){
	   			var txtContent = $("#txtArea").val();
	   			$.post("${basePath}counter/counterByTypeOfTxtInput.action",{txtContent:txtContent},function(data,status){
	   				if(data != null){
	   					$("#character").html(data.character);
	   					$("#digital").html(data.digital);
	   					$("#chinese").html(data.chinese);
	   					$("#punctuation").html(data.punctuation);
						var html = "<tr><th>名称</th><th>个数</th></tr>";
						$("#tr1").remove();
						$("#tr2").remove();
						$("#tr3").remove();
						$("#tr4").remove();
						for(var i=0;i<data.topklist.length;i++){
							html += "<tr><td  class='content' align='center'>"+data.topklist[i].data +"</td><td  class='content' align='center'>"+ data.topklist[i].count +"</td></tr>";
						}
						if(data.topklist.length<3)
						{
							for(var i=0;i<3-data.topklist.length;i++){
								html += "<tr><td  class='content' >"+"</td><td  class='content' >"+"</td></tr>";
							}
						}
						$("#tableFrequencyId").html(html);
						<%--$("#txtArea").val("");--%>
	   				}
	   			});
	   		});
	   		
	   		//清空内容按钮
	   		$("#resetContent").click(function(){
	   			$("#txtArea").val("");
	   		});
	   });
		//把内容清空
	   function empty(){
	   		for(var i=0;i<document.getElementsByClassName("content").length;i++){
				document.getElementsByClassName("content")[i].innerHTML = "";
			}
	   }
	   //radio控制文件上传方式
	   function judgeRadioValue(){
	   		var radios = document.getElementsByName("type"); 
	   		//根据 name集合长度 遍历name集合  
    		for(var i=0;i<radios.length;i++){   
		        //判断那个单选按钮为选中状态  
		        if(radios[i].checked && radios[i].value == '文件上传'){
		        	if(document.getElementById("txtArea").nodeValue != "" ){
						empty();
					}

		            document.getElementById("div1").style.display = "block";
		            document.getElementById("div2").style.display = "none";

		        }
		        if(radios[i].checked && radios[i].value == '文本输入'){
		        	if(document.getElementById("myFile").nodeValue!=""){
						empty();
					}
		            document.getElementById("div2").style.display = "block";
		            document.getElementById("div1").style.display = "none";
		        }   
	    	}   
	   }
   
   		//限制上传文件大小及格式 
   		function fileChange(target) {
			var fileSize = 0;
			var filetypes =[".txt"];
			var filepath = target.value;
			var filemaxsize = 1024*100;//4M,可以自己调节
			if(filepath){
				var isnext = false;
				var fileend = filepath.substring(filepath.indexOf("."));
				if(filetypes && filetypes.length>0){//判断是否是txt格式的文件
					for(var i = 0; i < filetypes.length;i++){
						if(filetypes[i] == fileend){
							isnext = true;
							break;
						}
					}
				}
				if(!isnext){
					alert("不接受此文件类型！");
					target.value ="";
					return false;
				}else{
					var file = document.getElementById("myFile");
					var fileSize = file.files[0].size;
					var size = fileSize / 1024;
					if(size > filemaxsize){
						alert("附件大小不能大于"+filemaxsize/1024+"M！");
						target.value ="";
						return false;
					}
				}
			}
		} 
  </script>
  </head>
  <body>
    	<p class="title">请选择一段文字</p>
		<div style="overflow:hidden;margin-left: -10px">
    	<div  style="width: 400px;height: 130px;">
    		<input type="radio" value="文件上传" name="type" onClick="judgeRadioValue();" id="radio"/>文件上传&nbsp;&nbsp;&nbsp;
	    	<input type="radio" value="文本输入" name="type" onClick="judgeRadioValue();"/>文本输入<br/>
    		<div id="div1" style="margin-top: 30px" >
    			<input id="myFile" type="file" name="file" value="上传文件"  style="display:none;" onchange="fileChange(this);changeAgentContent();">

				<input	type="button" class="Uploadfile" onclick="document.getElementById('myFile').click()" value="上传文件" style="height: 25px;width: 80px;
				display:inline;background-color:#00CCFF;color:#FFFFFF;font-weight:bold;border: none"/>
				<input type="text"  value="" disabled    id="inputFileAgent" style="display:inline;background-color: #FFF;border: none;margin-left: 10px"/>
    			<input type="submit"  id="submit" value="统计" style="display:inline;float:right;background-color: #00CCFF;width: 80px;height: 25px;;color:#FFFFFF;font-weight:bold;border: none"/>
				<script 	type="text/javascript">
					function changeAgentContent(){
						document.getElementById("inputFileAgent").value
							= document.getElementById("myFile").value;
					}
				</script>
    		</div>
   			<div id="div2" ><%--onfocus="if (value == '请在此输入文本内容'){value =''}" onblur="if (value == '') {value='请在此输入文本内容'}"--%>
    			<textarea rows="5" cols="40" id="txtArea" class="txtArea" style="float:left;" placeholder="请在此输入文本内容"></textarea>
				<div style="float:left;">
					<input type="button" class="statistics" value="统计" id="counter" style="margin-top: 10px;margin-left: 10px;height: 25px;;color:#FFFFFF;font-weight:bold;border: none" />
					<br/><br/>
					<input type="reset" value="清空内容" id="resetContent" style="width:80px;margin-left: 10px;height: 25px;background-color:#FF9900;color:#FFFFFF;font-weight:bold;border: none" class="btn"/>
				</div>
    		</div>
    	</div>

    	<div style="width: 400px;">
			<p style="display:inline" >各统计内容的个数如下：</p>
    		<table id="tableCounterId" class="table">
    			<tr>
    				<th>统计项</th>
    				<th>个数</th>
    			</tr>
    			<tr >
    				<td align="center">英文字母</td>
    				<td id="character" class="content" align="center"></td>
    			</tr>
    			<tr >
    				<td align="center">数字</td>
    				<td id="digital"  class="content" align="center"></td>
    			</tr>
    			<tr >
    				<td align="center">中文汉字</td>
    				<td id="chinese"  class="content" align="center"></td>
    			</tr>
    			<tr >
    				<td align="center">中英文标点符号</td>
    				<td id="punctuation" class="content" align="center"></td>
    			</tr>
    		</table>
    		<p>文字中出现频率最高的三个字是：</p>
    		<table id="tableFrequencyId" class="table">
				<tr  id="tr1">
					<th>名称</th>
					<th>个数</th>
				</tr>
				<tr id="tr2">
					<td id="top1" align="center"></td>
					<td id="top1Count" align="center"></td>
				</tr>
				<tr id="tr3">
					<td id="top2" align="center"></td>
					<td id="top2Count" align="center"></td>
				</tr>
				<tr id="tr4">
					<td id="top3" align="center"></td>
					<td id="top3Count" align="center"></td>
				</tr>
    		</table>
    	</div>
		</div>
  </body>
</html>
