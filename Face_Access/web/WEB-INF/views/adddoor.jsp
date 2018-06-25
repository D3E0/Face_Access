<%--
  Created by IntelliJ IDEA.
  User: yan
  Date: 2018/6/6
  Time: 20:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserForm</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/normal.css">
</head>
<body>
<div class="father">
    <form class="layui-form layui-form-pane son" action="">
        <div class="layui-form-item">
            <label class="layui-form-label">门禁ID</label>
            <div class="layui-input-inline">
                <input type="text" id="houseid" class="layui-input" autocomplete="false">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">ip地址</label>
            <div class="layui-input-inline">
                <input type="text" id="ip" class="layui-input" autocomplete="false">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">具体位置</label>
            <div class="layui-input-inline">
                <input type="text" id="location" class="layui-input" autocomplete="false">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-inline">
                <select id="status" lay-verify="">
                    <option value="">请选择状态</option>
                    <option value="ok">正常</option>
                    <option value="not ok">维护</option>
                </select>
            </div>
        </div>

    </form>
</div>
</body>
<script>
    layui.use(['laydate', 'form', 'layer', 'jquery'], function () {
        var laydate = layui.laydate, form = layui.form
            , layer = layui.layer, $ = layui.jquery;
    });
</script>
</html>

