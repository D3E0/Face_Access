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
            margin-top: 50px;
        }

        .thumb {
            width: 100px;
            position: relative;;
            margin: auto;
            border-radius: 100%;
        }

    </style>

</head>
<body>
<div class="layui-card" style="width: 400px; margin: 0 auto">
    <div class="layui-card-header"></div>
    <div class="layui-card-body">
        <div class="thumb">
            <img class="thumb" src="/static/images/logo.jpg">
        </div>
        <div style="color: #009688; text-align: center; font-size: 24px; padding: 20px;">
            用户登录
        </div>

        <form id="signInByUsername" class="layui-form layui-form-pane">

            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-block">
                    <input type="text" name="username" class="layui-input" id="username"
                           lay-verify="required|username" placeholder="请输入用户名">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-block">
                    <input type="password" name="password" class="layui-input"
                           lay-verify="required" placeholder="请输入密码">
                </div>
            </div>

            <div class="layui-input-block">
                <a href="javascript:" id="toPhone" style="color: #009688;margin-left: 30px">手机号登陆</a>
                <a href="/register" style="color: #009688; margin-left: 60px">立即注册</a>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn " lay-submit lay-filter="signInByUsername"
                            style="width: 120px;">登陆
                    </button>
                    <button type="reset" class="layui-btn layui-btn-primary"
                            style="width: 120px;">重置
                    </button>
                </div>
            </div>
        </form>

        <form id="signInByTelephone" hidden class="layui-form layui-form-pane">
            <div class="layui-form-item">
                <label class="layui-form-label">手机号</label>
                <div class="layui-input-block">
                    <input type="text" name="telephone" lay-verify="required|phone"
                           placeholder="请输入手机号" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">验证码</label>
                    <div class="layui-input-inline " style="width: 140px;">
                        <input type="text" name="verifyCode" lay-verify="required"
                               placeholder="验证码" class="layui-input">
                    </div>

                    <div class="layui-input-inline" style="width: 50px">
                        <button type="button" id="getCode"
                                class="layui-btn layui-btn-primary">
                            获取验证码
                        </button>
                    </div>
                </div>
            </div>

            <div class="layui-input-block">
                <a href="javascript:" id="toUsername" style="color: #009688">账号密码登陆</a>
                <a href="/register" style="color: #009688; margin-left: 20px">立即注册</a>
            </div>

            <div class="layui-form-item">
                <button class="layui-btn layui-btn-fluid"
                        lay-submit lay-filter="signInByTelephone">登陆
                </button>
            </div>
        </form>

    </div>
</div>
<script>
    layui.use(['form', 'laydate', 'jquery', 'layer'], function () {
        var form = layui.form, $ = layui.jquery, layer = layui.layer;
        form.on('submit(signInByUsername)', function (data) {
            $.post('/processSignIn', data.field, function (val) {
                var dataObj = eval("(" + val + ")");
                if (dataObj.result === 'success') {
                    layer.msg("登陆成功");
                    window.location.href = '/home';
                } else {
                    layer.msg("密码错误");
                }
            });
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });

        form.on('submit(signInByTelephone)', function (data) {
            $.post('/processSignInByTelephone', data.field, function (val) {
                var dataObj = eval("(" + val + ")");
                if (dataObj.result === 'success') {
                    layer.msg("登陆成功");
                    window.location.href = '/home';
                } else {
                    layer.msg("验证码错误");
                }
            });
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });

        form.verify({
            username: function (value, item) { //value：表单的值，item：表单的DOM对象
                // if (!new RegExp("^[a-zA-Z0-9_]{6,16}$").test(value)) {
                //     return '用户名至少6位，包括字母、数字、下划线';
                // }
            },
            password: function (value, item) {
                // if (value.length < 6) {
                //     return "密码至少6位";
                // }
            }
        });

        $("#toPhone").click(function () {
            $("#signInByTelephone").show();
            $("#signInByUsername").hide();
        });

        $("#toUsername").click(function () {
            $("#signInByTelephone").hide();
            $("#signInByUsername").show();
        });

        $("#getCode").click(function () {
            var telephone = $("input[name='telephone']");
            var pattern = /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/;
            if (!pattern.test(telephone.val())) {
                layer.tips('请输入合法的手机号', telephone, {
                    tips: [2, '#d16d62']
                });
            } else {
                var COUNT = 10;
                var timeCount = COUNT;
                var elem = $("#getCode");

                elem.toggleClass("layui-btn-disabled", true);
                elem.attr("disabled", true);
                $.post('/getDigitVerifyCode', {}, function (data) {
                    console.info(data);
                });

                //倒计时开始
                var id = setInterval(function () {
                    elem.text(timeCount + "s 后重发");
                    timeCount--;
                }, 1000);

                //倒计时结束 可以点击
                setTimeout(function () {
                    clearInterval(id);
                    elem.text("获取验证码");
                    elem.toggleClass("layui-btn-disabled", false);
                    elem.attr("disabled", false);
                    timeCount = COUNT;
                }, COUNT * 1000);

            }
        });
    })
</script>
</body>
</html>