<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/my.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
</head>
<body>
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.11.1.js"></script>
<%--<script src="${pageContext.request.contextPath}/static/resource/jquery.webcam.min.js"></script>--%>
<script rel="script" src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
<div class="panel-lite">
    <div class="layui-tab">
        <ul class="layui-tab-title  tap">
            <li class="layui-this">登录</li>
            <li>人脸登录</li>
        </ul>
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <div class="thumbur">
                    <div class="icon-lock"></div>
                </div>
                <h4>用户登录</h4>
                <form action="" method="get">
                    <div class="form-group">
                        <input required="required" class="form-control"/>
                        <label class="form-label">用户名</label>
                    </div>
                    <div class="form-group">
                        <input type="password" required="required" class="form-control"/>
                        <label class="form-label">密　码</label>
                    </div><a href="#">忘记密码?</a>
                    <button class="floating-btn" type="submit"><i class="icon-arrow"></i></button>
                </form>
            </div>
            <div class="layui-tab-item" onclick="startvideo()">
                <video id="video" width="350" height="350" autoplay></video>
                <canvas id="canvas" hidden width="350" height="350"></canvas>
                <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/takepic.js"></script>
                <button class="layui-btn takepic" onclick="takepic()">点击触发</button>
            </div>
        </div>
    </div>
</div>
<script>
    //注意：选项卡 依赖 element 模块，否则无法进行功能性操作
    layui.use('element', function(){
        var element = layui.element;
        //…
    });
</script>
</body>
</html>