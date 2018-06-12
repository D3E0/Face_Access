layui.use(['jquery', 'laypage', 'table', 'layer', 'element', 'laydate'], function () {
    var $ = layui.$, laypage = layui.laypage
        , table = layui.table, layer = layui.layer
        , element = layui.element;
    layer.load();
    table.render({
        elem: '#houseTable'
        , url: '/housesjson'
        , page: true
        , cols: [[
            {field: 'houseId', title: 'ID', align: "center"}
            ,{field: 'userId', title: '业主ID', align: "center"}
            , {field: 'username', title: '业主名字', align: "center"}
            , {fixed: 'right', title: '操作', align: 'center', toolbar: '#toolBar'}
        ]],
        done:function(res, curr, count){
            layer.closeAll('loading');
        }
    });
    //监听工具条 tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
    table.on('tool(houseTable)', function (obj) {
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值
        if (layEvent === 'del') { //删除
            layer.open({
                content: '<div style="text-align: center">确定要删除该门禁吗？</div>'
                , btn: ['取消', '删除'] //按钮
                , btnAlign: 'c'
                , yes: function () {
                    layer.msg("取消删除");
                }
                , btn2: function () {
                    layer.msg("确认删除");
                    obj.del();
                    $.post("/delhouse",
                        {
                            houseid:data.houseId
                        },
                        function(data,status){
                            layer.msg(data);
                        });
                }
            });
        } else if (layEvent === 'edit') { //编辑;
            layer.open({
                type: 2,
                content: ['/updatedoorview?id=' + data.Id+ '&location=' + data.Location+ '&ip=' + data.Ip+ '&status=' + data.Status, 'no'],
                title: "修改信息",
                shade: 0,
                btn: ['确认', '取消'],
                area: ['500', '580'],
                btnAlign: 'c',
                id: 'first',
                resize: false,
                yes: function (index, layero) {
                    var test = $('#' + layero.find('iframe')[0]['name']).get(0);
                    var doc = test.contentDocument;

                    // layer.msg('修改成功'+ip);
                    layer.close(index); //如果设定了yes回调，需进行手工关闭
                }
            })
        }
    });

    $("#add").click(function () {
        layer.open({
            type: 2,
            content: ['/addhouseview', 'no'],
            title: '添加门禁',
            btn: ['确认', '取消'],
            area: ['500', '580'],
            resize: false,
            btnAlign:'c',
            shade: 0,
            id: "second",
            yes: function (index, layero) {
                var test = $('#' + layero.find('iframe')[0]['name']).get(0);
                var doc = test.contentDocument;
                var id = $("#id", doc).val();
                var doorid = $("#doorid", doc).val();
                var userid = $("#userid", doc).val();
                var password = $("#password", doc).val();
                $.post("/addhouse",
                    {
                        "Content-Type:text/html;charset":"utf8",
                        houseid:id,
                        doorid:doorid,
                        userid: userid,
                        housepassword:password
                    },
                    function(data,status){
                        layer.msg(data);
                    });
                layer.close(index); //如果设定了yes回调，需进行手工关闭
            }
        });
    })

});

