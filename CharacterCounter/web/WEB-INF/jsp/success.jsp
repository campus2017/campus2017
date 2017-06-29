<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>success</title>
</head>
<body>
    上传成功！</br>
    存储的文件名为：${requestScope.name}<br/>
    <form action="/CharacterCounter/returnStart" method="post">
        <input type="submit" value="返回首页"/>
    </form>
</body>
</html>
