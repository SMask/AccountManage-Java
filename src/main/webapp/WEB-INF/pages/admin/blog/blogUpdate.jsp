<%--
  Created by IntelliJ IDEA.
  User: server
  Date: 2018/11/5
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <title>博文更新</title>

        <meta http-equiv="refresh" content="5;url=<%=url%>">
    </c:if>

    <c:if test="${blog!=null}">
        <title>${blog.title} 更新</title>
    </c:if>

    <!-- public.css -->
    <link href="${pageContext.request.contextPath}/style/public.css" rel="stylesheet" type="text/css" charset="UTF-8">

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
        <h1>${blog.title} 更新</h1>
        <hr/>
        <form:form action="${pageContext.request.contextPath}/admin/blogs/updateP" method="post" modelAttribute="blog"
                   role="form">
            <!-- 把 id 一并写入 userP 中 -->
            <input type="hidden" id="id" name="id" value="${blog.id}">
            <div class="form-group">
                <span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span>
                <label for="title">Title:</label>
                <form:errors path="title" cssClass="error"/>
                <input required type="text" class="form-control" id="title" name="title" value="${blog.title}"
                       placeholder="Enter Title:">
            </div>
            <div class="form-group">
                <span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span>
                <label for="userByUserId.id">Author:</label>
                <form:errors path="userByUserId.id" cssClass="error"/>
                <select required class="form-control" id="userByUserId.id" name="userByUserId.id">
                        <%-- 显示默认值，value为空表示此选项为空，防止选择提交后台出错 --%>
                    <option value="">请选择</option>
                        <%-- 下面这种也可以，但是会导致默认没有显示 --%>
                        <%--<option style='display: none'></option>--%>
                    <c:forEach items="${userList}" var="user">
                        <option value="${user.id}" <c:if test="${user.id==blog.userByUserId.id}"> selected </c:if> >
                                ${user.id} - ${user.username}
                            - ${user.nickname} ${user.firstName} ${user.lastName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="content">Content:</label>
                <textarea class="form-control" id="content" name="content" rows="3"
                          placeholder="Please Input Content">${blog.content}</textarea>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-sm btn-success">提交</button>
            </div>
        </form:form>
    </c:if>
</div>

<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>

</body>
</html>
