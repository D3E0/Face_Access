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
<script rel="script" src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
<div class="panel-lite">
    <div class="layui-tab">
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <div class="thumbur">
                    <img src="${pageContext.request.contextPath}/static/images/logo.jpg" width="150" height="150">
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
                    </div>
                    <a href="#">忘记密码?</a>
                    <button class="floating-btn" type="submit"><i class="icon-arrow"></i></button>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
    //注意：选项卡 依赖 element 模块，否则无法进行功能性操作
    layui.use('element', function () {
        var element = layui.element;
        //…
    });
</script>
</body>
</html>