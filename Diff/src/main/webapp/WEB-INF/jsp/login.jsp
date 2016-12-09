<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: youthlin.chen
  Date: 2016-11-15 015
  Time: 20:45 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@ include file="head.jsp" %>
    <title>登录</title>
</head>
<body>
<div class="container">
    <h3>登录</h3>
    <form class="form-horizontal" role="form" action="<c:url value="/login.do"/>" method="post">
        <p>登录后可以管理历史记录。</p>
        <c:if test="${not empty error}">
            <div class="form-group text-danger">
                <label class="col-sm-2 control-label">错误</label>
                <div class="col-sm-10">
                    <p class="form-control-static">${error}</p>
                </div>
            </div>
        </c:if>
        <div class="form-group">
            <label class="col-sm-2 control-label" for="username">用户名</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="username" name="username" placeholder="用户名">
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label" for="password">密码</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="password" name="password" placeholder="密码">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-primary">登录</button>
                <a class="btn btn-default" href="<c:url value="/list"/>">匿名访问</a>
            </div>
        </div>
    </form>
</div>
</body>
</html>
