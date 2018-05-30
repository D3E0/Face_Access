<%--
  Created by IntelliJ IDEA.
  User: ACM-PC
  Date: 2018/5/20
  Time: 18:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="./static/layui/css/layui.css">
    <script src="static/layui/layui.js"></script>
    <title>index</title>
</head>
<body>

<div class="layui-btn-container" style="width: 400px; margin: 100px auto">
    <a href="/home" class="layui-btn" target="_blank">Home</a>
    <a href="/users" class="layui-btn" target="_blank">Users</a>
    <a href="/adduser" class="layui-btn" target="_blank">Add</a>
    <a href="/user?id=1" class="layui-btn" target="_blank">UserProfile</a>
    <a href="/test" class="layui-btn" target="_blank">Test</a>
    <a href="/choosedate?start=2018-05-20&end=2018-05-28" class="layui-btn" target="_blank">Date</a>
</div>

<script>
    layui.use(['laydate', 'form', 'layer', 'upload'], function () {
        var laydate = layui.laydate, form = layui.form
            , layer = layui.layer, upload = layui.upload;
    });


</script>
</body>
</html>
