<%--
  Created by IntelliJ IDEA.
  User: Vsur-Pc
  Date: 2017/7/3
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>字符统计</title>
    <script src="js/jquery.min.js"></script>
    <script>
       function onClickFileRadio(){
           $("#filediv").show();
           $("#textdiv").hide();
       }
       function onClickTextRadio(){
           $("#filediv").hide();
           $("#textdiv").show();
       }
       function onClickClearButton(){
           $("#textinput").val("");
       }
       function onClickFileupButton(){

           var formData = new FormData($("#form1")[0]);
         //  formData.append("file",$("#upload")[0]);
           //alert($("#upload")[0]);
           $.ajax({
               url : "characterCounter",
               type : 'POST',
               data : formData,
// 告诉jQuery不要去处理发送的数据
               processData : false,
// 告诉jQuery不要去设置Content-Type请求头
               contentType : false,
               beforeSend:function(){
                   for(var i=1;i<=4;i++){
                       $("#lable"+i).html("");
                       for(var i=1;i<=3;i++) {
                           $("#name"+i).html("");
                           $("#sum"+i).html("");
                       }
                   }
               },
               success : function(responseStr) {
                   var number = responseStr.number;
                   for(var i=1;i<=number.length;i++){
                       $("#lable"+i).html(number[i-1]);
                   }
                   var set = responseStr.set;
                   for(var i=1;i<=set.length;i++) {
                       $("#name"+i).html(set[i-1].key);
                       $("#sum"+i).html(set[i-1].value);
                   }
               },
               error : function(responseStr) {
                   console.log("error");
               }
           });
       }
       function onClickTextupButton(){
         //  alert($("#textinput").val())
           $.ajax({
               type: "POST",
               url: "characterCounterOfString",
               data: {"str":$("#textinput").val()},
               dataType: "json",
               beforeSend:function(){
                   for(var i=1;i<=4;i++){
                       $("#lable"+i).html("");
                       for(var i=1;i<=3;i++) {
                           $("#name"+i).html("");
                           $("#sum"+i).html("");
                       }
                   }
               },
               success : function(responseStr) {
                   var number = responseStr.number;
                   for(var i=1;i<=number.length;i++){
                       $("#lable"+i).html(number[i-1]);
                   }
                   var set = responseStr.set;
                   for(var i=1;i<=set.length;i++) {
                       $("#name"+i).html(set[i-1].key);
                       $("#sum"+i).html(set[i-1].value);
                   }
               },
               error : function(responseStr) {
                   console.log("error");
               }
           });
       }
    </script>
  </head>
  <p>请选择一段文字</p>
  <input type="radio" name = "radioselect" onclick="onClickFileRadio()">文件上传&nbsp;&nbsp;
  <input type="radio" name = "radioselect" onclick="onClickTextRadio()">文本输入
  <div id="filediv">
    <form id="form1">
     <input type="file" id="upload" name="file"/>
      <input type="button" value="统计" onclick="onClickFileupButton()"/>
    </form>

  </div>
  <div id="textdiv" style="display:none;">
    <table>
    <tr>
      <td><textarea name=" " id="textinput" cols="30" rows="5"></textarea> </td>
      <td> <input type="button" value="统计" onclick="onClickTextupButton()"/><br>
           <input type="button" value="清空内容" onclick="onClickClearButton()"/>
      </td>
    </tr>
    </table>
  </div>
  <p>各项统计的内容结果如下：</p>
  <table  border="1" cellspacing="0">
    <tr>
    <td style="width:100px;height:30px">统计项</td> <td style="width:100px;height:30px"> 个数</td>
  </tr>
    <tr>
      <td style="width:100px;height:30px">英文字母</td> <td style="width:100px;height:30px"> <label id="lable1"></label></td>
    </tr>
    <tr>
      <td style="width:100px;height:30px">数字</td> <td style="width:100px;height:30px"> <label id="lable2"></label></td>
    </tr>
    <tr>
      <td style="width:100px;height:30px">中文汉字</td> <td style="width:100px;height:30px"> <label id="lable3"></label></td>
    </tr>
    <tr>
      <td style="width:100px;height:30px">中英文标点符号</td> <td style="width:100px;height:30px"> <label id="lable4"></label></td>
    </tr>
  </table>
  <p>文字中出现频率最高的三个字是：</p>
  <table  border="1" cellspacing="0">
    <tr>
      <td  style="width:100px;height:30px">名称</td> <td style="width:100px;height:30px"> 个数</td>
    </tr>
    <tr>
      <td style="width:100px;height:30px"><label id="name1"></label></td> <td style="width:100px;height:30px"> <label id="sum1"></label></td>
    </tr>
    <tr>
      <td style="width:100px;height:30px"><label id="name2"></label></td> <td style="width:100px;height:30px"> <label id="sum2"></label></td>
    </tr>
    <tr>
      <td style="width:100px;height:30px"><label id="name3"></label></td> <td style="width:100px;height:30px"> <label id="sum3"></label></td>
    </tr>

  </table>
  </body>
</html>
