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
        , value: (new Date())
        , isInitValue: true
    });

    form.on('select(username)', function (data) {
        $.getJSON('/user.json', {username: data.value}, function (val) {
            $("input[name='userTel']").val(val.userTel);
            $("input[name='userId']").val(val.userId);
        });
    });

    form.on('submit(submit)', function (data) {
        $.post('/processAddAuthority', data.field, function (data) {
            if (data.result === 'success') {
                var index = parent.register.addIndex;
                parent.layer.close(index); //关闭自身
                parent.layer.msg("添加成功");
                //userTable 表格重载
                var siberDoc = parent.document.getElementById("frame").contentDocument;
                $(".layui-laypage-btn", siberDoc)[0].click();
                // parent.register.userTable.reload({
                //     url: '/users.json'
                // });
            } else {
                layer.msg("添加失败");
            }
        }, 'json');
        // console.log(data.field); //当前容器的全部表单字段，名值对形式：{name: value}
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });


    $.getJSON('/getAllUsername', function (val) {
        $.each(val, function (i, n) {
            $("<option>" + n.username + "</option>").appendTo($("#username"));
        });
        //更新渲染
        form.render('select');
    });

    $.getJSON('/getHouse', {userId: parent.id}, function (val) {
        $.each(val, function (i, n) {
            $("<option>" + n.houseId + "</option>").appendTo($("#houseId"));
        });
        //更新渲染
        form.render('select');
    });

});