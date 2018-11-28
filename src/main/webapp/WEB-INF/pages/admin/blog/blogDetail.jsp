<%--
  Created by IntelliJ IDEA.
  User: server
  Date: 2018/11/5
  Time: 19:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;

    String url = path + "/admin/blogs";
%>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

    <c:if test="${blog==null}">
        <title>博文详情</title>

        <meta http-equiv="refresh" content="5;url=<%=url%>">
    </c:if>

    <c:if test="${blog!=null}">
        <title>${blog.title} 详情</title>
    </c:if>

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
    <c:if test="${blog==null}">
        <h1 style="color: red">Error!</h1>
        <h1>该博文不存在</h1>

        <h2 style=color:red><span id=jump>5</span> 秒钟后页面将自动返回...</h2>
        <h2><a href="javascript:void(0);" onclick="jumpToIndex()">点击此处立即跳转</a></h2>

        <script>
            function countDown(secs) {
                jump.innerText = secs;
                if (--secs > 0)
                    setTimeout("countDown(" + secs + " )", 1000);
            }

            countDown(5);

            function jumpToIndex() {
                window.location.href = '<%=url %>';
            }
        </script>
    </c:if>

    <c:if test="${blog!=null}">
        <h1>${blog.title} 详情</h1>
        <hr/>

        <table class="table table-bordered table-striped" style="table-layout: fixed">
            <tr>
                <th style="width: 15%">ID</th>
                <td style="width: 85%">${blog.id}</td>
            </tr>
            <tr>
                <th>Title</th>
                <td>${blog.title}</td>
            </tr>
            <tr>
                <th>Author</th>
                <td>${blog.userByUserId.id} - ${blog.userByUserId.username}
                    - ${blog.userByUserId.nickname} ${blog.userByUserId.firstName} ${blog.userByUserId.lastName}</td>
            </tr>
            <tr>
                <th>Publish Time</th>
                <td>
                    <c:if test="${blog.publishTime>0}">
                        <jsp:useBean id="publishTimeValue" class="java.util.Date"/>
                        <jsp:setProperty name="publishTimeValue" property="time" value="${blog.publishTime}"/>
                        <fmt:formatDate value="${publishTimeValue}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </c:if>
                </td>
            </tr>
            <tr>
                <th>Update Time</th>
                <td>
                    <c:if test="${blog.updateTime>0}">
                        <jsp:useBean id="updateTimeValue" class="java.util.Date"/>
                        <jsp:setProperty name="updateTimeValue" property="time" value="${blog.updateTime}"/>
                        <fmt:formatDate value="${updateTimeValue}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </c:if>
                </td>
            </tr>
            <tr>
                <th>Content</th>
                <td>${blog.content}</td>
            </tr>
        </table>
    </c:if>
</div>

<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>

</body>
</html>
