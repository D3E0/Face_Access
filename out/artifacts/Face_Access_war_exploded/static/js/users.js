layui.use(['jquery', 'laypage', 'table', 'layer', 'element', 'laydate'], function () {
    var $ = layui.$, laypage = layui.laypage
        , table = layui.table, layer = layui.layer
        , element = layui.element, laydate = layui.laydate;

    parent.register.userTable = table.render({
        elem: '#userTable'
        , url: '/users.json'
        , page: true
        , cols: [[
            {field: 'houseId', title: '房间 ID', align: "center"}
            , {field: 'userName', title: '用户名', align: "center"}
            , {field: 'remark', title: '备注', align: "center"}
            , {field: 'startDate', title: '授权日期', align: "center"}
            , {field: 'endDate', title: '失效日期', align: "center"}
            , {fixed: 'right', title: '操作', align: 'center', toolbar: '#toolBar'}
        ]]
        , id: "userTable"
    });

    //监听工具条 tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
    table.on('tool(userTable)', function (obj) {
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值
        // console.info(data.userId + " " + data.authorityId);
        if (layEvent === 'detail') { //查看
            parent.layer.open({
                type: 1,
                title: false,
                closeBtn: 0,
                shadeClose: true,
                content: '<div><img src="/static/images/666.jpg" style="width: 100%"/></div>'
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
                    $.post("/deleteAuthority", {id: data.authorityId}, function (data) {
                        var dataObj = eval("(" + data + ")");
                        if (dataObj.result === 'success') {
                            layer.msg("删除成功");
                            obj.del();
                        } else {
                            layer.msg("删除失败");
                        }
                    })
                }
            });
        } else if (layEvent === 'edit') { //编辑;
            parent.layer.open({
                type: 2,
                content: ['/authorityInfo?start=' + data.startDate + '&end=' + data.endDate + '&remark=' + data.remark, 'no'],
                title: false,
                shade: 0,
                btn: ['确认', '取消'],
                area: ['400', '470'],
                btnAlign: 'c',
                id: 'first',
                resize: false,
                yes: function (index, layero) {
                    var editFrame = parent.document.getElementById(layero.find('iframe')[0]['name']);
                    var editDoc = editFrame.contentDocument;
                    var endDate = $("#endDate", editDoc).val();
                    var remark = $("#remark", editDoc).val();
                    $.post('/updateAuthority', {end: endDate, id: data.authorityId, remark: remark}, function (val) {
                        var dataObj = eval("(" + val + ")");
                        if (dataObj.result === 'success') {
                            obj.update({
                                endDate: endDate,
                                remark: remark
                            });
                            parent.layer.msg('修改成功');
                            parent.layer.close(index); //如果设定了yes回调，需进行手工关闭
                        } else {
                            parent.layer.msg("修改失败");
                        }

                    });

                }
            })
        }
    });

    $("#add").click(function () {
        //用于关闭自身
        parent.register.addIndex = parent.layer.open({
            type: 2,
            content: ['/addAuthority', 'no'],
            title: '添加人员',
            area: ['500', '540'],
            resize: false,
            shade: 0,
            id: "second"//同一 ID 窗口打开一个
        });
    });
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


// $.post('/updateRemark', {id: obj.data.authorityId, remark: obj.value()}, function (data) {
//     console.info(data);
// })