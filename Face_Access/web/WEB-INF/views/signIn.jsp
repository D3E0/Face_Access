<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Login</title>
    <link rel="stylesheet" href="<c:url value="/static/layui/css/layui.css"/>">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/signIn.css">
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.3.1.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/jquery.transit.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/capture.js"></script>
    <script>
        var contextPath = '${pageContext.request.contextPath}';
    </script>
</head>
<body>
<div class="layui-container" style="margin-top: 50p; height: 500px;">
    <div class="layui-row layui-col-space10">
        <div class="layui-col-md7">
            <div id="box">
                <img src="<c:url value="/static/images/door.png"/>" id="img">
            </div>
        </div>
        <div class="layui-col-md5">
            <div class="scene">
                <div class="cube">
                    <div class="side front" id="Front">
                        <div class="thumb">
                            <img class="thumb" src="${pageContext.request.contextPath}/static/images/logo.jpg">
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

                            <div class="layui-form-item">
                                <div class="layui-inline">
                                    <label class="layui-form-label">验证码</label>
                                    <div class="layui-input-inline " style="width: 160px;">
                                        <input type="text" name="imageCode" lay-verify="required"
                                               placeholder="验证码" class="layui-input">
                                    </div>

                                    <div class="layui-input-inline" style="width: 100px">
                                        <img src="<c:url value="/signIn/imageCode"/>">
                                    </div>
                                </div>
                            </div>

                            <div class="navi" style="margin-top: -10px; padding-bottom: 15px; margin-left: 90px">
                                <a href="javascript:myTransition(1, 500)">手机号登陆</a>
                                <a href="javascript:myTransition(-1, 500)">人脸登陆</a>
                                <a href="<c:url value="/register"/>">立即注册</a>
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
                    </div>
                    <div class="side back" id="Back"></div>

                    <div class="side top" id="Top" style="padding: 0">
                        <video id="video" width=100% height="300"></video>
                        <div class="navi">
                            <a href="javascript:myTransition(1, 300);myTransition(1, 300)">手机号登陆</a>
                            <a href="javascript:myTransition(1, 500)">账号密码登陆</a>
                            <a href="<c:url value="/register"/>">立即注册</a>
                        </div>
                        <button id="start" class="layui-btn layui-btn-fluid ">开始登陆</button>
                    </div>

                    <div class="side bottom" id="Bottom">
                        <div class="thumb">
                            <img class="thumb" src="<c:url value="/static/images/logo.jpg"/>">
                        </div>
                        <div style="color: #009688; text-align: center; font-size: 24px; padding: 20px;">
                            用户登录
                        </div>
                        <form id="signInByTelephone" class="layui-form layui-form-pane">
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
                                    <div class="layui-input-inline " style="width: 170px;">
                                        <input type="text" name="digitCode" lay-verify="required"
                                               placeholder="验证码" class="layui-input">
                                    </div>

                                    <div class="layui-input-inline" style="width: 70px">
                                        <button type="button" id="getCode"
                                                class="layui-btn layui-btn-primary">
                                            获取验证码
                                        </button>
                                        <%--<img src="<c:url value="/signIn/imageCode"/>">--%>
                                    </div>
                                </div>
                            </div>

                            <div class="navi" style="margin-top: -10px; padding-bottom: 15px">
                                <a href="javascript:myTransition(-1, 300);myTransition(-1, 300)">人脸登陆</a>
                                <a href="javascript:myTransition(-1, 500)">账号密码登陆</a>
                                <a href="<c:url value="/register"/>">立即注册</a>
                            </div>

                            <div class="layui-form-item">
                                <button class="layui-btn layui-btn-fluid"
                                        lay-submit lay-filter="signInByTelephone">登陆
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<canvas id="canvas" hidden width="400" height="360"></canvas>
<script>
    var contextPath = '${pageContext.request.contextPath}';
    layui.use(['form', 'laydate', 'jquery', 'layer'], function () {
        var form = layui.form, $ = layui.jquery, layer = layui.layer;
        form.on('submit(signInByUsername)', function (data) {
            // $.post('/processSignIn', data.field, function (val) {
            $.post(contextPath + '/signIn/process/username', data.field, function (val) {
                var dataObj = eval("(" + val + ")");
                console.info(dataObj);
                if (dataObj.result === 'success') {
                    layer.msg("登陆成功");
                    window.location.href = contextPath + dataObj.url;
                } else {
                    layer.msg("密码错误");
                }
            });
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });

        form.on('submit(signInByTelephone)', function (data) {
            $.post(contextPath + '/signIn/process/telephone', data.field, function (val) {
                var dataObj = eval("(" + val + ")");
                if (dataObj.result === 'success') {
                    layer.msg("登陆成功");
                    window.location.href = contextPath + '/home';
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

        $("#getCode").click(function () {
            var telephone = $("input[name='telephone']");
            var pattern = /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/;
            if (!pattern.test(telephone.val())) {
                layer.tips('请输入合法的手机号', telephone, {
                    tips: [2, '#d16d62']
                });
                telephone.focus();
            } else {
                var COUNT = 10;
                var timeCount = COUNT;
                var elem = $("#getCode");

                elem.toggleClass("layui-btn-disabled", true);
                elem.attr("disabled", true);
                $.post(contextPath + '/register/digitCode', {}, function (data) {
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
    });
    var $bottom = $("#Bottom");
    var $front = $("#Front");
    var $top = $("#Top");
    var $back = $("#Back");
    var myStatus = {top: 90, front: 0, bottom: -90, back: 0};

    /**
     *
     * @param direction  动画方向，向上转 1；向下转 -1
     * @param speed 动画速度
     */
    function myTransition(direction, speed) {

        for (i in myStatus) {
            myStatus[i] += 90 * direction;
        }

        $bottom.transition({
            transform: "perspective(1200px) rotateX(" + myStatus.bottom + "deg)"
        }, speed, 'linear');

        $front.transition({
            transform: "perspective(1200px) rotateX(" + myStatus.front + "deg)"
        }, speed, 'linear');

        $top.transition({
            transform: "perspective(1200px) rotateX(" + myStatus.top + "deg)"
        }, speed, 'linear');

        $back.transition({
            transform: "perspective(1200px) rotateX(" + myStatus.back + "deg) rotateY(180deg) rotateZ(180deg) "
        }, speed, 'linear');
    }
</script>
</body>
</html>