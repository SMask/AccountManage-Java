<%--
  Created by IntelliJ IDEA.
  User: server
  Date: 2018/10/30
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

    <c:if test="${user==null}">
        <title>用户更新</title>
    </c:if>

    <c:if test="${user!=null}">
        <title>${user.nickname} 更新</title>
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
    <c:if test="${user==null}">
        <h1 style="color: red">Error!</h1>
        <h1>该用户不存在</h1>
    </c:if>

    <c:if test="${user!=null}">
        <h1>${user.nickname} 更新</h1>
        <hr/>
        <form:form action="${pageContext.request.contextPath}/admin/users/updateP" method="post" modelAttribute="user"
                   role="form" onsubmit="return formCheck()">
            <!-- 把 id 一并写入 userP 中 -->
            <input type="hidden" id="id" name="id" value="${user.id}">
            <div class="form-group">
                <span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span>
                <label for="username">Username:(字母、数字和下划线组成，6至12位之间)</label>
                <form:errors path="username" cssClass="error"/>
                <input required type="text" class="form-control" id="username" name="username" value="${user.username}"
                       placeholder="Enter Username:">
            </div>
            <div class="form-group">
                <span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span>
                <label for="password">Password:(6至12位之间)</label>
                <form:errors path="password" cssClass="error"/>
                <input required type="password" class="form-control" id="password" name="password"
                       value="${user.password}"
                       placeholder="Enter Password:">
            </div>
            <div class="form-group">
                <span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span>
                <label for="passwordAgain">Password again:</label>
                <form:errors path="passwordAgain" cssClass="error"/>
                <input required type="password" class="form-control" id="passwordAgain" name="passwordAgain"
                       value="${user.passwordAgain}" placeholder="Enter Password again:">
            </div>
            <div class="form-group">
                <span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span>
                <label for="nickname">Nickname:</label>
                <form:errors path="nickname" cssClass="error"/>
                <input required type="text" class="form-control" id="nickname" name="nickname" value="${user.nickname}"
                       placeholder="Enter Nickname:">
            </div>
            <div class="form-group">
                <label for="firstName">FirstName:</label>
                <input type="text" class="form-control" id="firstName" name="firstName" value="${user.firstName}"
                       placeholder="Enter FirstName:">
            </div>
            <div class="form-group">
                <label for="lastName">LastName:</label>
                <input type="text" class="form-control" id="lastName" name="lastName" value="${user.lastName}"
                       placeholder="Enter LastName:">
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-sm btn-success">提交</button>
            </div>
        </form:form>
    </c:if>
</div>

<!-- 表单检查 -->
<script type="text/javascript">
    function formCheck() {
        var password = document.getElementById("password").value;
        var passwordAgain = document.getElementById("passwordAgain").value;
        if (password === passwordAgain) {
            return true;
        } else {
            alert("两次输入的密码不一致！");
            return false;
        }
    }
</script>

<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>

</body>
</html>
