<%--
  Created by IntelliJ IDEA.
  User: yan
  Date: 2018/6/3
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <style>

        body {
            background-color: #eee;
        }

        .father {
            background-color: white;
            padding: 20px;
            height: 400px;
            width: 350px;
            /*center*/
            position: absolute;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
            /*shadow*/
            box-shadow: 0 9px 30px -6px rgba(0, 0, 0, .2), 0 18px 20px -10px rgba(0, 0, 0, .04), 0 18px 20px -10px rgba(0, 0, 0, .04), 0 10px 20px -10px rgba(0, 0, 0, .04);
            border: 1px solid #dadada;
            border-radius: 10px;
        }

        .son {
            position: absolute;
            top: 50%;
            transform: translate(0%, -50%);
            margin-left: 20px;
        }
    </style>
</head>
<body>
<div class="father">
    <br>
    <input hidden value="${id}" id="id">
    <div class="layui-form-item">
        <label class="layui-form-label">门的位置</label>
        <div class="layui-input-inline">
            <input  type="text" id="location" class="layui-input" autocomplete="false" value="${location}">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">门的ip</label>
        <div class="layui-input-inline">
            <input  type="text" id="ip" class="layui-input" autocomplete="false" value="${ip}">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">门的状态</label>
        <div class="layui-input-inline">
            <input  type="text" id="status" class="layui-input" value="${status}">
        </div>
    </div>
</body>
</html>
