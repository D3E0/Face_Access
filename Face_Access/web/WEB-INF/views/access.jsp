<%--
  Created by IntelliJ IDEA.
  User: ACM-PC
  Date: 2018/5/20
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>users</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/users.js"></script>
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
                <div class="layui-card-header">可通行门列表</div>
                <div class="layui-card-body">
                    <div class="layui-input-inline">
                        <input class="layui-input" id="searchVal">
                    </div>
                    <button class="layui-btn" id="search">
                        <i class="layui-icon">&#xe615;</i> 搜索
                    </button>
                    <div id="accessTable" lay-filter="accessTable"></div>
                </div>
            </div>
        </div>
    </div>
</div>


<script>

    layui.use(['jquery', 'laypage', 'table', 'layer', 'element', 'laydate'], function () {
        var $ = layui.$, laypage = layui.laypage
            , table = layui.table, layer = layui.layer
            , element = layui.element, laydate = layui.laydate;

        parent.register.accessTable = table.render({
            elem: '#accessTable'
            , url: '/access.json'
            , page: true
            , cols: [[
                {field: 'houseId', title: '房间 ID', align: "center"}
                , {field: 'userName', title: '业主', align: "center"}
                , {field: 'startDate', title: '授权日期', align: "center"}
                , {field: 'endDate', title: '失效日期', align: "center"}
            ]]
            , id: "accessTable"
        });

        $("#search").click(function () {
            var data = $("#searchVal").val();
            console.info(data);
            parent.register.accessTable.reload({
                where: { //设定异步数据接口的额外参数，任意设
                    data: data
                }
                , page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
        })

    });
</script>
</body>
</html>

