<%--
  Created by IntelliJ IDEA.
  User: FGT
  Date: 2017/6/3
  Time: 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EffectiveLines</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form action="/EffectiveLines.do" enctype="multipart/form-data" method="post" >
    上传文件：<br>
    <input type="file" name="file" />
    <input type="submit" />
</form>
<div>
  ${message}
</div>

</body>
</html>
