<%--
  Created by IntelliJ IDEA.
  User: youthlin.chen
  Date: 2016-11-17 017
  Time: 14:43 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%
    //    response.sendRedirect("list");
    request.getRequestDispatcher("list").forward(request, response);
    return;
%>
