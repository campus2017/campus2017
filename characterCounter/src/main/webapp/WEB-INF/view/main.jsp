<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>CharacterCounter</title>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	
	<script type="text/javascript">
		$(function(){
			$('#fileUploadBtn').click(function(){
				$('#textInputTable').hide();
				$('#fileUploadTable').show();
			});
			$('#textInputBtn').click(function(){
				$('#textInputTable').show();
				$('#fileUploadTable').hide();
			});
			
			$('#doCharacterCount').click(function(){
					
				var content = $('#text').val();
				
				$.ajax({
					data:{"text":content},
					type:"post",
					url:"${pageContext.request.contextPath}/characterCounter/doCharacterCounter",
					success:function(model){
						for(attr in model){
						
							$('#'+attr).html(model[attr]);
						
						}
						
						 var len = $("#top3CharacterCount tr").length;  
					        if(len > 1)  
					            $("#top3CharacterCount tr").remove();  
					    var head = "<tr><td>名称</td><td>个数</td></tr>";
						$("#top3CharacterCount").append(head);
						for(var key in model["topCharacter"]){
							
							var countResult = "<tr><td>"+key+"</td><td>"+model["topCharacter"][key]+"</td></tr>";
							$("#top3CharacterCount").append(countResult);
						}
					
					}
				
				});
				
			
			});
			
			$('#doFileUploadCharacterCount').click(function(){
				
				$.ajax({
					data: new FormData($('#fileForm')[0]),
					type:"post",
					url:"${pageContext.request.contextPath}/characterCounter/fileUploadCharacterCount",
					processData: false,
    				contentType: false,
					success:function(model){
						for(attr in model){
							$('#'+attr).html(model[attr]);
						}
					
						 var len = $("#top3CharacterCount tr").length;  
					        if(len > 1)  
					            $("#top3CharacterCount tr").remove();  
					    var head = "<tr><td>名称</td><td>个数</td></tr>";
						$("#top3CharacterCount").append(head);
						for(var key in model["topCharacter"]){
							alert(key);
							var countResult = "<tr><td>"+key+"</td><td>"+model["topCharacter"][key]+"</td></tr>";
							$("#top3CharacterCount").append(countResult);
						}
					
					}
				
				});
				
				$('#fileForm').submit(function(){
					
					return false;
				});
			
			});
		
		});
	
	</script>
  </head>
  
  <body>
	  <fieldset>
	  	<legend>请选择一段文字</legend>
	  	<form id="textForm" action="${pageContext.request.contextPath}/characterCounter/doCharacterCounter" method="post">
	  		<table id="radioBtnTable">
	  			<tr>
	  				<td rowspan="3" colspan="4">
	  					<input id="fileUploadBtn" type="radio" value="" checked="checked" name="chooseBtn">文件上传</input>
	  				</td>
	  				<td rowspan="3" colspan="4">
	  					<input id="textInputBtn" type="radio" value="" name="chooseBtn">文本输入</input>
	  				</td>
	  			</tr>
	  		</table>
	  	
	  		
	  		<table id="textInputTable" style="display:none;">
	  			<tr>
	  				<td rowspan="6" colspan="22">
	  					<textarea rows="8" cols="22" id="text"></textarea>
	  				</td>
	  				<td rowspan="3" colspan="4">
	  					<input id="doCharacterCount" type="button" value="统计" name="count">
	  				</td>
	  				<td rowspan="3" colspan="4">
	  					<input id="clearContext" type="reset" value="清空内容" name="clearContext">
	  				</td>
	  			</tr>
	  		</table>
	  	
	  </form>
	  	
	  	<form id="fileForm" action="${pageContext.request.contextPath}/characterCounter/fileUploadCharacterCount" enctype="multipart/form-data" method="post">
	  		<table id="fileUploadTable">
	  			<tr>
	  				<td rowspan="3" colspan="4">
	  				上传文件：<input type="file" name="file"/>
	  				</td>
	  				<td rowspan="3" colspan="4">
	  					<input id="doFileUploadCharacterCount" type="button" value="统计" name="count">
	  				</td>
	  			</tr>
	  		</table>
	  	</form>
	  		<h6>统计内容的个数如下：</h6>
	  		<table border="1">
	  			<tr>
	  				<td>统计项</td>
	  				<td>个数</td>
	  			</tr>
	  			<tr>
	  				<td>英文字母</td>
	  				<td id="englishCount"></td>
	  			</tr>
	  			<tr>
	  				<td>数字</td>
	  				<td id="numCount"></td>
	  			</tr>
	  			<tr>
	  				<td>中文汉字</td>
	  				<td id="chineseCount"></td>
	  			</tr>
	  			<tr>
	  				<td>中英文标点符号</td>
	  				<td id="otherCount"></td>
	  			</tr>
	  		
	  		</table>
	  	
	  	<h6>文章中出现频率最高的三个字符：</h6>
	  		<table id="top3CharacterCount" border="1">
	  			<tr>
	  				<td>名称</td>
	  				<td>个数</td>
	  			</tr>
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
	  			
	  			
	  		</table>
	 
	  	
	  </fieldset>
    
    
  </body>
</html>
