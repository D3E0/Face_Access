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
        <legend>JSP</legend>
        <div class="layui-field-box">
            <div class="layui-btn-container">
                <a href="/home" class="layui-btn" target="_blank">主界面</a>
                <a href="/users" class="layui-btn" target="_blank">人员管理</a>
                <a href="/adduser" class="layui-btn" target="_blank">添加用户</a>
                <a href="/user?id=1" class="layui-btn" target="_blank">用户资料</a>
                <a href="/test" class="layui-btn" target="_blank">Test</a>
                <a href="/choosedate?start=2018-05-20&end=2018-05-28" class="layui-btn" target="_blank">日期选择</a>
            </div>
        </div>
    </fieldset>

    <fieldset class="layui-elem-field">
        <legend>JSON</legend>
        <div class="layui-field-box">
            <div class="layui-btn-container">
                <a href="/user.json" class="layui-btn" target="_blank">Home</a>
                <a href="/getAllUserId" class="layui-btn" target="_blank">Users</a>
                <a href="/users.json" class="layui-btn" target="_blank">Add</a>
            </div>
        </div>
    </fieldset>
</div>


</body>
</html>
