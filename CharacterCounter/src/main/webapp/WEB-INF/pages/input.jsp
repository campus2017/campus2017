
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>input</title>
</head>
<body>

<form:form method="post" action="/counter" >  <!-- 映射的东西-->
    <form:textarea path="inputString"  cols="100" rows="20"/>

    <input type="submit" value="统计"/>
    <input type="reset" value="清空内容"/>

</form:form>

</body>
</html>
