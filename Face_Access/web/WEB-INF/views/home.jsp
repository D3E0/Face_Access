<%--
  Created by IntelliJ IDEA.
  User: ACM-PC
  Date: 2018/5/25
  Time: 13:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/home.js"></script>
    <%--<meta http-equiv="Pragma" content="no-cache">--%>
</head>

<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">Incredible System</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-right" lay-filter="topnav">
            <li class="layui-nav-item" id="profile">
                <a>
                    <img src="${pageContext.request.contextPath}/static/images/logo.jpg" class="layui-nav-img">
                    <%--D3E0--%>
                    ${userid}
                </a>
            </li>
            <li class="layui-nav-item" id="quit"><a>退下</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="sidenav">
                <li class="layui-nav-item  layui-this"><a lay-event="/users">人员管理</a></li>
                <li class="layui-nav-item"><a lay-event="/user?id=1">门禁管理</a></li>
                <li class="layui-nav-item"><a lay-event="room">房间管理</a></li>
                <li class="layui-nav-item"><a lay-event="room">可通行门列表</a></li>
                <li class="layui-nav-item"><a lay-event="record">进出门记录查看</a></li>
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:;">解决方案</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">列表一</a></dd>
                        <dd><a href="javascript:;">列表二</a></dd>
                        <dd><a href="">超链接</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>

</body>
</html>
