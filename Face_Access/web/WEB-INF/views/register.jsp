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
        </div>

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
                <div class="layui-input-inline " style="width: 150px;">
                    <input type="text" name="verifyCode" lay-verify="required"
                           placeholder="验证码" class="layui-input">
                </div>

                <div class="layui-input-inline" style="width: 50px">
                    <button type="button" id="getCode"
                            class="layui-btn layui-btn-primary layui-btn layui-btn-disabled">
                        获取验证码
                    </button>
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-block">
                <input type="password" name="password" lay-verify="required|password"
                       placeholder="请输入密码" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">确认密码</label>
            <div class="layui-input-block">
                <input type="password" name="confirm" lay-verify="required|password|check"
                       placeholder="确认密码" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <button class="layui-btn layui-btn-fluid" id="submit"
                    lay-submit lay-filter="submit">注册
            </button>
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
                    window.location.href = '/signIn';
                } else {
                    layer.msg("注册失败");
                }
            });
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });

        form.verify({
            username: function (value, item) { //value：表单的值、item：表单的DOM对象
                if (value.length < 6) {
                    return '用户名至少6位';
                } else if (!new RegExp("^[a-zA-Z0-9_]{6,16}$").test(value)) {
                    return '用户名含有非法字符';
                }
            },
            password: function (value, item) {
                if (value.length < 6) {
                    return '密码至少6位';
                }

            },
            check: function (value, item) {
                var data = $("input[name='password']").val();
                if (value !== data) {
                    return "两次密码不一致";
                }
            }
        });

        var SUCCESS = '#4caf50';
        var FAIL = '#d16d62';

        $("input[name='username']").blur(function () {
            var data = $(this).val();
            var elem = this;
            data.toLowerCase();

            if (data.length < 6) {
                layer.tips('用户名至少6位', elem, {
                    tips: [2, FAIL]
                });
            } else if (!new RegExp("^[a-zA-Z0-9_]{6,}$").test(data)) {
                layer.tips('用户名含有非法字符\n至少包含字母、数字或下划线', elem, {
                    tips: [2, FAIL]
                });
            } else {
                $.post('/verify', {username: data}, function (val) {
                    var dataObj = eval("(" + val + ")");
                    if (dataObj.result === 'success') {
                        layer.tips('验证通过', elem, {
                            tips: [2, SUCCESS]
                        });
                    } else {
                        layer.tips('用户名已存在', elem, {
                            tips: [2, FAIL]
                        });
                    }
                });
            }
        });

        $("input[name='telephone']").blur(function () {
            var data = $(this).val();
            var elem = this;
            var pattern = /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/;
            if (pattern.test(data)) {
                layer.tips('验证通过', elem, {
                    tips: [2, SUCCESS]
                });
            } else {
                layer.tips('请输入合法的手机号', elem, {
                    tips: [2, FAIL]
                });
                elem.focus();
            }
            $("#getCode").toggleClass("layui-btn-disabled", false);
        });

        $("#getCode").click(function () {
            var COUNT = 10;
            var timeCount = COUNT;
            var elem = $("#getCode");

            elem.toggleClass("layui-btn-disabled", true);

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
                timeCount = COUNT;
            }, COUNT * 1000);
        });
    })
</script>
</body>
</html>