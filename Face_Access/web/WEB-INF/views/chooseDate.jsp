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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <style>
        .center {
            position: absolute;
            top: 50%;
            transform: translate(0%, -50%);
        }
    </style>
</head>
<body>
<div class="layui-form" style="margin: 30px 0px 0px 60px">

    <div class="layui-form-item" style="margin-left: 30px">
        <div id="test"></div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">失效日期</label>
        <div class="layui-inline">
            <input type="text" class="layui-input" id="endDate" value="${end}">
        </div>
    </div>
</div>
<script>
    layui.use(['laydate', 'form', 'layer', 'upload'], function () {
        var laydate = layui.laydate, $ = layui.jquery;

        laydate.render({
            elem: '#test'
            , min: "${start}"
            , value: "${end}"
            , isInitValue: true
            , position: 'static'
            , showBottom: false
            , done: function (value, date, endDate) {
                $("#endDate").val(value);
            }
        });
    });
</script>
</body>
</html>