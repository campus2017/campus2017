<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>CharacterCount</title>
	</head>
	<body>
		<form action="<c:url value="/CharacterCount.html"/>" method="post">
		请输入一段文字：
		<br>
		<textarea name="input" rows="15" cols="20" >${r.input}</textarea>
       <br>
       <input type="submit" value="统计" />  <input type="reset" value="清空" />
       <br>
       </form>
       <c:if test="${empty error}">
各项统计结果如下：
      <table border=1>
        <tr>
          <th>统计项</th>
          <th>个数</th>
        </tr>
        <tr>
          <td>英文字母</td>
          <td> ${r.number[2]}</td>
        </tr>
      <tr>
          <td>数字</td>
          <td> ${r.number[1]} </td>
        </tr>
      <tr>
          <td>中文字母</td>
          <td>${r.number[0]}  </td>
        </tr>
      <tr>
          <td>中英文标点</td>
          <td> ${r.number[3]}</td>
        </tr>
      </table>
      出现频率最高的三个是：
      <table border=1>
        <tr>
          <th>名称</th>
          <th>个数</th>
        </tr>
        <tr>
          <td height=18px>   ${r.mostTimesChar[0]}</td>
          <td>  ${r.charNumber[0]} </td>
        </tr>
      <tr>
          <td height=18px>  ${r.mostTimesChar[1]}  </td>
          <td>   ${r.charNumber[1]}</td>
        </tr>
      <tr>
          <td height=18px>  ${r.mostTimesChar[2]}  </td>
          <td>  ${r.charNumber[2]} </td>
        </tr>
      </table>
       <br>

       		</c:if>


	</body>
</html>