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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/normal.css">
</head>
<body>
<div class="father">
    <br>
    <input hidden value="${id}" id="id">
    <div class="layui-form-item">
        <label class="layui-form-label">门的位置</label>
        <div class="layui-input-inline">
            <input  type="text" id="location" class="layui-input" autocomplete="false" value="${location}">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">门的ip</label>
        <div class="layui-input-inline">
            <input  type="text" id="ip" class="layui-input" autocomplete="false" value="${ip}">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">门的状态</label>
        <div class="layui-input-inline">
            <input  type="text" id="status" class="layui-input" value="${status}">
        </div>
    </div>
</body>
</html>
