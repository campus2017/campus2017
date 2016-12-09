<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: youthlin.chen
  Date: 2016-11-16 016
  Time: 10:13 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@ include file="head.jsp" %>
    <title>文件比较</title>
</head>
<body>
<div class="container">

    <h3>上传文件</h3>
    <form class="form-horizontal" role="form" action="<c:url value="/upload"/>" method="post"
          enctype="multipart/form-data">
        <p>请选择文件大小在 1KB 内的文本文件进行比较。</p>
        <c:if test="${not empty error}">
            <div class="form-group text-danger">
                <label class="col-sm-2 control-label">错误</label>
                <div class="col-sm-10">
                    <p class="form-control-static">${error}</p>
                </div>
            </div>
        </c:if>
        <div class="form-group">
            <label class="col-sm-2 control-label" for="source-file">源文件</label>
            <div class="col-sm-10">
                <input type="file" class="form-control" id="source-file" name="source-file" placeholder="源文件">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label" for="target-file">目标文件</label>
            <div class="col-sm-10">
                <input type="file" class="form-control" id="target-file" name="target-file" placeholder="目标文件">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-primary">上传</button>
            </div>
        </div>
    </form>

    <h3>最近5条历史对比结果</h3>
    <p><c:choose>
        <c:when test="${current_user.username != 'anon'}">
            您已登录为：${current_user.username} . <a href="<c:url value="/logout"/>">注销</a>
        </c:when>
        <c:otherwise>
            正以匿名用户的身份浏览， <a href="<c:url value="/login"/>">登录</a> 以管理历史记录
        </c:otherwise>
    </c:choose></p>
    <div class="table-responsive">
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <td>序号</td>
                <td>对比时间</td>
                <td>源文件内容</td>
                <td>目标文件内容</td>
                <td>差异</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody id="tbody">
            <c:set var="count" value="0"/>
            <c:if test="${not empty list}">
                <c:forEach items="${list}" var="diff">
                    <c:set var="count" value="${count+1}"/>
                    <c:choose>
                        <%--1/2--%>
                        <c:when test="${count lt 3}">
                            <tr class="count count-${count} did-${diff.did}">
                        </c:when>
                        <%--3/4/5 hide--%>
                        <c:otherwise>
                            <tr class="count count-${count} did-${diff.did} hide">
                        </c:otherwise>
                    </c:choose>
                    <td>${count}</td>
                    <td><fmt:formatDate value="${diff.compareTime}" pattern="yyyy-MM-dd"/></td>
                    <td>${diff.source}</td>
                    <td>${diff.target}</td>
                    <td>${diff.onlySource}${diff.onlyTarget}${diff.differing}</td>
                    <td>
                        <c:choose>
                            <c:when test="${current_user.username != 'anon'}">
                                <a class="delete-btn" data-user="${current_user.userId}" data-did="${diff.did}">删除</a>
                            </c:when>
                            <c:otherwise>
                                <a href="<c:url value="/login"/>" title="登录后可管理历史记录">登录</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    </tr>
                </c:forEach>
                <c:if test="${count gt 3}">
                    <tr>
                        <td>共 <span class="sum">${count}</span> 条</td>
                        <td>
                            <button class="more btn btn-default">查看更多</button>
                        </td>
                        <td colspan="4"></td>
                    </tr>
                </c:if>
            </c:if>
            <c:if test="${empty list}">
                <tr>
                    <td colspan="6" class="text-danger text-center"><strong>没有数据</strong></td>
                </tr>
            </c:if>
            </tbody>
        </table>

    </div>
</div>
<script>
    $(document).ready(function () {
        var count = ${count};
        var list = $('.count');
        var btn = $('.more');
        if (count == 3) {
            list.removeClass('hide').hide().show('slow');
        }
        if (count > 3) {
            btn.click(function (e) {
                var i = 1;
                list.each(function (index, e) {
                    if (i > 2)return;
                    if ($(e).hasClass('hide')) {
                        $(e).removeClass('hide').hide().show('slow');
                        i++;
                    }
                });
                i = 0;
                list.each(function () {
                    if ($(this).hasClass('hide')) {
                        i++;
                    }
                });
                if (i == 0) {
                    btn.addClass('hide');
                }
            });
        }

        //delete
        $('.delete-btn').click(function (e) {
            e.preventDefault();
            var userId = $(this).data('user');
            var did = $(this).data('did');
            var tbody = $('#tbody');
            var noData = '<tr><td colspan="6" class="text-danger text-center"><strong>没有数据</strong></td></tr>';
            var sum = $('.sum');
            $.ajax({
                url: "delete",
                type: 'POST',
                data: {'userId': userId, 'did': did},
                dataType: 'json',
                success: function (e) {
                    if (e.code == 0) {
                        $('.did-' + did).hide('slow').remove();
                        sum.text(sum.text() - 1);
                        if (tbody.children().length == 0) {
                            tbody.append(noData);
                        }
                    } else {
                        console.log(e.data);
                    }
                },
                error: function (e) {
                    console.log(e);
                }
            });
        });
    });
</script>
</body>
</html>
