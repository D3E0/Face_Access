layui.use(['jquery', 'laypage', 'table', 'layer', 'element', 'laydate'], function () {
    var $ = layui.$, laypage = layui.laypage
        , table = layui.table, layer = layui.layer
        , element = layui.element, laydate = layui.laydate;

    table.render({
        elem: '#userTable'
        , url: '/users.json'
        , page: true
        , cols: [[
            {field: 'userId', title: 'ID', align: "center"}
            , {field: 'userName', title: '用户名', align: "center"}
            , {field: 'userTelephone', title: '联系方式', align: "center"}
            , {field: 'startDate', title: '授权日期', align: "center"}
            , {field: 'endDate', title: '失效日期', align: "center"}
            , {fixed: 'right', title: '操作', align: 'center', toolbar: '#toolBar'}
        ]]
    });

    //监听工具条 tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
    table.on('tool(userTable)', function (obj) {
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值
        // console.info(data.userId + " " + data.authorityId);
        if (layEvent === 'detail') { //查看
            layer.open({
                type: 1,
                title: false,
                closeBtn: 0,
                shadeClose: true,
                content: '<div><img src="/static/images/666.jpg" style="width: 100%"/></div>'//$('#div_preview')
            });
        } else if (layEvent === 'del') { //删除
            layer.open({
                content: '<div style="text-align: center">确定要删除该人员吗？</div>'
                , btn: ['取消', '删除'] //按钮
                , btnAlign: 'c'
                , yes: function () {
                    layer.msg("取消删除");
                }
                , btn2: function () {
                    layer.msg("确认删除");
                }
            });
        } else if (layEvent === 'edit') { //编辑;
            var index = layer.open({
                type: 2,
                content: ['/choosedate?start=' + data.startDate + '&end=' + data.endDate, 'no'],
                title: "请选择失效日期",
                shade: 0,
                btn: ['确认', '取消'],
                area: ['450', '480'],
                btnAlign: 'c',
                id: 'first',
                resize: false,
                yes: function (index, layero) {
                    var test = $('#' + layero.find('iframe')[0]['name']).get(0);
                    var doc = test.contentDocument;
                    var x = $("#endDate", doc).val();
                    // console.info(x);
                    //这里一般是发送修改的 Ajax 请求
                    //同步更新表格和缓存对应的值
                    obj.update({
                        endDate: x
                    });
                    layer.msg('修改成功');
                    layer.close(index); //如果设定了yes回调，需进行手工关闭
                }
            })
        }
    });

    $("#add").click(function () {
        layer.open({
            type: 2,
            content: ['/adduser', 'no'],
            title: '添加人员',
            area: ['500', '580'],
            resize: false,
            shade: 0,
            id: "second"
        });
    })

});

// function check(date) {
//     if (date[date.length - 2] == '-') {
//         //yyyy-MM-d
//         return new Date(date).getDate() == date.substring(date.length - 1);
//     } else {
//         //yyyy-MM-dd
//         return new Date(date).getDate() == date.substring(date.length - 2);
//     }
// }