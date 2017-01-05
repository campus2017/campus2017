<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="common/global.jsp" %>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<title>CharacterCounter</title>
<link rel="stylesheet" href="css/base.css" />
<link rel="stylesheet" href="css/bootstrap.min.css" />
<script  src="js/jquery-1.11.1.js" ></script>
</head>

<body>
<div class="row h20"></div>
<div class=" container " style="width: 760px">
<div class="pl20 row  mb10"><h1>CharacterCounter</h1></div>

<div class="row  mb20">
	<div class="col-md-12">请选择一段文字</div>
</div>

  <div class="row mb20">
    <div class="col-md-2"><label><input type="radio"  name="selectType" />文件上传</label></div>
    <div class="col-md-10"><label><input type="radio"  name="selectType" checked />文本输入<label></div>
  </div>


      <div class="row">
          <form id="funForm" method="post"  >
        <div class="col-md-6">
            <textarea name="wordStr" style="width:100% ; height:80px" ></textarea>
        </div>
          </form>
        <div class="col-md-6">
            <button type="button" onclick="clickOnStatistic()" class="btn btn-primary">统计</button>
        </div>
      </div>

  <div class="row">
    <div class="col-md-12">各统计内容的个数如下:</div>
  </div>
  <div class="row">
  	<div class="col-md-6">
  		<table  class="  table table-striped table-bordered table-hover mt10 ml0 mr0 mb20">
   <thead>
     <tr><th style="width:150px">统计项</th><th style="width:150px">个数</th></tr>
   </thead>
   <tbody>
     <tr><td>英文字母</td>
         <td><label class="lbo" id="ENG">&nbsp;</label></td ></tr>
     <tr> <td>数字</td>
         <td><label class="lbo" id="NUM">&nbsp;</label></td> </tr>
     <tr><td>中文汉字</td>
         <td><label class="lbo" id="CHZ">&nbsp;</label></td></tr>
     <tr><td>中英文标点符号</td>
         <td><label class="lbo" id="PUNCT">&nbsp;</label></td></tr>
   </tbody>
 </table>
  	</div>
  </div>
  
 <div class="row">
    <div class="col-md-12">文中出现频率最高的三个字是:</div>
  </div>
  <div class="row" >
  	<div class="col-md-6">
  		 <table  class=" mt10 ml0 mr0 mb20 table table-striped table-bordered table-hover ">
   <thead>
     <tr><th style="width:150px">名称</th><th style="width:150px">个数</th></tr>
   </thead>
   <tbody>
   <c:forEach begin="0" step="1" end="2" varStatus="status" >
       <tr> <td><label class="tpi" id="top${status.index}"></label></td>
       <td><label class="tpv" id="nm${status.index}"></label></td></tr>
   </c:forEach>
     <%--<tr><td><label class="tpi" id="top0"></label></td>
         <td></td><label class="tpv" id="nm0"></label></tr>
     <tr> <td></td>
         <td> </td> </tr>
     <tr><td></td>
         <td></td></tr>--%>
   </tbody>
 </table>
  	</div>
  </div>

</div>
<!--js Begin-->
<script type="text/javascript">
    function clickOnStatistic( ){
        $.ajax({
            type: "POST",
            url: "work/counterStr.action",
            dataType: 'json',
            data:	$('#funForm').serialize() ,
            success: function(msg) {
              $("#ENG").html( msg.stmap.ENG);
                $("#NUM").html( msg.stmap.NUM);
                $("#CHZ").html( msg.stmap.CHZ);
                $("#PUNCT").html( msg.stmap.PUNCT);
                $(".tpi").each(function(index){
                    $(this).html(msg.tplist[index].val);
                    $("#nm"+index).html(msg.tplist[index].count);
                });
            }
        });
    }

</script>
<!--js End-->
</body>
</html>
