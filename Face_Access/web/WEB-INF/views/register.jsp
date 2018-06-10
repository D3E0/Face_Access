<%--
  Created by IntelliJ IDEA.
  User: ACM-PC
  Date: 2018/5/31
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <style>
        body {
            background-color: #eee;
            margin-top: 50px;
        }

        #main {
            width: 375px;
            margin: 0 auto;
        }
    </style>

</head>
<body>
<div id="main">
    <div class="layui-form layui-form-pane">

        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-block">
                <input type="text" name="username" lay-verify="required|username" class="layui-input"
                       placeholder="请输入用户名">
            </div>
            <div class="layui-form-mid layui-word-aux" id="nameTip">用户名至少6位，包括字母、数字、下划线</div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">手机号</label>
            <div class="layui-input-block">
                <input type="text" name="telephone" lay-verify="required|phone"
                       placeholder="请输入手机号" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux" id="telephoneTip"></div>
        </div>


        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">验证码</label>
                <div class="layui-input-inline " style="width: 150px;">
                    <input type="text" name="verifyCode" lay-verify="required"
                           placeholder="验证码" class="layui-input">
                </div>

                <div class="layui-input-inline" style="width: 50px">
                    <button type="button" class="layui-btn layui-btn-primary">
                        获取验证码
                    </button>
                </div>
            </div>
            <div class="layui-form-mid layui-word-aux" id="verifyCodeTip"></div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-block">
                <input type="text" name="password" lay-verify="required|password"
                       placeholder="请输入密码" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux" id="passwordTip"></div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">确认密码</label>
            <div class="layui-input-block">
                <input type="password" name="confirm" lay-verify="required|password"
                       placeholder="确认密码" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux" id="confirmTip"></div>
        </div>

        <div class="layui-form-item">
            <button class="layui-btn  layui-btn-fluid" lay-submit lay-filter="submit">注册</button>
        </div>
    </div>
</div>


<script>
    layui.use(['form', 'laydate', 'jquery', 'layer'], function () {
        var form = layui.form, $ = layui.jquery, layer = layui.layer;
        form.on('submit(submit)', function (data) {
            console.info(data.field);
            $.post('/processRegister', data.field, function (val) {
                var dataObj = eval("(" + val + ")");
                console.info(dataObj);
                if (dataObj.result === 'success') {
                    layer.msg("注册成功");
                    window.location.href = '/login';
                } else {
                    layer.msg("注册失败");
                }
            });
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });

        form.verify({
            username: function (value, item) { //value：表单的值、item：表单的DOM对象
                if (!new RegExp("^[a-zA-Z0-9_]{6,16}$").test(value)) {
                    return '用户名至少6位，包括字母、数字、下划线';
                }
            },
            password: function (value, item) {

            }
        });

        $("input[name='username']").blur(function () {
            var data = $(this).val();
            console.info(data);
            if (!new RegExp("^[a-zA-Z0-9_]{6,16}$").test(data)) {
                // return '用户名至少6位，包括字母、数字、下划线';
                layer.tips('只想提示地精准些', $("input[name='username']"), {
                    tips: [2, '#3595CC']
                });
                // $("#nameTip").text('用户名至少6位，包括字母、数字、下划线');
            } else {
                $.post('/verify', {username: data}, function (data) {
                    if (data.result === '1') {
                        $("#nameTip").text('验证通过');
                    } else {
                        $("#nameTip").text('用户名已存在');
                    }
                });
            }

        })
    })
</script>
</body>
</html>
