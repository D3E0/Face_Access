layui.use(['jquery', 'laypage', 'table', 'layer', 'element', 'laydate'], function () {
    var $ = layui.$, laypage = layui.laypage
        , table = layui.table, layer = layui.layer
        , element = layui.element;
    layer.load();
    table.render({
        elem: '#recordTable'
        , url: '/recordsjson'
        , page: true
        , cols: [[
            // {field: 'Id', title: 'ID', align: "center"}
            // , {field: 'doorId', title: '门的id', align: "center"}
             {field: 'doorLocation', title: '门的位置', align: "center"}
            // , {field: 'userID', title: '用户id', align: "center"}
            , {field: 'userName', title: '用户名', align: "center"}
            , {field: 'openDate', title: '开门日期', align: "center"}
            , {field: 'openResult', title: '结果', align: "center"}
            , {fixed: 'right', title: '操作', align: 'center', toolbar: '#toolBar'}
        ]],
        done:function(res, curr, count){
            layer.closeAll('loading');
        }
    });
    laypage.render({
        limit:10
    });
    //监听工具条 tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
    table.on('tool(recordTable)', function (obj) {
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值
        if (layEvent === 'show') { //编辑;
        }
    });
});
