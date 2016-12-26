<%--
  Created by IntelliJ IDEA.
  User: tianyiren
  Date: 16-12-23
  Time: 下午12:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传失败！</title>
</head>
<body>
    ${requestScope.message}
    <form action="/CharacterCounter/uploadFile" method="post">
        <input type="submit" value="返回文件上传页"/>
    </form>
    <form action="/CharacterCounter/start" method="post">
        <input type="submit" value="返回&nbsp;&nbsp;首页"/>
    </form>
</body>
</html>
