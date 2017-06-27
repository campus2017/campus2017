<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>SpringMVC Demo 首页</title>
</head>
<body>
<h1>这里是SpringMVC Demo首页</h1>
<h3>出现此页面，说明配置成功。</h3>
<form action="login.html" method="post">
    username:<input type="text" name="username" />
    <p>
        password:<input type="password" name="password"/>
    <p>
        <input type="submit" value="submit" />
</form>

<form action="CharacterCounter" method="post">
    CharacterText:<input type="text" name="CharacerText">
    <p>
        <input type="submit" value="submit" />
</form>
</body>
</html>