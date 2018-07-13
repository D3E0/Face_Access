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
        <div class="layui-logo">智慧人脸门禁系统</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-right" lay-filter="topnav">
            <li class="layui-nav-item">
                <a>
                    <img src="${pageContext.request.contextPath}/static/images/logo.jpg" class="layui-nav-img">
                    ${username}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="<c:url value="/user?id=${userId}"/>" target="frame">个人资料</a></dd>
                    <dd><a href="javascript:">密码修改</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item" id="quit"><a href="/logout">退下</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul id="sideNav" class="layui-nav layui-nav-tree" lay-filter="sidenav">
                <c:choose>
                    <c:when test="${type == 'USER'}">
                        <li id="user" class="layui-nav-item">
                            <a href="<c:url value="/access"/>" target="frame">可通行门列表</a>
                        </li>
                        <c:set var="tar" value="/access"/>
                    </c:when>
                    <c:when test="${type == 'OWNER'}">
                        <li id="houseOwner" class="layui-nav-item  layui-this">
                            <a href="<c:url value="/authorities"/>" target="frame">人员管理</a>
                        </li>
                        <li id="user" class="layui-nav-item">
                            <a href="<c:url value="/access"/>" target="frame">可通行门列表</a>
                        </li>
                        <c:set var="tar" value="/authorities"/>
                    </c:when>
                    <c:when test="${type == 'ADMIN'}">
                        <li class="layui-nav-item  layui-this">
                            <a href="<c:url value="/records"/>" target="frame">进出门记录查看</a>
                        </li>
                        <li class="layui-nav-item">
                            <a href="<c:url value="/doors"/>" target="frame">门禁管理</a>
                        </li>
                        <li class="layui-nav-item">
                            <a href="<c:url value="/houses"/>" target="frame">房间管理</a>
                        </li>
                        <c:set var="tar" value="/records"/>
                    </c:when>
                </c:choose>
            </ul>
        </div>
    </div>

    <div id="content">
        <iframe id="frame" name="frame" src="${tar}"></iframe>
    </div>

</div>
<div id="divPreview">
    <img id="updatePreview" style="width: 100%"/>
</div>
<script>
    var id = ${userId};
    layui.use(['jquery', 'layer', 'element'], function () {
        var $ = layui.$, layer = layui.layer, element = layui.element;

        $("#updatePreview").hide();
    })

</script>
</body>
</html>
