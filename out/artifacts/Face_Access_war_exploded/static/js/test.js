layui.use(['element', 'laypage', 'table', 'layer'], function () {
    var element = layui.element, laypage = layui.laypage
        , table = layui.table, layer = layui.layer;

    table.render({
        elem: '#userTable'
        , url: '/users.json'
        // , height: 400
        , page: true
        , cols: [[
            {field: 'userId', title: 'ID', align: "center"}
            , {
                field: 'userName', title: '用户名', align: "center", templet: function (d) {
                    return '<div class="layui-bg-red">d.userName</div>'
                }
            }
            , {field: 'userTelephone', title: '联系方式', align: "center"}
            , {fixed: 'right', width: 150, align: 'center', toolbar: '#toolBar'}
        ]]
    });

    //监听工具条 tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
    table.on('tool(userTable)', function (obj) {
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）

        if (layEvent === 'detail') { //查看
            console.info("detail " + obj.data);
            layer.msg("datail");
        } else if (layEvent === 'del') { //删除
            console.info("delete " + obj.data);
        } else if (layEvent === 'edit') { //编辑
            console.info("edit " + obj.data);
        }
    });

    laypage.render({
        elem: 'page' //注意，这里的 test1 是 ID，不用加 # 号
        , count: 50 //数据总数，从服务端得到
    });
});

function f() {
    var layer = layui.layer;
    // layer.msg('ss');
    console.info('dsad');
    // layer.open({
    //     type: 0,
    //     content: '0（信息框，默认）' //这里content是一个普通的String
    // });
    // layer.open({
    //     type: 1,
    //     content: '1（页面层）' //这里content是一个普通的String
    // });
    // layer.open({
    //     type: 3,
    //     content: '3（加载层）' //这里content是一个普通的String
    // });
    // layer.open({
    //     type: 4,
    //     content: '4（tips层）' //这里content是一个普通的String
    // });
    layer.open({
        type: 2,
        content: 'http://t.tt' //这里content是一个普通的String
    });

}