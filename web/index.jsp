<%--
  Created by IntelliJ IDEA.
  User: chunming.xcm
  Date: 2017/2/23
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>

<%@ page language = "java" pageEncoding = "UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
  <meta charset = "utf-8">
  <script type = "text/javascript" >
      function clearTable() {
          tdSpans = document.getElementsByName("tdSpan");
          for(var i = 0; i < tdSpans.length; i++){
              tdSpans[i].innerHTML = "&nbsp";
          }
      }
      function showTextarea() {
          document.getElementById("hidden").style.display = "inline";
          document.getElementById("show").style.display = "none";
          clearTable();
      }
      function hiddenTextarea() {
          document.getElementById("hidden").style.display = "none";
          document.getElementById("show").style.display = "inline";
          clearTable();
      }
      function checkForm(form) {
          if(form.radio.value == "upload_radio"){
              if (form.submit_upload.value == ""){
                  alert("请上传文件!");
                  form.userId.focus();
                  return false;
              }
          }
      }
      function numDesc(a, b) {
          return b - a;
      }

      function display(text) {
          var obj = eval("(" + text + ")");
          var englishLet = document.getElementById("englishLet");
          var number = document.getElementById("number");
          var chineseChar = document.getElementById("chineseChar");
          var punctuation = document.getElementById("punctuation");
          englishLet.innerHTML = obj.englishLet;
          number.innerHTML = obj.number;
          chineseChar.innerHTML = obj.chineseChar;
          punctuation.innerHTML = obj.punctuation;

          var arrKey = [];
          var arrValue = [];
          var flag = 0;
          for(var key in obj) {
              if(key != "englishLet" && key != "number" && key != "chineseChar"
                  && key != "punctuation") {
                  arrKey[flag] = key;
                  arrValue[flag] = obj[key];
                  flag++;
              }
          }
          var arrValueCopy = arrValue;
          arrValueCopy.sort(numDesc);

          var first = document.getElementById("first");
          for(var i = 0; i < 3; i++) {
              if(arrValueCopy[0] == arrValue[i]) {
                  first.innerHTML = arrKey[i];
              }
          }
          var firstValue = document.getElementById("firstValue");
          firstValue.innerHTML = arrValueCopy[0];

          var second = document.getElementById("second");
          for(var i = 0; i < 3; i++) {
              if(arrValueCopy[1] == arrValue[i]) {
                  second.innerHTML = arrKey[i];
              }
          }
          var secondValue = document.getElementById("secondValue");
          secondValue.innerHTML = arrValueCopy[1];

          var third = document.getElementById("third");
          for(var i = 0; i < 3; i++) {
              if(arrValueCopy[2] == arrValue[i]) {
                  third.innerHTML = arrKey[i];
              }
          }
          var thirdValue = document.getElementById("thirdValue");
          thirdValue.innerHTML = arrValueCopy[2];
      }

      function createXMLHttpRequest() {
          try {
              return new XMLHttpRequest();
          } catch (e) {
              try {
                  return ActvieXObject("Msxml2.XMLHTTP");
              } catch (e) {
                  try {
                      return ActvieXObject("Microsoft.XMLHTTP");
                  } catch (e) {
                      alert("未知浏览器");
                      throw e;
                  }
              }
          }
      }
      function stringMode() {
          var stringEle = document.getElementById("stringEle");
          var xmlHttp = createXMLHttpRequest();
          xmlHttp.open("POST", "<c:url value='/count/stringCount'/>",
              true);
          xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
          xmlHttp.send("string=" + stringEle.value);
          xmlHttp.onreadystatechange = function() {
              if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                  var text = xmlHttp.responseText;
                  display(text);
              }
          };
      }
      function fileMode() {
          var fileEle = document.getElementById("fileEle");
          var xmlHttp = createXMLHttpRequest();
          xmlHttp.open("POST", "<c:url value='/count/fileCount'/>", true);
          var form = new FormData();
          form.append("file", fileEle.files[0]);
          xmlHttp.send(form);
          xmlHttp.onreadystatechange = function() {
              if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                  var text = xmlHttp.responseText;
                  display(text);
              }
          };
      }
  </script>
  <style type="text/css">
    body{
      font-family: "宋体" , serif;
      font-size: 14px;
      font-style: normal;
      text-align: center;
      margin: 0 auto;

    }
    #main_table{
      border: 1px solid #000;
      text-align: left;
      margin:auto;
      margin-top: 20px;
    }
    .sub_table {
      width: 350px;
      border-right:1px solid #000;
      border-bottom:1px solid #000;
      text-align:center
    }
    .sub_table tr{
      line-height:30px;
    }
    .sub_table td{
      line-height:30px;
      cellpadding: 0px;
      cellspacing: 0px;
      border-left:1px solid #000;
      border-top:1px solid #000;
    }
    .smt{
      font-family: "宋体" , serif;
      font-size: 14px;
      width: 80px;
      height: 30px;
      border:0;
      background-color:#3CB6F1;
      margin:auto 10px
    }
    #choose_words{
      text-align:left;
    }
    #stringEle{
      width:350px;
      height: 80px;
      margin-top: 5px;
      padding: 0;
      vertical-align:top;
      word-break:break-all;
    }
  </style>
  <title>CharacterCounter</title>
