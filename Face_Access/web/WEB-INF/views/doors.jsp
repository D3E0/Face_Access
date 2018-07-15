<%--
  Created by IntelliJ IDEA.
  User: yan
  Date: 2018/5/31
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>users</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/doors.js"></script>
    <script>
        var contextPath = '${pageContext.request.contextPath}';
    </script>
    <meta http-equiv="Pragma" content="no-cache">
    <style>
        .content {
            background-color: #eee;
            margin: 15px;
            border-radius: 2px;
            box-shadow: 0 1px 2px 0 rgba(0, 0, 0, .05);
        }

        body {
            background-color: #eee;
        }
        #search{
            float:right;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            display: inline-block;
            background: white;
            padding: 0;
        }
        #searchtxt{
            border: none;
            height: 38px;
        }
        #searchbtn{
            margin: auto;
        }
    </style>
</head>
<body class="layui-layout-body">
<div class="content">
    <button class="layui-btn" id="add">添加门禁</button>
    <div id="search" ><input type="text" id="searchtxt" size="25px" placeholder="根据门的位置搜索"><button id="searchbtn" class="layui-btn">搜索</button></div>
    <div id="doorTable" lay-filter="doorTable"></div>
</div>
<script type="text/html" id="toolBar">
    <a class="layui-btn layui-btn-sm" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="del">删除</a>
</script>

</body>
</html>