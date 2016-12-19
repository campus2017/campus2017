<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<form action="hello" method="post" enctype="multipart/form-data">
    最简单的文件上传：<input type="file" name="fileupload"/>
    描述：<input type="text" name="desc"/>
    <input type="submit" value="submit"/>
</form>
</body>
</html>