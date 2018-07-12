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
    <c:set var="data2" value="gacl"/>
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul id="sideNav" class="layui-nav layui-nav-tree" lay-filter="sidenav">

                <c:if test="${data2 == 'gacl'}">
                    <li id="user" class="layui-nav-item">
                        <a href="/access" hidden target="frame">这个不应该显示的</a>
                    </li>
                </c:if>

                <c:if test="true">
                    <li id="user" class="layui-nav-item">
                        <a href="/access" hidden target="frame">这个应该显示的</a>
                    </li>
                </c:if>

                <c:if test="false">
                    <li id="user" class="layui-nav-item">
                        <a href="/access" hidden target="frame">这个不应该显示的</a>
                    </li>
                </c:if>

                <li id="user" class="layui-nav-item">
                    <a href="/access" hidden target="frame">可通行门列表</a>
                </li>

                <li id="houseOwner" class="layui-nav-item  layui-this">
                    <a href="/users" target="frame">人员管理</a>
                </li>

                <li class="layui-nav-item"><a href="/doors" target="frame">门禁管理</a></li>
                <li class="layui-nav-item"><a href="/houses" target="frame">房间管理</a></li>
                <li class="layui-nav-item"><a href="/records" target="frame">进出门记录查看</a></li>
            </ul>
        </div>
    </div>

    <div id="content">
        <iframe id="frame" name="frame" src=""></iframe>
    </div>

</div>
<script>
    console.info('type is ' + type);
    layui.use(['jquery', 'layer', 'element'], function () {
        var $ = layui.$, layer = layui.layer, element = layui.element;
    })

</script>
</body>
</html>

<%--<li class="layui-nav-item layui-nav-itemed">--%>
<%--<a href="javascript:">Yu</a>--%>
<%--<dl class="layui-nav-child ">--%>
<%--<dd class="layui-this"><a href="/users" target="frame">人员管理</a></dd>--%>
<%--<dd><a href="/access" target="frame">可通行门列表</a></dd>--%>
<%--</dl>--%>
<%--</li>--%>

<%--<li class="layui-nav-item layui-nav-itemed">--%>
<%--<a href="javascript:">Yan</a>--%>
<%--<dl class="layui-nav-child">--%>
<%--<dd><a href="/doors" target="frame">门禁管理</a></dd>--%>
<%--<dd><a href="/houses" target="frame">房间管理</a></dd>--%>
<%--<dd><a href="/records" target="frame">进出门记录查看</a></dd>--%>
<%--</dl>--%>
<%--</li>--%>
