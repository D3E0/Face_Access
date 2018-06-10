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
            }
        });
    });

    form.on('submit(updatePassword)', function (data) {
        $("input[name = 'oldPassword']").val('');
        $("input[name = 'confirm']").val('');
        $("input[name = 'password']").val('');
        if (data.field.confirm === data.field.password) {
            $.post('/updatePassword', data.field, function (val) {
                var dataObj = eval("(" + val + ")");
                if (dataObj.result === 'success') {
                    layer.msg("修改成功")
                } else {
                    layer.msg("密码错误");
                }
            });
        } else {
            layer.msg("两次密码不一致");
        }
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

    form.on('submit(updateTelephone)', function (data) {
        $.post('/updateTelephone', data.field, function (val) {
            var dataObj = eval("(" + val + ")");
            if (dataObj.result === 'success') {
                layer.msg("修改成功");
                $("input[name = 'userTel']").val(dataObj.telephone);
                $("input[name = 'verifyCode']").val('');
                $("input[name = 'telephone']").val('');
            } else {
                layer.msg("修改失败");
            }
        });
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

    form.verify({
        check: function (value, item) {
            var data = $("input[name='password']").val();
            if (value !== data) {
                return "两次密码不一致";
            }
        },
        password: function (value, item) {
            if (value.length < 6) {
                return "密码至少6位";
            }
        }

    });

    $("#getCode").click(function () {
        var COUNT = 10;
        var timeCount = COUNT;

        var elem = $("#getCode");
        // elem.attr('disabled', true);
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
            // elem.attr('disabled', false);
            elem.toggleClass("layui-btn-disabled", false);
            timeCount = COUNT;
        }, COUNT * 1000);
    });

});
