layui.use(['laydate', 'form', 'layer', 'upload'], function () {
    var laydate = layui.laydate, form = layui.form
        , layer = layui.layer, upload = layui.upload;
    var $ = layui.jquery;

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
                $('#img_preview').attr('src', result);
                $("#demo1").attr('src', result);
            });
        }
    });

    $("#demo1").click(function () {
        parent.layer.open({
            type: 1,
            title: false,
            closeBtn: 0,
            shadeClose: true,
            content: $('#div_preview')
        });
    });

    form.on('submit(updatePassword)', function (data) {
        $.post('/updatePassword', data.field, function (val) {
            var dataObj = eval("(" + val + ")");
            if (dataObj.result === 'success') {
                layer.msg("修改成功")
            } else {
                layer.msg("密码错误");
            }
        });
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

});
