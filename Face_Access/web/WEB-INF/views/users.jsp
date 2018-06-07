<%--
  Created by IntelliJ IDEA.
  User: ACM-PC
  Date: 2018/5/20
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>

<%--TODO 分页功能--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>users</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/users.js"></script>
    <script>
        var win = {};
    </script>
    <meta http-equiv="Pragma" content="no-cache">
    <style>
        body {
            background-color: #eee;
            margin-top: 15px;
        }
    </style>
</head>
<body>
<div class="layui-fluid">

    <div class="layui-row layui-col-space15">

        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">人员管理</div>
                <div class="layui-card-body">
                    <button class="layui-btn" id="add">添加人员</button>
                    <div id="userTable" lay-filter="userTable"></div>
                </div>
            </div>
        </div>
    </div>
</div>


<script type="text/html" id="toolBar">
    <a class="layui-btn layui-btn-sm" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-sm" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="del">删除</a>
</script>


</body>
</html>

