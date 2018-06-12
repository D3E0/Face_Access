<%--
  Created by IntelliJ IDEA.
  User: yan
  Date: 2018/6/3
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/normal.css">
</head>
<body>
<div class="father">
    <form class="layui-form layui-form-pane son" action="">
        <input type="text" id="id" hidden value="${id}">
        <div class="layui-form-item">
            <label class="layui-form-label">ip地址</label>
            <div class="layui-input-inline">
                <input type="text" id="ip" class="layui-input" value="${ip}" autocomplete="false">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">具体位置</label>
            <div class="layui-input-inline">
                <input type="text" id="location" class="layui-input" value="${location}" >
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-inline" >
                <select id="status" lay-verify="">
                    <option value="">请选择状态</option>
                    <option value="ok">正常</option>
                    <option value="not ok">维护</option>
                </select>
            </div>
        </div>

    </form>
</div>
</body>
<script>
    layui.use(['form', 'layer', 'jquery'], function () {
        var laydate = layui.laydate, form = layui.form
            , layer = layui.layer, $ = layui.jquery;
        $(document).ready(function () {
            var location="${status}";
            var options=$("#status").find("option");
            for (var j = 1; j < options.length; j++) {
                if ($(options[j]).val()== location) {
                    $(options[j]).attr("selected","selected");
                }
            }
            form.render();
        });

    });
</script>
</html>
