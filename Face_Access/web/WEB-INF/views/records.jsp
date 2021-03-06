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
    <script>
        var contextPath = '${pageContext.request.contextPath}';
    </script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/records.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.3.1.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/xlsx.full.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/FileSaver.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/outXls.js"></script>

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
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            display: inline-block;
            background: white;
            padding: 0;
            float: right;
        }
        #searchtxt{
            border: none;
            height: 38px;
        }
        #searchbtn{
            margin: auto;
        }
        #outxls{

        }
    </style>
</head>
<body class="layui-layout-body">
<div class="content">
    <button id="outxls" class="layui-btn" onclick="Go()">导出为表格</button>
    <div id="search" ><input type="text" id="searchtxt" size="25px"  placeholder="根据位置或用户名搜索"><button id="searchbtn" class="layui-btn">搜索</button></div>
    <div id="recordTable" lay-filter="recordTable"></div>
</div>
<script type="text/html" id="toolBar">
    <a class="layui-btn layui-btn-sm" lay-event="showpic">查看开门照片</a>
</script>

</body>
</html>