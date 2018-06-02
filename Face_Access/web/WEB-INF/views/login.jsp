<%--
  Created by IntelliJ IDEA.
  User: ACM-PC
  Date: 2018/5/31
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
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
    <form class="layui-form layui-form-pane son">

        <div class="layui-form-item">
            <label class="layui-form-label" for="userid">账号</label>
            <div class="layui-input-inline">
                <input type="text" name="userid" id="userid" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label" for="password">密码</label>
            <div class="layui-input-inline">
                <input type="password" name="password" id="password" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="submit">登陆</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>

    </form>
</div>
<script>
    layui.use(['form', 'laydate', 'jquery', 'layer'], function () {
        var form = layui.form, $ = layui.jquery, layer = layui.layer;
        form.on('submit(submit)', function (data) {
            $.post('/processlogin', data.field, function (val) {
                var dataObj = eval("(" + val + ")");
                if (dataObj.result === 'success') {
                    window.location.href = '/home';
                } else {
                    layer.msg("密码错误");
                }
            });
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });
    })
</script>
</body>
</html>
