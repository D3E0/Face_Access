<%--
  Created by IntelliJ IDEA.
  User: ACM-PC
  Date: 2018/5/25
  Time: 13:10
  To change this template use File | Settings | File Templates.
--%>
<%--TODO 从 Session 中获取 ID--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <script>var register = {};</script>
    <style>
        #frame {
            overflow: visible;
            border: 0;
            width: 100%;
            height: 100%;
        }

        #content {
            position: absolute;
            left: 200px;
            right: 0;
            top: 60px;
            bottom: 0;
            z-index: 998;
            width: auto;
            overflow: hidden;
            overflow-y: auto;
            box-sizing: border-box
        }
    </style>
</head>

<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">Incredible System</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-right" lay-filter="topnav">
            <li class="layui-nav-item">
                <a>
                    <img src="${pageContext.request.contextPath}/static/images/logo.jpg" class="layui-nav-img">
                    <%--D3E0--%>
                    ${username}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="/user?id=1" target="frame">基本资料</a></dd>
                    <dd><a href="javascript:">密码修改</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item" id="quit"><a href="/quit">退下</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="sidenav">
                <li class="layui-nav-item  layui-this"><a href="/users" target="frame">人员管理</a></li>
                <li class="layui-nav-item"><a href="/user?id=${userid}" target="frame">门禁管理</a></li>
                <li class="layui-nav-item"><a href="/user?id=${userid+1}" target="frame">房间管理</a></li>
                <li class="layui-nav-item"><a href="/user?id=${userid+2}" target="frame">可通行门列表</a></li>
                <li class="layui-nav-item"><a href="/user?id=${userid+3}" target="frame">进出门记录查看</a></li>
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:">解决方案</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:">列表一</a></dd>
                        <dd><a href="javascript:">列表二</a></dd>
                        <dd><a href="">超链接</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div id="content">
        <iframe id="frame" name="frame" src="/users"></iframe>
    </div>
    <%--<div class="layui-body">--%>
    <%--<!-- 内容主体区域 -->--%>
    <%--<iframe id="frame" name="frame" src="/users"></iframe>--%>
    <%--</div>--%>

    <%--<div class="layui-footer">--%>
    <%--<!-- 底部固定区域 -->--%>
    <%--© layui.com - 底部固定区域--%>
    <%--</div>--%>
</div>
<script>
    var id = ${userid};

    layui.use(['jquery', 'layer', 'element'], function () {
        var $ = layui.$, layer = layui.layer, element = layui.element;
    })
</script>
</body>
</html>
