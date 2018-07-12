layui.use(['laydate', 'form', 'layer', 'upload'], function () {
    var laydate = layui.laydate, form = layui.form
        , layer = layui.layer, upload = layui.upload;
    var $ = layui.jquery;
    var topDoc = parent.document;
    upload.render({
        elem: '#image' //绑定元素
        , url: '/user/upload' //上传接口
        , data: {userId: parent.id}
        , done: function (res) {
            //上传完毕回调
            if (res.msg === "success") {
                layer.msg("修改成功");
            } else {
                layer.msg("似乎失败了呢，再试一次吧", {icon: 5});
            }
        }
        , error: function () {
            //请求异常回调
            layer.msg("请求异常回调，再试一次吧", {icon: 5});
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
            console.info(parent.id);
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

    form.on('submit(updatePassword)', function (data) {
        $("input[name = 'oldPassword']").val('');
        $("input[name = 'confirm']").val('');
        $("input[name = 'password']").val('');
        if (data.field.confirm === data.field.password) {
            $.post('/user/update/password', data.field, function (val) {
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
        $.post('/user/update/telephone', data.field, function (val) {
            var dataObj = eval("(" + val + ")");
            if (dataObj.result === 'success') {
                layer.msg("修改成功");
                $("input[name = 'userTel']").val(dataObj.telephone);
                $("input[name = 'digitCode']").val('');
                $("input[name = 'telephone']").val('');
            } else {
                layer.msg("修改失败");
            }
        });
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

    $("#getCode").click(function () {
        var COUNT = 10;
        var timeCount = COUNT;

        var elem = $("#getCode");
        elem.attr('disabled', true);
        elem.toggleClass("layui-btn-disabled", true);

        $.post('/register/digitCode', {}, function (data) {
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
            elem.attr('disabled', false);
            elem.toggleClass("layui-btn-disabled", false);
            timeCount = COUNT;
        }, COUNT * 1000);
    });

});
