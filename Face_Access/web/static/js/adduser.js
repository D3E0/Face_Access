layui.use(['laydate', 'form', 'layer', 'jquery'], function () {
    var laydate = layui.laydate, form = layui.form
        , layer = layui.layer, $ = layui.jquery;

    laydate.render({
        elem: '#startDate'
        , type: 'date'
        , value: (new Date())
        , isInitValue: true
    });

    laydate.render({
        elem: '#endDate'
        , type: 'date'
        , min: 0
        , value: "2018-05-30"
        , isInitValue: true
    });

    //字符串加密 赵** 131****7788
    form.on('select(userid)', function (data) {
        $.getJSON('/user.json', {id: data.value}, function (val) {
            var username = val.username;
            var usertel = val.usertel;
            $("input[name='username']").val(val.username);
            $("input[name='usertel']").val(val.usertel);
        });
    });

    form.on('submit(submit)', function (data) {
        $.post('/processAddUser', data.field, function (data) {
            console.info(data);
            if (data.result === 'success') {
                var index = parent.win.addFrmaeIndex;
                parent.layer.close(index); //再执行关闭
                parent.layer.msg("添加成功");
            } else {
                layer.msg("添加失败");
            }
        }, 'json');
        // console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });


    $.getJSON('/getAllUserId', function (val) {
        $.each(val, function (i, n) {
            $("<option>" + n.id + "</option>").appendTo($("#userid"));
        });
        //更新渲染
        form.render('select');
    });


});