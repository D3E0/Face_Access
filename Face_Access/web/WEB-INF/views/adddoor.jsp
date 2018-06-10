<%--
  Created by IntelliJ IDEA.
  User: yan
  Date: 2018/6/6
  Time: 20:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserForm</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/adduser.js"></script>
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
    <form class="layui-form layui-form-pane son" action="/adddoor">

        <div class="layui-form-item">
            <label class="layui-form-label">ip地址</label>
            <div class="layui-input-inline">
                <input type="text" name="ip" class="layui-input" autocomplete="false">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">具体位置</label>
            <div class="layui-input-inline">
                <input type="text" name="location" class="layui-input" autocomplete="false">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-inline">
                <select name="status" lay-verify="">
                    <option value="">请选择状态</option>
                    <option value="ok">正常</option>
                    <option value="not ok">维护</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="*">立即提交</button>
            </div>
        </div>

    </form>
</div>
</body>
</html>