</head>

<body>
<table id = "main_table" width="500" cellpadding="5" cellspacing="10">
  <tbody>
  <tr>
    <td height="100" text-align="left" valign="top">
      <form id="submitform" method="post" >
        <div id="choose_words">
          <p>请选择一段文字</p>
          <p>
            <input type="radio"  name="radio" id="upload_radio" checked="checked" value="upload_radio" onClick='hiddenTextarea()'>文件上传
            <input type="radio" name="radio" id="input_radio" style="margin-left:100px" value="input_radio" onClick='showTextarea()'>文本输入
          </p>
          <div id="show">
            <p>
              <input type="button" value="上传文件" onclick="fileEle.click()" class="smt">
              <input type="text" size="30" name="upfile" id="upfile" style="border:0px">
              <input type="file" id="fileEle" class="smt" style="margin-right:255px; display:none" formmethod="POST"
                     value="上传文件" onchange="upfile.value=this.value">
              <input type="button" name="button_count" class="smt" formmethod="POST" value="统计"
                     onclick="fileMode()">
            </p>
          </div>
          <div id="hidden" style="display:none">
            <table>
              <tr>
                <td>
	                    	<textarea id="stringEle" name="string" value="请在此输入文本内容"
                                      onfocus="if (value =='请在此输入文本内容'){value =''}"></textarea>
                </td>
                <td>
                  <input type="button" class="smt" margin-left="5px" name="button_count"
                         formmethod="POST" value="统计", onclick="stringMode()">
                  <input type="button" class="smt" style="background-color:#E97A25; margin-top:5px"
                         id="clear" formmethod="POST" value="清空内容"
                         onclick="document.getElementById('stringEle').value='';clearTable()">
                </td>
              </tr>
            </table>
          </div>
        </div>
      </form>

    </td>
  </tr>
  <tr style="text-align: left">
    <td><p>各统计内容的个数如下</p>
      <table class="sub_table" height="168">
        <tbody style="font-size: 9px">
        <tr>
          <td width="192"><spans style="font-weight: bold">统计项</spans></td>
          <td width="192"><spans style="font-weight: bold">个数</spans></td>
        </tr>
        <tr>
          <td>英文字母</td>
          <td><span id="englishLet" name="tdSpan"></span></td>
        </tr>
        <tr>
          <td>数字</td>
          <td><span id="number" name="tdSpan"></span></td>
        </tr>
        <tr>
          <td>中文汉字</td>
          <td><span id="chineseChar" name="tdSpan"></span></td>
        </tr>
        <tr>
          <td>中英文标点符号</td>
          <td><span id="punctuation" name="tdSpan"></span></td>
        </tr>
        </tbody>
      </table>
    </td>
  </tr>
  <tr>
    <td><p>文字中出现频率最高的三个字是：</p>
      <table class="sub_table">
        <tbody>
        <tr>
          <td><spans style="font-weight: bold;">名称</spans></td>
          <td><spans style="font-weight: bold;">个数</spans></td>
        </tr>
        <tr>
          <td><span id="first" name="tdSpan">&nbsp</span></td>
          <td><span id="firstValue" name="tdSpan"></span></td>
        </tr>
        <tr>
          <td><span id="second" name="tdSpan">&nbsp</span></td>
          <td><span id="secondValue" name="tdSpan"></span></td>
        </tr>
        <tr>
          <td><span id="third" name="tdSpan">&nbsp</span></td>
          <td><span id="thirdValue" name="tdSpan"></span></td>
        </tr>
        </tbody>
      </table>
    </td>
  </tr>
  </tbody>
</table>
</body>
</html>
