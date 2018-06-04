<%--
  Created by IntelliJ IDEA.
  User: ACM-PC
  Date: 2018/5/21
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>UserProfile</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/userProfile.js"></script>
    <style>
        body {
            background-color: #eee;
        }
    </style>
</head>

<body>
<div class="layui-fluid">

    <div class="layui-row layui-col-space15">
        <div class="layui-form vetically ">

            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-inline">
                    <input type="text" name="username" value="${userEntity.userName}" disabled
                           class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux">不可修改。一般用于后台登入名</div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">真实姓名</label>
                <div class="layui-input-inline">
                    <input type="text" name="realname" value="${userEntity.realName}" disabled
                           class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">手机号</label>
                <div class="layui-input-inline">
                    <input type="text" name="userTel" value="${userEntity.userTelephone}" autocomplete="off"
                           class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">个人图片</label>
                <button type="button" class="layui-btn" id="image" name="image">
                    <i class="layui-icon">&#xe67c;</i>上传图片
                </button>
            </div>

            <div class="layui-form-item" id="preview" hidden>
                <div class="layui-input-block">
                    <img id="demo1" style="width: 113px">
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-filter="*" id="submit">提交修改</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
