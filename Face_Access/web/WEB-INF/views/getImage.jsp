<%--
  Created by IntelliJ IDEA.
  User: ACM-PC
  Date: 2018/6/14
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <style>

        body {
            background-color: #eee;
            margin-top: 50px;
        }

    </style>
</head>
<body>
<div class="layui-card" style="width: 400px; margin: 0 auto">
    <div class="layui-card-header">个人图片</div>
    <div class="layui-card-body">
        <div class="layui-form ">
            <input type="text" name="userId" value=${userEntity.userId} hidden>

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
                    <button class="layui-btn" lay-filter="*" id="imgSubmit">提交修改</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    layui.use(['laydate', 'form', 'layer', 'upload'], function () {
        var laydate = layui.laydate, form = layui.form
            , layer = layui.layer, upload = layui.upload;
        var $ = layui.jquery;
        var topDoc = parent.document;

        upload.render({
            elem: '#image' //绑定元素
            , url: '/upload' //上传接口
            , done: function (res) {
                //上传完毕回调
                layer.msg("success");
            }
            , error: function () {
                //请求异常回调
                layer.msg("error");
            }
            , field: 'image'
            , accept: 'images'
            , acceptMime: 'image/*'
            , auto: false
            , bindAction: '#imgSubmit'
            , choose: function (obj) {
                obj.preview(function (index, file, result) {
                    $("#preview").show();
                    $('#updatePreview', topDoc).attr('src', result);
                    $("#demo1").attr('src', result);
                });
            }
        });

        $("#demo1").click(function () {
            // $('#updatePreview', topDoc).show();
            parent.layer.open({
                type: 1,
                title: false,
                closeBtn: 0,
                shadeClose: true,
                content: $('#updatePreview', topDoc).show(),
                end: function () {
                    $('#updatePreview', topDoc).hide();
                    console.info("end");
                }
            });
        });

    })


</script>
</body>
</html>
