<%--
  Created by IntelliJ IDEA.
  User: yan
  Date: 2018/6/3
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/normal.css">
</head>
<body>
<div class="father">
    <form class="layui-form layui-form-pane son" action="">
        <input type="text" id="houseid" hidden value="${houseid}">
        <div class="layui-form-item">
            <label class="layui-form-label">旧密码</label>
            <div class="layui-input-inline">
                <input type="password" id="oldPassword" class="layui-input" autocomplete="false">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">新密码</label>
            <div class="layui-input-inline">
                <input type="password" id="newPassword" class="layui-input" autocomplete="false">
            </div>
        </div>
    </form>
</div>
</body>
<script>
    layui.use(['form', 'layer', 'jquery'], function () {
        var laydate = layui.laydate, form = layui.form
            , layer = layui.layer, $ = layui.jquery;

    });
</script>
</html>
