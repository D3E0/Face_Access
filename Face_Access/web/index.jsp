<%--
  Created by IntelliJ IDEA.
  User: ACM-PC
  Date: 2018/5/20
  Time: 18:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
    <script src="<c:url value="/static/layui/layui.js"/>"></script>
    <title>导航界面</title>
    <style>
        .f {
            width: 500px;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }
    </style>
</head>
<body>
<div class="f">
    <fieldset class="layui-elem-field">
        <legend>导航页</legend>
        <div class="layui-field-box">
            <div class="layui-btn-container">
                <a href="<c:url value="/home"/>" class="layui-btn">主界面</a>
                <a href="<c:url value="/signIn"/>" class="layui-btn">登陆</a>
                <a href="<c:url value="/register"/>" class="layui-btn">注册</a>
                <a href="<c:url value="/logout"/>" class="layui-btn">注销</a>
                <a href="<c:url value="/error"/>" class="layui-btn">404</a>
                <a href="<c:url value="/denied"/>" class="layui-btn">请求拒绝</a>
                <a href="<c:url value="/signIn/test/add"/>" class="layui-btn">测试添加</a>
                <a href="<c:url value="/signIn/test/remove"/>" class="layui-btn">测试移除</a>
            </div>
        </div>
    </fieldset>

    <%--<fieldset class="layui-elem-field">--%>
    <%--<legend>JSON</legend>--%>
    <%--<div class="layui-field-box">--%>
    <%--<div class="layui-btn-container">--%>
    <%--<a href="<c:url value="/authorities"/>" class="layui-btn" target="_blank">Home</a>--%>
    <%--<a href="<c:url value="/user"/>" class="layui-btn" target="_blank">Add</a>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</fieldset>--%>
    <%--</div>--%>


</body>
</html>
