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
        .center {
            position: absolute;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
        }

        .vetically {
            position: absolute;
            top: 50%;
            transform: translate(0%, -50%);
            margin: auto;
        }

        .content {
            background-color: white;
            padding: 70px;
            border-radius: 2px;
            position: relative;
            height: 460px;
            width: 350px;
        }

        .boxShadow {
            box-shadow: 0 9px 30px -6px rgba(0, 0, 0, .2), 0 18px 20px -10px rgba(0, 0, 0, .04), 0 18px 20px -10px rgba(0, 0, 0, .04), 0 10px 20px -10px rgba(0, 0, 0, .04);
            border: 1px solid #dadada;
            border-radius: 10px;
        }

        body {
            background-color: #eee;
        }
    </style>
</head>

<body>
<div class="content boxShadow center">

    <div class="layui-form vetically">

        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-inline">
                <input type="text" name="username" value="${userEntity.userName}" autocomplete="off"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">联系方式</label>
            <div class="layui-input-inline">
                <input type="text" name="userTel" value="${userEntity.userTelephone}" autocomplete="off"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">旧密码</label>
            <div class="layui-inline">
                <input type="password" name="oldPassword" class="layui-input" id="oldPassword" value="">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">新密码</label>
            <div class="layui-inline">
                <input type="password" name="newPassword" class="layui-input" id="newPassword">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">确认新密码</label>
            <div class="layui-inline">
                <input type="password" name="confirmPassword" class="layui-input" id="confirmPassword">
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

<div id="div_preview" style=" display: none;">
    <img id="img_preview" style="width: 100%"/>
</div>

</body>
</html>
