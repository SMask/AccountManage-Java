<%--
  Created by IntelliJ IDEA.
  User: server
  Date: 2018/11/1
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

    <title>博客管理</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
    <![endif]-->

</head>
<body>

<div class="container">
    <h1>博客系统 - 博客管理</h1>
    <hr/>

    <h3>所有博客 <a href="${pageContext.request.contextPath}/admin/blogs/add" type="button"
                class="btn btn-primary btn-sm">添加</a>
    </h3>

    <!-- 如果博客列表为空 -->
    <c:if test="${empty blogList}">
        <div class="alert alert-warning" role="alert">
            <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> Blog表为空，请
            <a href="${pageContext.request.contextPath}/admin/blogs/add" type="button"
               class="btn btn-primary btn-sm">添加</a>
        </div>
    </c:if>

    <!-- 如果博客列表非空 -->
    <c:if test="${!empty blogList}">
        <table class="table table-bordered table-striped" style="table-layout: fixed">
            <tr>
                <th style="width: 5%">ID</th>
                <th style="width: 10%">标题</th>
                <th style="width: 25%">内容</th>
                <th style="width: 15%">作者</th>
                <th style="width: 15%">发布时间</th>
                <th style="width: 15%">修改时间</th>
                <th style="width: 15%">操作</th>
            </tr>
            <c:forEach items="${blogList}" var="blog">
                <tr>
                    <td>${blog.id}</td>
                    <td>${blog.title}</td>
                    <td style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap">${blog.content}</td>
                    <td>${blog.userByUserId.id} - ${blog.userByUserId.username} - ${blog.userByUserId.nickname}</td>
                    <td>
                        <c:if test="${blog.publishTime>0}">
                            <jsp:useBean id="publishTimeValue" class="java.util.Date"/>
                            <jsp:setProperty name="publishTimeValue" property="time" value="${blog.publishTime}"/>
                            <fmt:formatDate value="${publishTimeValue}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${blog.updateTime>0}">
                            <jsp:useBean id="updateTimeValue" class="java.util.Date"/>
                            <jsp:setProperty name="updateTimeValue" property="time" value="${blog.updateTime}"/>
                            <fmt:formatDate value="${updateTimeValue}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </c:if>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/blogs/show/${blog.id}" type="button"
                           class="btn btn-sm btn-success">详情</a>
                        <a href="${pageContext.request.contextPath}/admin/blogs/update/${blog.id}" type="button"
                           class="btn btn-sm btn-warning">修改</a>
                        <a href="javascript:if(confirm('是否确认删除博文${blog.title}？'))location='${pageContext.request.contextPath}/admin/blogs/delete/${blog.id}'"
                           type="button"
                           class="btn btn-sm btn-danger">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>

    </c:if>

</div>

<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>

</body>
</html>
